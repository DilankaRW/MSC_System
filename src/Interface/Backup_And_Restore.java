/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database_Connection.DB;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Dilanka
 */
public class Backup_And_Restore extends javax.swing.JFrame {

    /**
     * Creates new form Backup_And_Restore
     */
    public Backup_And_Restore() {
        initComponents();
        setIcon();
        DateandTime();
    }
    String path = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        txt_Backup_Path = new javax.swing.JTextField();
        btn_Backup_Open = new javax.swing.JButton();
        btn_Backup = new javax.swing.JButton();
        btn_Restore = new javax.swing.JButton();
        txt_Restore_Path = new javax.swing.JTextField();
        btn_Restore_Open = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/minimize-38_1.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Home-38_1.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lock-38_1.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-cancel-38.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbl_Time.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Time.setText("Time");

        lbl_Date.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Date.setText("Date");

        txt_Backup_Path.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btn_Backup_Open.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Backup_Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save Path.png"))); // NOI18N
        btn_Backup_Open.setText("Backup Path");
        btn_Backup_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Backup_OpenActionPerformed(evt);
            }
        });

        btn_Backup.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Backup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/backup_icon.png"))); // NOI18N
        btn_Backup.setText("Backup");
        btn_Backup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackupActionPerformed(evt);
            }
        });

        btn_Restore.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_Restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/restore_icon.png"))); // NOI18N
        btn_Restore.setText("Restore");
        btn_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RestoreActionPerformed(evt);
            }
        });

        txt_Restore_Path.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btn_Restore_Open.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Restore_Open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save Path.png"))); // NOI18N
        btn_Restore_Open.setText("Restore Path");
        btn_Restore_Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Restore_OpenActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MINO TOP LOGO.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Restore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Backup_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Backup_Open))
                            .addComponent(btn_Backup, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Restore_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Restore_Open, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(180, 180, 180))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_Backup_Path)
                    .addComponent(btn_Backup_Open))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Backup, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Restore_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Restore_Open))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Restore, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            setExtendedState(Customer.ICONIFIED);
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','System Minimized')");
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" +  Login.User_Name  + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Admin Menu')");
        new Admin_Menu().setVisible(true);
        this.dispose();
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Login')");
            new Login().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" +  Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Has been Exit from the System')");
            System.exit(0);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_Backup_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Backup_OpenActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        String date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

        try {
            File f = fc.getSelectedFile();
            path = f.getAbsolutePath();
            path = path.replace('\\', '/');
            path = path + "_" + date + ".sql";
            txt_Backup_Path.setText(path);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_Backup_OpenActionPerformed

    private void btn_BackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackupActionPerformed
        if (txt_Backup_Path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Backup Database Path Not Set ", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe -uroot -p123 --add-drop-database -B viruvo -r" + path);

                int processComplete = p.waitFor();
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(this, "Backup Database Successfully. ", "Information", JOptionPane.INFORMATION_MESSAGE);
                    txt_Backup_Path.setText(null);
                    new Login().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Backup Database Not Successfully. ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    txt_Backup_Path.setText(null);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_BackupActionPerformed

    private void btn_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RestoreActionPerformed
        if (txt_Restore_Path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your Restore Database Path No Set ", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String dbUserName = "root";// username
            String dbPassword = "123";//Password

            String[] restoreCmd = new String[]{"C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\mysql.exe", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + path};
            Process runtimProcess;
            try {
                runtimProcess = Runtime.getRuntime().exec(restoreCmd);
                int proceCom = runtimProcess.waitFor();

                if (proceCom == 0) {
                    JOptionPane.showMessageDialog(this, "Your Restore Database Successfully. ", "Information", JOptionPane.INFORMATION_MESSAGE);
                    txt_Restore_Path.setText(null);
                    new Login().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Your Restore Database Not Successfully. ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    txt_Restore_Path.setText(null);
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btn_RestoreActionPerformed

    private void btn_Restore_OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Restore_OpenActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            File f = fc.getSelectedFile();
            path = f.getAbsolutePath();
            path = path.replace('\\', '/');

            txt_Restore_Path.setText(path);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_Restore_OpenActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Backup_And_Restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Backup_And_Restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Backup_And_Restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Backup_And_Restore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Backup_And_Restore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Backup;
    private javax.swing.JButton btn_Backup_Open;
    private javax.swing.JButton btn_Restore;
    private javax.swing.JButton btn_Restore_Open;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JTextField txt_Backup_Path;
    private javax.swing.JTextField txt_Restore_Path;
    // End of variables declaration//GEN-END:variables

    private void DateandTime() {
        Timer t = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat sdf_Time = new SimpleDateFormat("hh:mm:ss aa");
                SimpleDateFormat sdf_Date = new SimpleDateFormat("YYYY-MM-dd");
                String time = sdf_Time.format(d);
                String Date = sdf_Date.format(d);
                lbl_Time.setText(time);
                lbl_Date.setText(Date);
            }
        });
        t.start();
    }
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon.png")));
    }
}
