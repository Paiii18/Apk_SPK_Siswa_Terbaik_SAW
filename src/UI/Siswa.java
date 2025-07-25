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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Siswa extends javax.swing.JFrame {

    private Connection conn = new connect().connect();
    private DefaultTableModel tabmode;

    /**
     * Creates new form Siswa
     */
    public Siswa() {
        initComponents();
        datatable();
        autoKodeSiswa();
        ds_kode.setEditable(false);
        this.setLocationRelativeTo(null);
        ds_cari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                cariData(ds_cari.getText());
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                cariData(ds_cari.getText());
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                cariData(ds_cari.getText());
            }
        });
    }

    private void clear() {
        ds_kode.setText("");
        ds_nisn.setText("");
        ds_namasiswa.setText("");
        ds_kelas.setText("");
        ds_jenkel.setSelectedIndex(0);
    }

    private void active() {
        ds_kode.setText("");
        ds_nisn.setEnabled(true);
        ds_namasiswa.setEnabled(true);
        ds_kelas.setEnabled(true);
        ds_jenkel.setEnabled(true);
        ds_kode.requestFocus();
    }

    protected void datatable() {
        Object[] clcis = {"Kode", "Nisn", "Nama", "Kelas", "Jenis Kelamin", "Semester", "Tahun Ajaran"};
        tabmode = new DefaultTableModel(null, clcis);
        tablesiswa.setModel(tabmode);
        tabmode.setRowCount(0);
        String sql = "select * from alternatif";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String a = hasil.getString("kode_siswa");
                String b = hasil.getString("nisn");
                String c = hasil.getString("nama_siswa");
                String d = hasil.getString("kelas");
                String e = hasil.getString("jenkel");
                String f = hasil.getString("semester");
                String g = hasil.getString("tahun_ajaran");

                String[] data = {a, b, c, d, e, f, g};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal load tabel: " + e.getMessage());
        }
    }

    private void autoKodeSiswa() {
        try {
            String sql = "SELECT MAX(kode_siswa) FROM alternatif";
            java.sql.Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString(1);
                if (kode == null) {
                    ds_kode.setText("S001");
                } else {
                    int no = Integer.parseInt(kode.substring(1)) + 1;
                    String kodeBaru = String.format("S%03d", no);
                    ds_kode.setText(kodeBaru);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal generate kode siswa: " + e.getMessage());
        }
    }

    private void cariData(String keyword) {
        Object[] clcis = {"Kode", "NISN", "Nama", "Kelas", "Jenis Kelamin", "Semester", "Tahun Ajaran"};
        tabmode = new DefaultTableModel(null, clcis);
        tablesiswa.setModel(tabmode);

        String sql = "SELECT * FROM alternatif WHERE nama_siswa LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String a = rs.getString("kode_siswa");
                String b = rs.getString("nisn");
                String c = rs.getString("nama_siswa");
                String d = rs.getString("kelas");
                String e = rs.getString("jenkel");
                String f = rs.getString("semester");
                String g = rs.getString("tahun_ajaran");
                String[] data = {a, b, c, d, e, f, g};
                tabmode.addRow(data);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Pencarian gagal: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ds_nisn = new javax.swing.JTextField();
        ds_kode = new javax.swing.JTextField();
        ds_namasiswa = new javax.swing.JTextField();
        ds_kelas = new javax.swing.JTextField();
        ds_jenkel = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablesiswa = new javax.swing.JTable();
        ds_cari = new javax.swing.JTextField();
        ds_simpan = new javax.swing.JButton();
        ds_ubah = new javax.swing.JButton();
        ds_hapus = new javax.swing.JButton();
        ds_kembali = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ds_semester = new javax.swing.JComboBox<>();
        ds_tahun = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Siswa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 59));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 475));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Siswa");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NISN");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nama Siswa");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kelas");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Semester");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cari Nama");

        ds_nisn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        ds_kode.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ds_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_kodeActionPerformed(evt);
            }
        });

        ds_namasiswa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        ds_kelas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        ds_jenkel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ds_jenkel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        tablesiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tablesiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablesiswa);

        ds_cari.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ds_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_cariActionPerformed(evt);
            }
        });

        ds_simpan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ds_simpan.setText("Simpan");
        ds_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_simpanActionPerformed(evt);
            }
        });

        ds_ubah.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ds_ubah.setText("Ubah");
        ds_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_ubahActionPerformed(evt);
            }
        });

        ds_hapus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ds_hapus.setText("Hapus");
        ds_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_hapusActionPerformed(evt);
            }
        });

        ds_kembali.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ds_kembali.setText("Kembali");
        ds_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ds_kembaliActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Jenis Kelamin");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tahun Ajaran");

        ds_semester.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        ds_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ganjil", "Genap" }));

        ds_tahun.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ds_kode)
                                        .addComponent(ds_nisn)
                                        .addComponent(ds_namasiswa)
                                        .addComponent(ds_kelas)
                                        .addComponent(ds_jenkel, 0, 827, Short.MAX_VALUE)
                                        .addComponent(ds_semester, 0, 827, Short.MAX_VALUE)
                                        .addComponent(ds_tahun, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(ds_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ds_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ds_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ds_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(ds_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(ds_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(ds_nisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(ds_namasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ds_kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ds_jenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(ds_semester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ds_tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ds_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ds_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ds_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ds_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ds_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 59, 1070, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ds_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_kembaliActionPerformed
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_ds_kembaliActionPerformed

    private void ds_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_simpanActionPerformed
        String kode = ds_kode.getText();
        String nisn = ds_nisn.getText();
        String nama = ds_namasiswa.getText();
        String kelas = ds_kelas.getText();
        String jenkel = ds_jenkel.getSelectedItem().toString();
        String semester = ds_semester.getSelectedItem().toString();
        String tahun = ds_kelas.getText();

        String sql = "INSERT INTO alternatif (kode_siswa,nisn,nama_siswa,kelas,jenkel,semester,tahun_ajaran) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, kode);
            stat.setString(2, nisn);
            stat.setString(3, nama);
            stat.setString(4, kelas);
            stat.setString(5, jenkel);
            stat.setString(6, semester);
            stat.setString(7, tahun);

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            clear();
            ds_kode.requestFocus();
            ds_simpan.setVisible(true);
            autoKodeSiswa();

            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan " + e);
        }
    }//GEN-LAST:event_ds_simpanActionPerformed

    private void ds_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_hapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from alternatif where kode_siswa='" + ds_kode.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "data berhasi dihapus");;
                clear();
                ds_kode.requestFocus();
                datatable();
                autoKodeSiswa();
                ds_simpan.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus" + e);
            }
        }
    }//GEN-LAST:event_ds_hapusActionPerformed

    private void ds_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_ubahActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE alternatif SET kode_siswa=?, nisn=?, nama_siswa=?, kelas=?,jenkel=?, semester=?,tahun_ajaran=? WHERE kode_siswa=?";
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, ds_kode.getText());
            stat.setString(2, ds_nisn.getText());
            stat.setString(3, ds_namasiswa.getText());
            stat.setString(4, ds_kelas.getText());
            stat.setString(5, ds_jenkel.getSelectedItem().toString());
            stat.setString(6, ds_semester.getSelectedItem().toString());
            stat.setString(7, ds_tahun.getText());
            stat.setString(8, ds_kode.getText());

            stat.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah");

            clear();
            ds_kode.requestFocus();
            datatable();
            ds_simpan.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah: " + e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan tidak terduga: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_ds_ubahActionPerformed

    private void tablesiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablesiswaMouseClicked
        // TODO add your handling code here:
        int clc = tablesiswa.getSelectedRow();
        String a = tabmode.getValueAt(clc, 0).toString();
        String b = tabmode.getValueAt(clc, 1).toString();
        String c = tabmode.getValueAt(clc, 2).toString();
        String d = tabmode.getValueAt(clc, 3).toString();
        String e = tabmode.getValueAt(clc, 4).toString();
        String f = tabmode.getValueAt(clc, 5).toString();
        String g = tabmode.getValueAt(clc, 6).toString();

        ds_kode.setText(a);
        ds_nisn.setText(b);
        ds_namasiswa.setText(c);
        ds_kelas.setText(d);
        ds_jenkel.setSelectedIndex(0);
        ds_semester.setSelectedIndex(0);
        ds_tahun.setText(g);

        ds_simpan.setVisible(false);
    }//GEN-LAST:event_tablesiswaMouseClicked

    private void ds_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ds_kodeActionPerformed

    private void ds_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ds_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ds_cariActionPerformed

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
            java.util.logging.Logger.getLogger(Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ds_cari;
    private javax.swing.JButton ds_hapus;
    private javax.swing.JComboBox<String> ds_jenkel;
    private javax.swing.JTextField ds_kelas;
    private javax.swing.JButton ds_kembali;
    private javax.swing.JTextField ds_kode;
    private javax.swing.JTextField ds_namasiswa;
    private javax.swing.JTextField ds_nisn;
    private javax.swing.JComboBox<String> ds_semester;
    private javax.swing.JButton ds_simpan;
    private javax.swing.JTextField ds_tahun;
    private javax.swing.JButton ds_ubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablesiswa;
    // End of variables declaration//GEN-END:variables
}
