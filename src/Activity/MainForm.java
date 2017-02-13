/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activity;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author SacredGeeks
 */
public class MainForm extends javax.swing.JFrame {
    private Connection konek;
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        openKoneksi();
        
    }
    
    private void openKoneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String host = "localhost";
            String port = "3306";
            String db = "db_ikan";
            String username = "root";
            String password = "";
            
            konek = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, username, password);
            System.out.println("Koneksi Berhasil");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Driver tidak ditemukan");
        } catch (SQLException sql) {
            System.out.println("Koneksi ke database mysql gagal");
        }
    }
   
    private List getData(int jumlah,String sort){
        List dataSekolah = new ArrayList();
        String tampilData = "CALL biasa_"+sort+"("+jumlah+");";
        int nomer = 0;
        try {
            Statement stmt = konek.createStatement();
            ResultSet res = stmt.executeQuery(tampilData);
            if(res != null){
                while(res.next()){
                    nomer += 1;
                    String no = ""+nomer;
                    String sekolah_asal = res.getString("sekolah_asal");
                    String kuota = res.getString("lol");
                    String agama = res.getString("agama");
                    String fisika = res.getString("fisika");
                    String indo = res.getString("indo");
                    String inggris = res.getString("inggris");
                    String ipa = res.getString("ipa");
                    String ips = res.getString("ips");
                    String kewirausahaan = res.getString("kewirausahaan");
                    String kimia = res.getString("kimia");
                    String kkpi = res.getString("kkpi");
                    String matamatika = res.getString("matematika");
                    String pjok = res.getString("pjok");
                    String pkn = res.getString("pkn");
                    String plh = res.getString("plh");
                    String produktif = res.getString("produktif");
                    String senbud = res.getString("senbud");
                    String sunda = res.getString("sunda");
                    
                    Object objSekolah = new Object();
                    objSekolah.setNo(no);
                    objSekolah.setSekolah_asal(sekolah_asal);
                    objSekolah.setKuota(kuota+" Siswa");
                    
                    objSekolah.setAgama(agama);
                    objSekolah.setFisika(fisika);
                    objSekolah.setIndo(indo);
                    objSekolah.setInggris(inggris);
                    objSekolah.setIpa(ipa);
                    objSekolah.setIps(ips);
                    objSekolah.setKewirausahaan(kewirausahaan);
                    objSekolah.setKimia(kimia);
                    objSekolah.setKkpi(kkpi);
                    objSekolah.setMatematika(matamatika);
                    objSekolah.setPjok(pjok);
                    objSekolah.setPkn(pkn);
                    objSekolah.setPlh(plh);
                    objSekolah.setProduktif(produktif);
                    objSekolah.setSenbud(senbud);
                    objSekolah.setSunda(sunda);                  
                    
                    dataSekolah.add(objSekolah);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataSekolah;
    }
    
    private void callReportSingle(int jum,String sort) {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getData(jum,sort), false);
            
            HashMap param = new HashMap();
            
            String reportName = "SingleReport.jasper";
            
            ClassLoader cl = getClass().getClassLoader();
            InputStream inReportFile = cl.getResourceAsStream(reportName);
            
            JasperPrint print = JasperFillManager.fillReport(inReportFile, param, dataSource);
            
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
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

        RGBerdasarkan = new javax.swing.ButtonGroup();
        CbBaris = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbUrut = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        RKuota = new javax.swing.JRadioButton();
        RNilai = new javax.swing.JRadioButton();
        CbPelajaran = new javax.swing.JComboBox<>();
        BtnShow = new javax.swing.JButton();
        BtnExit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        CbBaris.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "30", "50", "100" }));
        CbBaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbBarisActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tampilkan");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Baris");

        CbUrut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Menaik", "Menurun" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Urutkan data secara");

        RGBerdasarkan.add(RKuota);
        RKuota.setText("Berdasarkan Kuota Masuk");
        RKuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RKuotaActionPerformed(evt);
            }
        });

        RGBerdasarkan.add(RNilai);
        RNilai.setText("Berdasarkan Nilai Pelajaran");
        RNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RNilaiActionPerformed(evt);
            }
        });

        CbPelajaran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agama", "Fisika", "Bahasa Indonesia", "Bahasa Inggris", "Bahasa Sunda", "IPA", "IPS", "Kewirausahaan", "Kimia", "KKPI", "Matematika", "PJOK", "PKN", "PLH", "Produktif", "Seni Budaya" }));

        BtnShow.setText("Tampilkan Laporan");
        BtnShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnShowMouseClicked(evt);
            }
        });

        BtnExit.setText("Keluar");
        BtnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnExitMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setText("Laporan Data Sekolah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnShow)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnExit))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RKuota)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(RNilai)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CbPelajaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 57, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CbBaris, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(CbUrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CbBaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CbUrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RKuota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RNilai)
                    .addComponent(CbPelajaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(BtnShow)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnExit)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CbBarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbBarisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbBarisActionPerformed

    private void RKuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RKuotaActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_RKuotaActionPerformed

    private void RNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RNilaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RNilaiActionPerformed

    private void BtnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtnExitMouseClicked

    private void BtnShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnShowMouseClicked
        // TODO add your handling code here:
        String Sort = "";
        if (RKuota.isSelected() == false && RNilai.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Pilihan Nilai / Kuota Belum Diisi");
        } else {
            String c = null;
            String d = null;
            String a = CbBaris.getSelectedItem().toString();
            String b = CbUrut.getSelectedItem().toString();
            
            int Baris = Integer.parseInt(a);
            
                if(RKuota.isSelected() == true) {
                    c = "Kuota";
                } else if(RNilai.isSelected() == true) {
                    d = CbPelajaran.getSelectedItem().toString();
                    c = "Nilai "+d;
                }
                
                if (b == "Menaik") {
                    Sort = "Asc";
                } else {
                    Sort = "Desc";
                }

            System.out.println("Menampilkan "+Baris+" Baris");
            System.out.println("Diurutkan Secara "+b);
            System.out.println("Berdasarkan "+c);
            callReportSingle(Baris,Sort);
        }
    }//GEN-LAST:event_BtnShowMouseClicked

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnShow;
    private javax.swing.JComboBox<String> CbBaris;
    private javax.swing.JComboBox<String> CbPelajaran;
    private javax.swing.JComboBox<String> CbUrut;
    private javax.swing.ButtonGroup RGBerdasarkan;
    private javax.swing.JRadioButton RKuota;
    private javax.swing.JRadioButton RNilai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
