/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import connection.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connection.connect;

/**
 *
 * @author Asus
 */
public class Proses_SAW extends javax.swing.JFrame {

    private Connection conn = new connect().connect();

    /**
     * Creates new form Proses_SAW
     */
    public Proses_SAW() {
        initComponents();
        this.setLocationRelativeTo(null);

    }

    private void simpanHasilSAW(DefaultTableModel modelNormalisasi, DefaultTableModel modelPeringkat) {
        try {
            connect conn = new connect();
            Connection con = conn.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM saw");
            stmt.executeUpdate("DELETE FROM hasil_akhir");

            int baris = modelNormalisasi.getRowCount();

            for (int i = 0; i < baris; i++) {
                String namaSiswa = modelPeringkat.getValueAt(i, 0).toString();
                double skorAkhir = Double.parseDouble(modelPeringkat.getValueAt(i, 1).toString());

                int idSiswa = getIdSiswaByNama(namaSiswa, con);
                
                String deleteSaw = "DELETE FROM saw";
                String deleteHasil = "DELETE FROM hasil_akhir";
                String sqlInsertHasil = "INSERT INTO hasil_akhir (id_siswa, skor_akhir) VALUES (?, ?)";
                PreparedStatement psHasil = con.prepareStatement(sqlInsertHasil, Statement.RETURN_GENERATED_KEYS);
                psHasil.setInt(1, idSiswa);
                psHasil.setDouble(2, skorAkhir);
                psHasil.executeUpdate();

                ResultSet rsHasil = psHasil.getGeneratedKeys();
                int idHasil = 0;
                if (rsHasil.next()) {
                    idHasil = rsHasil.getInt(1);
                } else {
                    throw new SQLException("Gagal mendapatkan id_hasil.");
                }
                for (int j = 1; j <= 5; j++) {
                    double nilaiNormalisasi = Double.parseDouble(modelNormalisasi.getValueAt(i, j).toString());
                    int idKriteria = j;
                    int idPenilaian = getIdPenilaian(idSiswa, idKriteria, con);

                    String sqlSaw = "INSERT INTO saw (id_siswa, id_penilaian, id_kriteria, nilai_normalisasi, id_hasil) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psSaw = con.prepareStatement(sqlSaw);
                    psSaw.setInt(1, idSiswa);
                    psSaw.setInt(2, idPenilaian);
                    psSaw.setInt(3, idKriteria);
                    psSaw.setDouble(4, nilaiNormalisasi);
                    psSaw.setInt(5, idHasil);
                    psSaw.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(null, "Data hasil SAW berhasil disimpan ke database.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan hasil SAW: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void tampilTabelX() {
        Object[] kolom = {"Nama Siswa", "Rapot", "Absensi", "Sikap", "Ekskul", "Keterampilan"};
        DefaultTableModel model = new DefaultTableModel(null, kolom);
        tableX.setModel(model);

        try {
            String sql = "SELECT a.nama_siswa, "
                    + "MAX(CASE WHEN n.id_kriteria = 1 THEN n.nilai END) AS rapot, "
                    + "MAX(CASE WHEN n.id_kriteria = 2 THEN n.nilai END) AS absensi, "
                    + "MAX(CASE WHEN n.id_kriteria = 3 THEN n.nilai END) AS sikap, "
                    + "MAX(CASE WHEN n.id_kriteria = 4 THEN n.nilai END) AS ekskul, "
                    + "MAX(CASE WHEN n.id_kriteria = 5 THEN n.nilai END) AS keterampilan "
                    + "FROM nilai_siswa n "
                    + "JOIN alternatif a ON n.id_siswa = a.id_siswa "
                    + "GROUP BY a.nama_siswa";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] data = {
                    rs.getString("nama_siswa"),
                    rs.getInt("rapot"),
                    rs.getInt("absensi"),
                    rs.getInt("sikap"),
                    rs.getInt("ekskul"),
                    rs.getInt("keterampilan")
                };
                model.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal tampil Tabel X: " + e.getMessage());
        }
    }

    private void tampilNormalisasi() {
        DefaultTableModel model = (DefaultTableModel) tableX.getModel();
        int rowCount = model.getRowCount();

        double[][] nilai = new double[rowCount][5];
        double[] max = new double[5];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 5; j++) {
                nilai[i][j] = Double.parseDouble(model.getValueAt(i, j + 1).toString());
                if (nilai[i][j] > max[j]) {
                    max[j] = nilai[i][j];
                }
            }
        }

        String[] kolom = {"Nama Siswa", "Rapot", "Absensi", "Sikap", "Ekskul", "Keterampilan"};
        DefaultTableModel normalModel = new DefaultTableModel(null, kolom);
        tableNormalisasi.setModel(normalModel);

        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[6];
            row[0] = model.getValueAt(i, 0);
            for (int j = 0; j < 5; j++) {
                row[j + 1] = String.format("%.4f", nilai[i][j] / max[j]);
            }
            normalModel.addRow(row);
        }
    }

    private void tampilPeringkingan() {
        double[] bobot = {0.25, 0.15, 0.2, 0.2, 0.2};

        DefaultTableModel model = (DefaultTableModel) tableNormalisasi.getModel();
        int rowCount = model.getRowCount();

        String[] kolom = {"Nama Siswa", "Preferensi", "Ranking"};
        DefaultTableModel prefModel = new DefaultTableModel(null, kolom);
        tablePrefrensi.setModel(prefModel);

        ArrayList<Object[]> hasil = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            double skor = 0;
            for (int j = 0; j < 5; j++) {
                skor += Double.parseDouble(model.getValueAt(i, j + 1).toString()) * bobot[j];
            }
            hasil.add(new Object[]{model.getValueAt(i, 0), skor});
        }

        hasil.sort((a, b) -> Double.compare((double) b[1], (double) a[1]));

        int no = 1;
        for (Object[] h : hasil) {
            prefModel.addRow(new Object[]{h[0], String.format("%.4f", h[1]), no++});
        }
    }

    private int getIdSiswaByNama(String nama, Connection con) throws SQLException {
        String sql = "SELECT id_siswa FROM alternatif WHERE nama_siswa = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nama);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_siswa");
        }
        throw new SQLException("Siswa tidak ditemukan: " + nama);
    }

    private int getIdPenilaian(int idSiswa, int idKriteria, Connection con) throws SQLException {
        String sql = "SELECT id_penilaian FROM nilai_siswa WHERE id_siswa = ? AND id_kriteria = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idSiswa);
        ps.setInt(2, idKriteria);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_penilaian");
        }
        throw new SQLException("Penilaian tidak ditemukan untuk siswa ID " + idSiswa + " dan kriteria ID " + idKriteria);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableX = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNormalisasi = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePrefrensi = new javax.swing.JTable();
        saw_normalisasi = new javax.swing.JButton();
        saw_perhitungan = new javax.swing.JButton();
        saw_tableX = new javax.swing.JButton();
        saw_kembali = new javax.swing.JButton();
        saw_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Proses Penilaian Siswa Terbaik");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 60));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        tableX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableX);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tabel X");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tabel Normalisasi");

        tableNormalisasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableNormalisasi);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tabel Prefrensi / Peringkingan");

        tablePrefrensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablePrefrensi);

        saw_normalisasi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saw_normalisasi.setText("Hitung Normalisasi");
        saw_normalisasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saw_normalisasiActionPerformed(evt);
            }
        });

        saw_perhitungan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saw_perhitungan.setText("Hitung Perangkingan");
        saw_perhitungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saw_perhitunganActionPerformed(evt);
            }
        });

        saw_tableX.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saw_tableX.setText("Hitung Tabel X");
        saw_tableX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saw_tableXActionPerformed(evt);
            }
        });

        saw_kembali.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saw_kembali.setText("Kembali");
        saw_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saw_kembaliActionPerformed(evt);
            }
        });

        saw_simpan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saw_simpan.setText("Simpan");
        saw_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saw_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(saw_tableX)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saw_normalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saw_perhitungan, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saw_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saw_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 200, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saw_normalisasi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saw_tableX, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saw_perhitungan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saw_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saw_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1070, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saw_tableXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saw_tableXActionPerformed
        // TODO add your handling code here:
        tampilTabelX();
    }//GEN-LAST:event_saw_tableXActionPerformed

    private void saw_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saw_kembaliActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_saw_kembaliActionPerformed

    private void saw_normalisasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saw_normalisasiActionPerformed
        // TODO add your handling code here:
        tampilNormalisasi();
    }//GEN-LAST:event_saw_normalisasiActionPerformed

    private void saw_perhitunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saw_perhitunganActionPerformed
        // TODO add your handling code here:
        tampilPeringkingan();
    }//GEN-LAST:event_saw_perhitunganActionPerformed

    private void saw_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saw_simpanActionPerformed
        // TODO add your handling code here:

        DefaultTableModel modelNormalisasi = (DefaultTableModel) tableNormalisasi.getModel();
        DefaultTableModel modelPeringkat = (DefaultTableModel) tablePrefrensi.getModel();

        simpanHasilSAW(modelNormalisasi, modelPeringkat);
    }//GEN-LAST:event_saw_simpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proses_SAW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proses_SAW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proses_SAW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proses_SAW.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proses_SAW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton saw_kembali;
    private javax.swing.JButton saw_normalisasi;
    private javax.swing.JButton saw_perhitungan;
    private javax.swing.JButton saw_simpan;
    private javax.swing.JButton saw_tableX;
    private javax.swing.JTable tableNormalisasi;
    private javax.swing.JTable tablePrefrensi;
    private javax.swing.JTable tableX;
    // End of variables declaration//GEN-END:variables
}
