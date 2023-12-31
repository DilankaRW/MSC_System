/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database_Connection.DB;
import static Interface.Admin_Main.ca;
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
public class Admin_Menu extends javax.swing.JFrame {

    static Petty_Cash P;
    static Admin_Shop_Details S;
    static Help H;

    /**
     * Creates new form Admin_Menu
     */
    public Admin_Menu() {
        initComponents();
        setIcon();
        DateandTime();
        lbl_user_name.setText(Login.User_Name);

        ////////////
        Action PrintAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Admin Invoice')");
                    new Admin_Main().setVisible(true);
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
                    new Customer_Return().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key1 = "print1";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), key1);
        jPanel1.getActionMap().put(key1, PrintAction1);
        ////////////

        Action PrintAction2 = new AbstractAction("print2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Price Update')");
                    new Pre_Order().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key2 = "print2";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), key2);
        jPanel1.getActionMap().put(key2, PrintAction2);
        ////////////

        Action PrintAction3 = new AbstractAction("print3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Report')");
                    new Reports().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key3 = "print3";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK), key3);
        jPanel1.getActionMap().put(key3, PrintAction3);
        ////////////

        Action PrintAction4 = new AbstractAction("print4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Credit Settle')");
                    new Credit_settle().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key4 = "print4";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK), key4);
        jPanel1.getActionMap().put(key4, PrintAction4);
        ////////////

        Action PrintAction5 = new AbstractAction("print5") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Interface')");
                    new Customer().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key5 = "print5";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.CTRL_DOWN_MASK), key5);
        jPanel1.getActionMap().put(key5, PrintAction5);
        ////////////

        Action PrintAction6 = new AbstractAction("print6") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Supplier')");
                    new Supplier().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key6 = "print6";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.CTRL_DOWN_MASK), key6);
        jPanel1.getActionMap().put(key6, PrintAction6);
        ////////////

        Action PrintAction7 = new AbstractAction("print7") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Item')");
                    new Item().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key7 = "print7";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.CTRL_DOWN_MASK), key7);
        jPanel1.getActionMap().put(key7, PrintAction7);
        ////////////

        Action PrintAction8 = new AbstractAction("print8") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into GRN')");
                    new GRN().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key8 = "print8";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.CTRL_DOWN_MASK), key8);
        jPanel1.getActionMap().put(key8, PrintAction8);
        ////////////

        Action PrintAction9 = new AbstractAction("print9") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Income')");
                    new Income().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key9 = "print9";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_9, KeyEvent.CTRL_DOWN_MASK), key9);
        jPanel1.getActionMap().put(key9, PrintAction9);
        ////////////

        Action PrintAction10 = new AbstractAction("print10") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Payments')");
                    new Payments().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key10 = "print10";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK), key10);
        jPanel1.getActionMap().put(key10, PrintAction10);
        ////////////

        Action PrintAction11 = new AbstractAction("print11") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into User')");
                    new User().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key11 = "print11";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.CTRL_DOWN_MASK), key11);
        jPanel1.getActionMap().put(key11, PrintAction11);
        ////////////

        Action PrintAction12 = new AbstractAction("print12") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Calculator')");
                    new Admin_Calculator().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key12 = "print12";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.CTRL_DOWN_MASK), key12);
        jPanel1.getActionMap().put(key12, PrintAction12);
        ////////////

        Action PrintAction13 = new AbstractAction("print13") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Quotation')");
                    new Quotation().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key13 = "print13";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK), key13);
        jPanel1.getActionMap().put(key13, PrintAction13);
        ////////////

        Action PrintAction14 = new AbstractAction("print14") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Backup & Restore')");
                    new Price_Update().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key14 = "print14";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK), key14);
        jPanel1.getActionMap().put(key14, PrintAction14);
        ////////////

        Action PrintAction15 = new AbstractAction("print15") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Barcode Generator')");
                    new Shop_Return().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key15 = "print15";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, KeyEvent.CTRL_DOWN_MASK), key15);
        jPanel1.getActionMap().put(key15, PrintAction15);
        ////////////

        Action PrintAction16 = new AbstractAction("print16") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Login Details')");
                    new Backup_And_Restore().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key16 = "print16";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, KeyEvent.CTRL_DOWN_MASK), key16);
        jPanel1.getActionMap().put(key16, PrintAction16);

        ///////////////////
        Action PrintAction17 = new AbstractAction("print17") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Login Details')");
                    new Barcode_Generator().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key17 = "print17";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, KeyEvent.CTRL_DOWN_MASK), key17);
        jPanel1.getActionMap().put(key17, PrintAction17);

        /////////////////
        Action PrintAction18 = new AbstractAction("print18") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Login Details')");
                    new Login_Details().setVisible(true);
                    System_dispose();
                } catch (Exception ex) {
                }
            }
        };
        String key18 = "print18";
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, KeyEvent.CTRL_DOWN_MASK), key18);
        jPanel1.getActionMap().put(key18, PrintAction18);

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
        jbtn_GRN = new javax.swing.JButton();
        jbtn_Price_Update = new javax.swing.JButton();
        jbtn_Report = new javax.swing.JButton();
        jbtn_User = new javax.swing.JButton();
        jbtn_Supplier = new javax.swing.JButton();
        jbtn_Credit_Settle = new javax.swing.JButton();
        jbtn_Item = new javax.swing.JButton();
        jbtn_Payments = new javax.swing.JButton();
        jbtn_Calculator = new javax.swing.JButton();
        jbtn_Return = new javax.swing.JButton();
        jbtn_Income = new javax.swing.JButton();
        jbtn_Backup_and_Restore = new javax.swing.JButton();
        jbtn_Login_Details = new javax.swing.JButton();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        jbtn_Backup_and_Restore1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jbtn_Backup_and_Restore2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jbtn_Login_Details1 = new javax.swing.JButton();
        jbtn_Login_Details2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

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

        jbtn_GRN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/GRN.png"))); // NOI18N
        jbtn_GRN.setFocusTraversalPolicyProvider(true);
        jbtn_GRN.setFocusable(false);
        jbtn_GRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_GRNActionPerformed(evt);
            }
        });

        jbtn_Price_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Price Update.png"))); // NOI18N
        jbtn_Price_Update.setFocusTraversalPolicyProvider(true);
        jbtn_Price_Update.setFocusable(false);
        jbtn_Price_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Price_UpdateActionPerformed(evt);
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

        jbtn_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/user.png"))); // NOI18N
        jbtn_User.setFocusTraversalPolicyProvider(true);
        jbtn_User.setFocusable(false);
        jbtn_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_UserActionPerformed(evt);
            }
        });

        jbtn_Supplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Supplier.png"))); // NOI18N
        jbtn_Supplier.setFocusTraversalPolicyProvider(true);
        jbtn_Supplier.setFocusable(false);
        jbtn_Supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_SupplierActionPerformed(evt);
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

        jbtn_Payments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/payment.png"))); // NOI18N
        jbtn_Payments.setFocusTraversalPolicyProvider(true);
        jbtn_Payments.setFocusable(false);
        jbtn_Payments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_PaymentsActionPerformed(evt);
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

        jbtn_Income.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Income.png"))); // NOI18N
        jbtn_Income.setFocusTraversalPolicyProvider(true);
        jbtn_Income.setFocusable(false);
        jbtn_Income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_IncomeActionPerformed(evt);
            }
        });

        jbtn_Backup_and_Restore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Backup.png"))); // NOI18N
        jbtn_Backup_and_Restore.setFocusTraversalPolicyProvider(true);
        jbtn_Backup_and_Restore.setFocusable(false);
        jbtn_Backup_and_Restore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Backup_and_RestoreActionPerformed(evt);
            }
        });

        jbtn_Login_Details.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Login Details.png"))); // NOI18N
        jbtn_Login_Details.setFocusTraversalPolicyProvider(true);
        jbtn_Login_Details.setFocusable(false);
        jbtn_Login_Details.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Login_DetailsActionPerformed(evt);
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

        jbtn_Backup_and_Restore1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Quotation.png"))); // NOI18N
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

        jbtn_Backup_and_Restore2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Barcode.png"))); // NOI18N
        jbtn_Backup_and_Restore2.setFocusTraversalPolicyProvider(true);
        jbtn_Backup_and_Restore2.setFocusable(false);
        jbtn_Backup_and_Restore2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Backup_and_Restore2ActionPerformed(evt);
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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/settings.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jbtn_Login_Details1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Stock Return.png"))); // NOI18N
        jbtn_Login_Details1.setFocusTraversalPolicyProvider(true);
        jbtn_Login_Details1.setFocusable(false);
        jbtn_Login_Details1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Login_Details1ActionPerformed(evt);
            }
        });

        jbtn_Login_Details2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Pre Order.png"))); // NOI18N
        jbtn_Login_Details2.setFocusTraversalPolicyProvider(true);
        jbtn_Login_Details2.setFocusable(false);
        jbtn_Login_Details2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Login_Details2ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Petty Cash.png"))); // NOI18N
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(81, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtn_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtn_Return)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Login_Details2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Report)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Credit_Settle, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jbtn_Income, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtn_Customer))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jbtn_Supplier)
                                            .addGap(18, 18, 18)
                                            .addComponent(jbtn_Item)
                                            .addGap(18, 18, 18)
                                            .addComponent(jbtn_GRN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jbtn_Payments)
                                            .addGap(18, 18, 18)
                                            .addComponent(jbtn_User)
                                            .addGap(18, 18, 18)
                                            .addComponent(jbtn_Calculator))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jbtn_Backup_and_Restore1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl_Time)
                                        .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jbtn_Backup_and_Restore2)
                                        .addComponent(jbtn_Price_Update))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jbtn_Login_Details1)
                                            .addGap(18, 18, 18)
                                            .addComponent(jbtn_Backup_and_Restore))
                                        .addComponent(jbtn_Login_Details, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(215, 215, 215)))
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jbtn_Invoice)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jbtn_Report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtn_Return, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtn_Credit_Settle))
                                    .addComponent(jbtn_Login_Details2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jbtn_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbtn_Customer))
                                    .addComponent(jbtn_GRN, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtn_Item, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jbtn_Payments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtn_Income)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jbtn_User)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addComponent(jbtn_Calculator))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtn_Backup_and_Restore1)
                                    .addComponent(jbtn_Login_Details1)
                                    .addComponent(jbtn_Backup_and_Restore)))
                            .addComponent(jbtn_Price_Update))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbtn_Backup_and_Restore2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtn_Login_Details, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_InvoiceActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Admin Invoice')");
            new Admin_Main().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_InvoiceActionPerformed

    private void jbtn_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_CustomerActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Interface')");
            new Customer().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_CustomerActionPerformed

    private void jbtn_GRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_GRNActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into GRN')");
            new GRN().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_GRNActionPerformed

    private void jbtn_Price_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Price_UpdateActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Price Update')");
            new Price_Update().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jbtn_Price_UpdateActionPerformed

    private void jbtn_ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ReportActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Report')");
            new Reports().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ReportActionPerformed

    private void jbtn_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_UserActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into User')");
            new User().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_UserActionPerformed

    private void jbtn_SupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_SupplierActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Supplier')");
            new Supplier().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_SupplierActionPerformed

    private void jbtn_Credit_SettleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Credit_SettleActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Credit Settle')");
            new Credit_settle().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Credit_SettleActionPerformed

    private void jbtn_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ItemActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Item')");
            new Item().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ItemActionPerformed

    private void jbtn_PaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_PaymentsActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Payments')");
            new Payments().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_PaymentsActionPerformed

    private void jbtn_CalculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_CalculatorActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Calculator')");
            new Admin_Calculator().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_CalculatorActionPerformed

    private void jbtn_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ReturnActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Customer Return')");
            new Customer_Return().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_ReturnActionPerformed

    private void jbtn_IncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_IncomeActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Income')");
            new Income().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_IncomeActionPerformed

    private void jbtn_Backup_and_RestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Backup_and_RestoreActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Backup & Restore')");
            new Backup_And_Restore().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Backup_and_RestoreActionPerformed

    private void jbtn_Login_DetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Login_DetailsActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Login Details')");
            new Login_Details().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }

    }//GEN-LAST:event_jbtn_Login_DetailsActionPerformed

    private void jbtn_Backup_and_Restore1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Backup_and_Restore1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into Quotation')");
            new Quotation().setVisible(true);
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
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Has been Exit from the System')");
            System.exit(0);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtn_Backup_and_Restore2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Backup_and_Restore2ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Barcode Generator')");
            new Barcode_Generator().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Backup_and_Restore2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new About().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (H == null) {
            H = new Help();
            H.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into to Settings')");
            if (S == null) {
                S = new Admin_Shop_Details();
                S.setVisible(true);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jbtn_Login_Details1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Login_Details1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into to Stock Return')");
            new Shop_Return().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Login_Details1ActionPerformed

    private void jbtn_Login_Details2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Login_Details2ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Logged into to Pre Order')");
            new Pre_Order().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jbtn_Login_Details2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + lbl_user_name.getText() + "','" + lbl_Date.getText() + "','" + lbl_Time.getText() + "','Opened Petty Cash')");
            if (P == null) {
                P = new Petty_Cash();
                P.setVisible(true);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Admin_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtn_Backup_and_Restore;
    private javax.swing.JButton jbtn_Backup_and_Restore1;
    private javax.swing.JButton jbtn_Backup_and_Restore2;
    private javax.swing.JButton jbtn_Calculator;
    private javax.swing.JButton jbtn_Credit_Settle;
    private javax.swing.JButton jbtn_Customer;
    private javax.swing.JButton jbtn_GRN;
    private javax.swing.JButton jbtn_Income;
    private javax.swing.JButton jbtn_Invoice;
    private javax.swing.JButton jbtn_Item;
    private javax.swing.JButton jbtn_Login_Details;
    private javax.swing.JButton jbtn_Login_Details1;
    private javax.swing.JButton jbtn_Login_Details2;
    private javax.swing.JButton jbtn_Payments;
    private javax.swing.JButton jbtn_Price_Update;
    private javax.swing.JButton jbtn_Report;
    private javax.swing.JButton jbtn_Return;
    private javax.swing.JButton jbtn_Supplier;
    private javax.swing.JButton jbtn_User;
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
//Done
