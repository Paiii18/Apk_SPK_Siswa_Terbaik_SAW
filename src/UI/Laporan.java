/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import connection.connect;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class Laporan extends javax.swing.JFrame {

    private Connection conn = new connect().connect();

    /**
     * Creates new form NewJFrame
     */
    public Laporan() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        lp_datakriteria = new javax.swing.JButton();
        lp_kembali = new javax.swing.JButton();
        lp_perangkingan = new javax.swing.JButton();
        lp_datasiswa = new javax.swing.JButton();
        lp_nilai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menu Laporan");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 70));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        lp_datakriteria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lp_datakriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/docs_120dp_E3E3E3_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        lp_datakriteria.setText("Laporan Data Kriteria");
        lp_datakriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_datakriteriaActionPerformed(evt);
            }
        });

        lp_kembali.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lp_kembali.setText("Kembali");
        lp_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_kembaliActionPerformed(evt);
            }
        });

        lp_perangkingan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lp_perangkingan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/docs_120dp_E3E3E3_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        lp_perangkingan.setText("Laporan Rangking");
        lp_perangkingan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_perangkinganActionPerformed(evt);
            }
        });

        lp_datasiswa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lp_datasiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/docs_120dp_E3E3E3_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        lp_datasiswa.setText("Laporan Data Siswa");
        lp_datasiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_datasiswaActionPerformed(evt);
            }
        });

        lp_nilai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lp_nilai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/docs_120dp_E3E3E3_FILL0_wght400_GRAD0_opsz48.png"))); // NOI18N
        lp_nilai.setText("Laporan Data Nilai");
        lp_nilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_nilaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lp_datasiswa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lp_nilai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lp_perangkingan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lp_datakriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lp_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp_datasiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lp_datakriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_perangkingan, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lp_nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(lp_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 66, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lp_datakriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_datakriteriaActionPerformed
        // TODO add your handling code here:
        try {
            String namaFile = "src/Reports/LaporanDataKriteria.jasper";

            connect conn = new connect();
            Connection con = conn.connect();

            HashMap<String, Object> parameter = new HashMap<>();
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("EEEE dd MMMM yyyy", new java.util.Locale("id", "ID"));
            String formattedDate = formatter.format(new java.util.Date());
            parameter.put("formattedDate", formattedDate);
            System.out.println("Tanggal formatted: " + formattedDate);
            File report_file = new File(namaFile);
            if (!report_file.exists()) {
                throw new FileNotFoundException("File laporan tidak ditemukan: " + namaFile);
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, con);

            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lp_datakriteriaActionPerformed

    private void lp_perangkinganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_perangkinganActionPerformed
        // TODO add your handling code here:
        try {
            String namaFile = "src/Reports/LaporanRangking.jasper";

            connect conn = new connect();
            Connection con = conn.connect();

            HashMap<String, Object> parameter = new HashMap<>();
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("EEEE dd MMMM yyyy", new java.util.Locale("id", "ID"));
            String formattedDate = formatter.format(new java.util.Date());
            parameter.put("formattedDate", formattedDate);

            File report_file = new File(namaFile);
            if (!report_file.exists()) {
                throw new FileNotFoundException("File laporan tidak ditemukan: " + namaFile);
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, con);

            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lp_perangkinganActionPerformed

    private void lp_datasiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_datasiswaActionPerformed
        // TODO add your handling code here:
        try {
            String namaFile = "src/Reports/LaporanDataSiswa.jasper";

            connect conn = new connect();
            Connection con = conn.connect();

            HashMap<String, Object> parameter = new HashMap<>();
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("EEEE dd MMMM yyyy", new java.util.Locale("id", "ID"));
            String formattedDate = formatter.format(new java.util.Date());
            parameter.put("formattedDate", formattedDate);
            System.out.println("Tanggal formatted: " + formattedDate);

            File report_file = new File(namaFile);
            if (!report_file.exists()) {
                throw new FileNotFoundException("File laporan tidak ditemukan: " + namaFile);
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, con);

            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lp_datasiswaActionPerformed

    private void lp_nilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_nilaiActionPerformed
        // TODO add your handling code here:
        try {
            String namaFile = "src/Reports/LaporanDataNilai.jasper";

            connect conn = new connect();
            Connection con = conn.connect();

            HashMap<String, Object> parameter = new HashMap<>();
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("EEEE dd MMMM yyyy", new java.util.Locale("id", "ID"));
            String formattedDate = formatter.format(new java.util.Date());
            parameter.put("formattedDate", formattedDate);

            File report_file = new File(namaFile);
            if (!report_file.exists()) {
                throw new FileNotFoundException("File laporan tidak ditemukan: " + namaFile);
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, con);

            JasperViewer.viewReport(jasperPrint, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_lp_nilaiActionPerformed

    private void lp_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_kembaliActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        dispose();
    }//GEN-LAST:event_lp_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(Laporan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lp_datakriteria;
    private javax.swing.JButton lp_datasiswa;
    private javax.swing.JButton lp_kembali;
    private javax.swing.JButton lp_nilai;
    private javax.swing.JButton lp_perangkingan;
    // End of variables declaration//GEN-END:variables
}
