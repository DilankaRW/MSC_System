/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database_Connection.DB;
import Interface.Backup_And_Restore;
import Interface.Credit_settle;
import Interface.Customer;
import Interface.Customer_Return;
import Interface.GRN;
import Interface.Income;
import Interface.Item;
import Interface.Login;
import Interface.Login_Details;
import Interface.Payments;
import Interface.Price_Update;
import Interface.Quotation;
import Interface.Reports;
import Interface.Supplier;
import Interface.User;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Dilanka
 */
public class User_Menu extends javax.swing.JFrame {
    
    static User_Petty_Cash C;
    static User_Help H;

    /**
     * Creates new form Admin_Menu
     */
    public User_Menu() {
        initComponents();
        setIcon();
        DateandTime();
    lbl_user_name.setText(Login.User_Name);
    
    ////////////////////////////////
    
    Action PrintAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Admin Invoice')");
                    new User_Main().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key = "print";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK), key);
        jPanel1.getActionMap().put(key, PrintAction);
        ////////////

        Action PrintAction1 = new AbstractAction("print1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Return')");
                    new User_Return().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key1 = "print1";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), key1);
        jPanel1.getActionMap().put(key1, PrintAction1);
        ////////////
        
        Action PrintAction7 = new AbstractAction("print7") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Quotation')");
                    new User_Quotation().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key7 = "print7";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), key7);
        jPanel1.getActionMap().put(key7, PrintAction7);
        
        //////////////

        Action PrintAction2 = new AbstractAction("print2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Report')");
                    new User_Quotation().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key2 = "print2";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK), key2);
        jPanel1.getActionMap().put(key2, PrintAction2);
        ////////////

        Action PrintAction3 = new AbstractAction("print3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Interface')");
                    new User_Customer().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key3 = "print3";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK), key3);
        jPanel1.getActionMap().put(key3, PrintAction3);
        ////////////

        Action PrintAction4 = new AbstractAction("print4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Credit Settle')");
                    new User_Credit_settle().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key4 = "print4";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.CTRL_DOWN_MASK), key4);
        jPanel1.getActionMap().put(key4, PrintAction4);
        ////////////

        Action PrintAction5 = new AbstractAction("print5") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Item')");
                    new User_Item().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key5 = "print5";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.CTRL_DOWN_MASK), key5);
        jPanel1.getActionMap().put(key5, PrintAction5);
        ////////////

        Action PrintAction6 = new AbstractAction("print6") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Calculator')");
                    new User_Report().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key6 = "print6";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.CTRL_DOWN_MASK), key6);
        jPanel1.getActionMap().put(key6, PrintAction6);

        ///////////////////////
        
        Action PrintAction8 = new AbstractAction("print8") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Item')");
                    new User_Calculator().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key8 = "print8";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK), key8);
        jPanel1.getActionMap().put(key8, PrintAction8);
        

    }
    
     public static String User_name = "User";
     
     private void System_dispose() {
        this.dispose();
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
        jbtn_Invoice = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbtn_Customer = new javax.swing.JButton();
        jbtn_Report = new javax.swing.JButton();
        jbtn_Credit_Settle = new javax.swing.JButton();
        jbtn_Item = new javax.swing.JButton();
        jbtn_Calculator = new javax.swing.JButton();
        jbtn_Return = new javax.swing.JButton();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        jbtn_Backup_and_Restore1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jbtn_Customer1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        jbtn_Invoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Invoice.png"))); // NOI18N
        jbtn_Invoice.setFocusTraversalPolicyProvider(true);
        jbtn_Invoice.setFocusable(false);
        jbtn_Invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_InvoiceActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MINO TOP LOGO.png"))); // NOI18N

        jbtn_Customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Customer.png"))); // NOI18N
        jbtn_Customer.setFocusTraversalPolicyProvider(true);
        jbtn_Customer.setFocusable(false);
        jbtn_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_CustomerActionPerformed(evt);
            }
        });

        jbtn_Report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Report.png"))); // NOI18N
        jbtn_Report.setFocusTraversalPolicyProvider(true);
        jbtn_Report.setFocusable(false);
        jbtn_Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ReportActionPerformed(evt);
            }
        });

        jbtn_Credit_Settle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Credit Settle.png"))); // NOI18N
        jbtn_Credit_Settle.setFocusTraversalPolicyProvider(true);
        jbtn_Credit_Settle.setFocusable(false);
        jbtn_Credit_Settle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Credit_SettleActionPerformed(evt);
            }
        });

        jbtn_Item.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/item.png"))); // NOI18N
        jbtn_Item.setFocusTraversalPolicyProvider(true);
        jbtn_Item.setFocusable(false);
        jbtn_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ItemActionPerformed(evt);
            }
        });

        jbtn_Calculator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Calculator.png"))); // NOI18N
        jbtn_Calculator.setFocusTraversalPolicyProvider(true);
        jbtn_Calculator.setFocusable(false);
        jbtn_Calculator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_CalculatorActionPerformed(evt);
            }
        });

        jbtn_Return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Return.png"))); // NOI18N
        jbtn_Return.setFocusTraversalPolicyProvider(true);
        jbtn_Return.setFocusable(false);
        jbtn_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ReturnActionPerformed(evt);
            }
        });

        lbl_Time.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Time.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Time.setText("Time");

        lbl_Date.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Date.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Date.setText("Date");

        lbl_user_name.setBackground(new java.awt.Color(255, 255, 255));
        lbl_user_name.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_user_name.setForeground(new java.awt.Color(255, 255, 255));
        lbl_user_name.setText("User Name");

        jbtn_Backup_and_Restore1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Pre Order.png"))); // NOI18N
        jbtn_Backup_and_Restore1.setFocusTraversalPolicyProvider(true);
        jbtn_Backup_and_Restore1.setFocusable(false);
        jbtn_Backup_and_Restore1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Backup_and_Restore1ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lock-38_1.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/info.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/question.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jbtn_Customer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Quotation.png"))); // NOI18N
        jbtn_Customer1.setFocusTraversalPolicyProvider(true);
        jbtn_Customer1.setFocusable(false);
        jbtn_Customer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Customer1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Petty Cash.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtn_Report)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Calculator))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_user_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_Time, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(128, 128, 128))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbtn_Invoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jbtn_Return)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtn_Backup_and_Restore1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtn_Customer1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(151, 151, 151))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jbtn_Customer)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtn_Credit_Settle)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtn_Item))))
                        .addContainerGap(51, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtn_Invoice)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtn_Return)
                            .addComponent(jbtn_Backup_and_Restore1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtn_Customer))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtn_Customer1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtn_Item)
                            .addComponent(jbtn_Credit_Settle))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtn_Calculator)
                    .addComponent(jbtn_Report))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_InvoiceActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Login User Invoice')");
            new User_Main().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_InvoiceActionPerformed

    private void jbtn_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_CustomerActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Interface')");
            new User_Customer().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_CustomerActionPerformed

    private void jbtn_ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ReportActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Report')");
            new User_Report().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ReportActionPerformed

    private void jbtn_Credit_SettleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Credit_SettleActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Credit Settle')");
            new User_Credit_settle().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Credit_SettleActionPerformed

    private void jbtn_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ItemActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Item')");
            new User_Item().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ItemActionPerformed

    private void jbtn_CalculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_CalculatorActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Calculator')");
            new User_Calculator().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_CalculatorActionPerformed

    private void jbtn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ReturnActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Return')");
            new User_Return().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ReturnActionPerformed

    private void jbtn_Backup_and_Restore1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Backup_and_Restore1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Quotation')");
            new User_Pre_Order().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Backup_and_Restore1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Login')");
            new Login().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Has been Exit from the System')");
            System.exit(0);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new User_About().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (H == null) {
            H = new User_Help();
            H.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jbtn_Customer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Customer1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Quotation')");
            new User_Quotation().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Customer1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Opened Petty Cash')");

        if (C == null) {
            C = new User_Petty_Cash();
            C.setVisible(true);
        }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtn_Backup_and_Restore1;
    private javax.swing.JButton jbtn_Calculator;
    private javax.swing.JButton jbtn_Credit_Settle;
    private javax.swing.JButton jbtn_Customer;
    private javax.swing.JButton jbtn_Customer1;
    private javax.swing.JButton jbtn_Invoice;
    private javax.swing.JButton jbtn_Item;
    private javax.swing.JButton jbtn_Report;
    private javax.swing.JButton jbtn_Return;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JLabel lbl_user_name;
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
