/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database_Connection.DB;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dilanka
 */
public class Petty_Cash extends javax.swing.JFrame {

    /**
     * Creates new form Petty_Cash
     */
    public Petty_Cash() {
        initComponents();
        DateandTime();
        Load_data();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        jtxt_Petty_Transaction_Amount1.setText(date);

        Petty_Table_Loader_Data();
        jTable1.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        setIcon();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jtxt_Description = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jtxt_Petty_Transaction_Amount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Time = new javax.swing.JLabel();
        jtxt_Petty_Available_Amount = new javax.swing.JLabel();
        jtxt_Petty_Available_Amount1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtxt_Petty_Transaction_Amount1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        jTabbedPane1.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Available");

        jtxt_Description.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jtxt_Description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_DescriptionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_DescriptionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_DescriptionKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/plus.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/minus.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jtxt_Petty_Transaction_Amount.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Petty_Transaction_Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Petty_Transaction_AmountActionPerformed(evt);
            }
        });
        jtxt_Petty_Transaction_Amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_AmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_AmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_AmountKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Amount");

        jLabel10.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Description");

        lbl_Date.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Date.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Date.setText("Date");

        lbl_Time.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Time.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Time.setText("Date");

        jtxt_Petty_Available_Amount.setFont(new java.awt.Font("Euphemia", 0, 36)); // NOI18N
        jtxt_Petty_Available_Amount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxt_Petty_Available_Amount.setText("00.00");

        jtxt_Petty_Available_Amount1.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Petty_Available_Amount1.setForeground(new java.awt.Color(255, 255, 255));
        jtxt_Petty_Available_Amount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jtxt_Petty_Available_Amount1.setText("00.00");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/closered.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_Date, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(lbl_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jtxt_Petty_Transaction_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jtxt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_Petty_Available_Amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jtxt_Petty_Available_Amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxt_Petty_Available_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxt_Petty_Transaction_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxt_Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jtxt_Petty_Available_Amount1)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(131, 131, 131))
        );

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/Petty Cash Up.png")), jPanel2); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Time", "Transaction", "Description", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(90);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(90);
            jTable1.getColumnModel().getColumn(1).setMinWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(2).setMinWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(4).setMinWidth(100);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jtxt_Petty_Transaction_Amount1.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Petty_Transaction_Amount1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxt_Petty_Transaction_Amount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_Amount1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_Amount1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Petty_Transaction_Amount1KeyTyped(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/closered.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jtxt_Petty_Transaction_Amount1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtxt_Petty_Transaction_Amount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6))
        );

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/Petty Cash Details.png")), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtxt_DescriptionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_DescriptionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_DescriptionKeyPressed

    private void jtxt_DescriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_DescriptionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_DescriptionKeyReleased

    private void jtxt_DescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_DescriptionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_DescriptionKeyTyped

    private void jtxt_Petty_Transaction_AmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_AmountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Petty_Transaction_AmountKeyPressed

    private void jtxt_Petty_Transaction_AmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_AmountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Petty_Transaction_AmountKeyReleased

    private void jtxt_Petty_Transaction_AmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_AmountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Petty_Transaction_AmountKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!jtxt_Petty_Transaction_Amount.getText().isEmpty() && !jtxt_Description.getText().isEmpty()) {
            try {
                ResultSet rs = new Database_Connection.DB().getData("SELECT amount FROM petty_amount WHERE petty_id = '1'");
                while (rs.next()) {
                    double QTY = Double.parseDouble(rs.getString("amount").toString());
                    double UP = Double.parseDouble(jtxt_Petty_Transaction_Amount.getText().toString());
                    double tot = QTY + UP;
                    jtxt_Petty_Available_Amount.setText(new DecimalFormat("0.00").format(tot));

                    double UP1 = Double.parseDouble(jtxt_Petty_Transaction_Amount.getText().toString());
                    double tot1 = UP1;
                    jtxt_Petty_Available_Amount1.setText(new DecimalFormat("0.00").format("+" + tot1));

                    new Database_Connection.DB().putData("Update petty_amount Set amount = '" + tot + "' WHERE petty_id = '1' ");
                    new DB().putData("INSERT INTO petty_details (petty_id,date,time,transaction,description,amount) VALUES('1','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Petty_Available_Amount1.getText() + "','" + jtxt_Description.getText() + "','" + Double.parseDouble(jtxt_Petty_Available_Amount.getText().toString()) + "')");
                    JOptionPane.showMessageDialog(this, "Petty Amount Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                    jtxt_Petty_Transaction_Amount.setText(null);
                    jtxt_Description.setText(null);
                    Load_data();
                    Petty_Table_Loader_Data();
                }
            } catch (Exception e) {
                System.out.println("Error 2 .......");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!jtxt_Petty_Transaction_Amount.getText().isEmpty() && !jtxt_Description.getText().isEmpty()) {
            try {
                ResultSet rs = new Database_Connection.DB().getData("SELECT amount FROM petty_amount WHERE petty_id = '1'");
                while (rs.next()) {
                    double QTY = Double.parseDouble(rs.getString("amount").toString());
                    double UP = Double.parseDouble(jtxt_Petty_Transaction_Amount.getText().toString());
                    double tot = QTY - UP;
                    jtxt_Petty_Available_Amount.setText(new DecimalFormat("0.00").format(tot));

                    double UP1 = Double.parseDouble(jtxt_Petty_Transaction_Amount.getText().toString());
                    double tot1 = UP1;
                    jtxt_Petty_Available_Amount1.setText(new DecimalFormat("0.00").format("-" + tot1));

                    new Database_Connection.DB().putData("Update petty_amount Set amount = '" + tot + "' WHERE petty_id = '1' ");
                    new DB().putData("INSERT INTO petty_details (petty_id,date,time,transaction,description,amount) VALUES('1','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Petty_Available_Amount1.getText() + "','" + jtxt_Description.getText() + "','" + Double.parseDouble(jtxt_Petty_Available_Amount.getText().toString()) + "')");
                    JOptionPane.showMessageDialog(this, "Petty Amount Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                    jtxt_Petty_Transaction_Amount.setText(null);
                    jtxt_Description.setText(null);
                    Load_data();
                    Petty_Table_Loader_Data();
                }
            } catch (Exception e) {
                System.out.println("Error 2 .......");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxt_Petty_Transaction_Amount1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_Amount1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Petty_Transaction_Amount1KeyPressed

    private void jtxt_Petty_Transaction_Amount1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_Amount1KeyReleased
        Petty_Table_Loader_Data();
    }//GEN-LAST:event_jtxt_Petty_Transaction_Amount1KeyReleased

    private void jtxt_Petty_Transaction_Amount1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_Amount1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Petty_Transaction_Amount1KeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Closed Petty Cash')");
            Admin_Menu.P = null;
            this.dispose();
            System.gc();
        } catch (Exception ex) {
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Closed Petty Cash')");
            Admin_Menu.P = null;
            this.dispose();
            System.gc();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtxt_Petty_Transaction_AmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Petty_Transaction_AmountActionPerformed
        jtxt_Description.grabFocus();
    }//GEN-LAST:event_jtxt_Petty_Transaction_AmountActionPerformed

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
            java.util.logging.Logger.getLogger(Petty_Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Petty_Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Petty_Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Petty_Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Petty_Cash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxt_Description;
    private javax.swing.JLabel jtxt_Petty_Available_Amount;
    private javax.swing.JLabel jtxt_Petty_Available_Amount1;
    private javax.swing.JTextField jtxt_Petty_Transaction_Amount;
    private javax.swing.JTextField jtxt_Petty_Transaction_Amount1;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
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

    private void Load_data() {
        try {
            ResultSet rs = new DB().getData("select * from petty_amount where petty_id ='1'");
            if (rs.next()) {

                double CCredit = Double.parseDouble(rs.getString("amount").toString());
                jtxt_Petty_Available_Amount.setText(new DecimalFormat("0.00").format(CCredit));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Petty_Table_Loader_Data() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet set = new Database_Connection.DB().getData("SELECT * FROM petty_details where petty_details.date = '" + jtxt_Petty_Transaction_Amount1.getText() + "'");

            while (set.next()) {
                Vector v = new Vector();
                v.add(set.getString("date"));
                v.add(set.getString("time"));
                v.add(set.getString("transaction"));
                v.add(set.getString("description"));
                v.add(set.getString("amount"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
