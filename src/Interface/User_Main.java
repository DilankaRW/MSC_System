/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Database_Connection.DB;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author User
 */
public class User_Main extends javax.swing.JFrame {

    private String gen;
    private String pay;

    static Customer_Add ca;
    static Short_Bill sb;

    double invtot = 0.0;
    double grntot = 0.0;
    double credit = 0.0;
    double customer_credits = 0.0;
    public String c;

    String fromE;
    String toE;
    String pwE;
    String subE;
    String mesE;
    String file;
    String sender;

    private boolean zerodisp;
    private boolean decdisp;
    private boolean dgrrad;
    private boolean sh;

    private byte op;

    private double ina;
    private double inb;
    private double out;

    /**
     * Creates new form Admin_Main
     */
    public User_Main() {
        initComponents();
        DateandTime();
        setIcon();
        lbl_user_name.setText(Login.User_Name);
        Invoice_ID_Auto_Loader();
        Item_ID_Auto_Loader();
        Customer_ID_Auto_Loader();
        Invoice_Item_Name_Autocomplete();
        Customer_Table_Loader_Data();
        Item_Table_Loader_Data();
        Auto_Total_Calculate();
        Shop_Credit_Table_Loader_Data();
        Customer_Credit_Table_Loader_Data();
        jtbl_Invoice_GRN.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jtbl_Invoice_Item.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jtbl_Item.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jtbl_Customer.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        Jtbl_Credit_Settle_Shop.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        Jtbl_Credit_Settle_Customer.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        lbl_Date2.setText(date);
        
        income();
        Customer_Credit();
        Chart();
        Report_Loader();

        check_box();

        Action PrintAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Invoice_Cash.grabFocus();
            }
        };
        String key = "print";
        jTabbedPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), key);
        jTabbedPane2.getActionMap().put(key, PrintAction);

        //////////////
        Action PrintAction1 = new AbstractAction("print1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Invoice_Barcode.grabFocus();
            }
        };
        String key1 = "print1";
        jTabbedPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), key1);
        jTabbedPane2.getActionMap().put(key1, PrintAction1);

        //////////////
        Action PrintAction2 = new AbstractAction("print2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Net_Discount.grabFocus();
            }
        };
        String key2 = "print2";
        jTabbedPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), key2);
        jTabbedPane2.getActionMap().put(key2, PrintAction2);

        //////////////
        Action PrintAction3 = new AbstractAction("print3") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear_Invoice();
            }
        };
        String key3 = "print3";
        jTabbedPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), key3);
        jTabbedPane2.getActionMap().put(key3, PrintAction3);

        //////////////
        Action PrintAction4 = new AbstractAction("print4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Customer_Return().setVisible(true);
                System_dispose();
            }
        };
        String key4 = "print4";
        jTabbedPane2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), key4);
        jTabbedPane2.getActionMap().put(key4, PrintAction4);

    }

    public static String User_name = "User";
    public static String User_Type = "Type";

    private static int getResultSetRowCount(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
            return 0;
        }
        return size;
    }

    public void Invoice_Item_Name_Autocomplete() {
        TextAutoCompleter ac = new TextAutoCompleter(jtxt_Invoice_Item_Name);
        try {
            ResultSet rs = new DB().getData("SELECT item_name FROM item;");
            while (rs.next()) {
                ac.addItem(rs.getString("item_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Time1 = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        lbl_Date1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxt_Invoice_Discount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_Invoice_Item = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jtxt_Invoice_Barcode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtxt_Net_Discount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_Invoice_GRN = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jtxt_Invoice_Qty = new javax.swing.JTextField();
        jtxt_Invoice_Cash = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jlbl_grn_supplier_id = new javax.swing.JLabel();
        jlbl_grn_id = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jbtn_Invoice_Pay = new javax.swing.JButton();
        jbtn_Clear_One = new javax.swing.JButton();
        jbtn_Clear_All = new javax.swing.JButton();
        jbtn_ADD_New_Customer = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jlbl_Invoice_No = new javax.swing.JLabel();
        jlbl_Invoice_Customer_ID = new javax.swing.JLabel();
        jlbl_Gross_Total = new javax.swing.JLabel();
        jtxt_Net_Total = new javax.swing.JLabel();
        jlbl_Invoice_Balance = new javax.swing.JLabel();
        customer_credit = new javax.swing.JRadioButton();
        customer_cash = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        sub_total_Number_Discount = new javax.swing.JRadioButton();
        sub_total_Precentage_Discount = new javax.swing.JRadioButton();
        jlbl_Invoice_No1 = new javax.swing.JLabel();
        jlbl_Net_Discount = new javax.swing.JLabel();
        Discount_Margin_Supplier = new javax.swing.JLabel();
        Discount_Margin = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jlbl_Invoice_Gross_Total3 = new javax.swing.JLabel();
        jtxt_Invoice_Customer_Name1 = new javax.swing.JTextField();
        jtxt_Invoice_Item_Name = new javax.swing.JTextField();
        jtxt_Invoice_Item_ID = new javax.swing.JTextField();
        jlbl_Invoice_Customer_ID1 = new javax.swing.JLabel();
        jtxt_Invoice_Selling_Price = new javax.swing.JLabel();
        jtxt_Invoice_Gross_Total = new javax.swing.JLabel();
        jlbl_Customer_ID_Check = new javax.swing.JLabel();
        jlbl_Invoice_Customer_ID2 = new javax.swing.JLabel();
        jbtn_Clear_All1 = new javax.swing.JButton();
        jbtn_Clear_One1 = new javax.swing.JButton();
        jtxt_Invoice_Customer_Name = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jCmb_Item_Status = new javax.swing.JComboBox();
        jbtn_Item_Save = new javax.swing.JButton();
        jtxt_Item_barcode = new javax.swing.JTextField();
        jtxt_Item_Name = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtbl_Item = new javax.swing.JTable();
        jtxt_Item_Search = new javax.swing.JTextField();
        jbtn_Item_Clear = new javax.swing.JButton();
        jbtn_Item_Update = new javax.swing.JButton();
        jlbl_Item_Item_ID = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtbl_Customer = new javax.swing.JTable();
        jtxt_Customer_Search = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jbtn_ADD_Customer_Clear = new javax.swing.JButton();
        jbtn_ADD_Customer_update = new javax.swing.JButton();
        jbtn_ADD_Customer_Save = new javax.swing.JButton();
        jCmb_Customer_Status = new javax.swing.JComboBox();
        jtxt_Customer_Address = new javax.swing.JTextField();
        jtxt_Customer__Email = new javax.swing.JTextField();
        jtxt_Customer_Phone_number = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jlbl_Customer_ID = new javax.swing.JLabel();
        jtxt_Customer_Name = new javax.swing.JTextField();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jbtn_GRN_Print_GRN2 = new javax.swing.JButton();
        jlbl_Income_Full_Amount = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jlbl_income = new javax.swing.JLabel();
        jlbl_Customer_Credit = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        lbl_Date2 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        lbl_Report_ID = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_Pay_Price = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        Jbtn_Credit_Settle_Shop_Pay = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        Jtbl_Credit_Settle_Shop = new javax.swing.JTable();
        jtxt_Credit_Settle_Shop_GRN_ID = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_GRN_Date = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_Supplier_Name = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_Supplier_ID = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_Credit_Price = new javax.swing.JLabel();
        jtxt_Credit_Settle_Shop_Ballence_Credit = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_Invoice_ID = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_Date = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_ID = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_Customer_Name = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_Credit_Price = new javax.swing.JLabel();
        jtxt_Credit_Settle_Customer_Pay_Price = new javax.swing.JTextField();
        jtxt_Credit_Settle_Customer_Ballence_Credit = new javax.swing.JLabel();
        Jbtn_Credit_Settle_Customer_Pay = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        Jtbl_Credit_Settle_Customer = new javax.swing.JTable();
        jtxt_Credit_Settle_Customer_Contact_Number = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        zero = new javax.swing.JButton();
        equals = new javax.swing.JButton();
        decpoint = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        divide = new javax.swing.JButton();
        multiply = new javax.swing.JButton();
        subtract = new javax.swing.JButton();
        add = new javax.swing.JButton();
        three = new javax.swing.JButton();
        two = new javax.swing.JButton();
        one = new javax.swing.JButton();
        six = new javax.swing.JButton();
        nine = new javax.swing.JButton();
        eight = new javax.swing.JButton();
        five = new javax.swing.JButton();
        four = new javax.swing.JButton();
        seven = new javax.swing.JButton();
        percent = new javax.swing.JButton();
        display1 = new javax.swing.JTextField();
        display2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MINO TOP LOGO.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Home-38.png"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lock-38.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton3KeyReleased(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/minimize-38.png"))); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lbl_Time.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Time.setText("Time");

        lbl_Date.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date.setText("Date");

        lbl_Time1.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        lbl_Time1.setForeground(new java.awt.Color(0, 153, 255));
        lbl_Time1.setText("Loged User :");

        lbl_user_name.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        lbl_user_name.setForeground(new java.awt.Color(0, 153, 255));
        lbl_user_name.setText("User Name");

        lbl_Date1.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Date1.setText("Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_Date1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Time1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_Time)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Date)
                            .addComponent(lbl_Date1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Time1)
                    .addComponent(lbl_user_name))
                .addGap(30, 30, 30))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFocusable(false);
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseEntered(evt);
            }
        });
        jTabbedPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane2KeyPressed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel3.setText("Item Name");

        jLabel5.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel5.setText("Qty.");

        jtxt_Invoice_Discount.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Invoice_DiscountActionPerformed(evt);
            }
        });
        jtxt_Invoice_Discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_DiscountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_DiscountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_DiscountKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel6.setText("Discount");

        jtbl_Invoice_Item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtbl_Invoice_Item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "GRN ID", "S. ID", "Price", "Qty", "Discount", "Item Gross Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbl_Invoice_Item.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtbl_Invoice_Item.setFocusable(false);
        jtbl_Invoice_Item.setRowHeight(20);
        jtbl_Invoice_Item.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtbl_Invoice_Item);
        if (jtbl_Invoice_Item.getColumnModel().getColumnCount() > 0) {
            jtbl_Invoice_Item.getColumnModel().getColumn(0).setMinWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(0).setPreferredWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(0).setMaxWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(2).setMinWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(2).setPreferredWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(2).setMaxWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(3).setMinWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(3).setPreferredWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(3).setMaxWidth(0);
            jtbl_Invoice_Item.getColumnModel().getColumn(4).setMinWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(4).setPreferredWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(4).setMaxWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(5).setMinWidth(100);
            jtbl_Invoice_Item.getColumnModel().getColumn(5).setPreferredWidth(100);
            jtbl_Invoice_Item.getColumnModel().getColumn(5).setMaxWidth(100);
            jtbl_Invoice_Item.getColumnModel().getColumn(6).setMinWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(6).setPreferredWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(6).setMaxWidth(120);
            jtbl_Invoice_Item.getColumnModel().getColumn(7).setMinWidth(210);
            jtbl_Invoice_Item.getColumnModel().getColumn(7).setPreferredWidth(210);
            jtbl_Invoice_Item.getColumnModel().getColumn(7).setMaxWidth(210);
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("Gross Total");

        jtxt_Invoice_Barcode.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_BarcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_BarcodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_BarcodeKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel8.setText("Barcode");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Discount");

        jtxt_Net_Discount.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jtxt_Net_Discount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_Net_Discount.setText("0");
        jtxt_Net_Discount.setToolTipText("");
        jtxt_Net_Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Net_DiscountActionPerformed(evt);
            }
        });
        jtxt_Net_Discount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Net_DiscountKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Net. Total");

        jtbl_Invoice_GRN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtbl_Invoice_GRN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRN ID", "Item ID", "Supplier", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbl_Invoice_GRN.setToolTipText("");
        jtbl_Invoice_GRN.setFocusable(false);
        jtbl_Invoice_GRN.setRowHeight(20);
        jtbl_Invoice_GRN.getTableHeader().setReorderingAllowed(false);
        jtbl_Invoice_GRN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbl_Invoice_GRNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtbl_Invoice_GRN);
        if (jtbl_Invoice_GRN.getColumnModel().getColumnCount() > 0) {
            jtbl_Invoice_GRN.getColumnModel().getColumn(0).setMinWidth(100);
            jtbl_Invoice_GRN.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtbl_Invoice_GRN.getColumnModel().getColumn(0).setMaxWidth(100);
            jtbl_Invoice_GRN.getColumnModel().getColumn(1).setMinWidth(0);
            jtbl_Invoice_GRN.getColumnModel().getColumn(1).setPreferredWidth(0);
            jtbl_Invoice_GRN.getColumnModel().getColumn(1).setMaxWidth(0);
            jtbl_Invoice_GRN.getColumnModel().getColumn(3).setMinWidth(100);
            jtbl_Invoice_GRN.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtbl_Invoice_GRN.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabel46.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel46.setText("Inv. No");

        jLabel47.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel47.setText("Unit Price");

        jtxt_Invoice_Qty.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Invoice_QtyActionPerformed(evt);
            }
        });
        jtxt_Invoice_Qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_QtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_QtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_QtyKeyTyped(evt);
            }
        });

        jtxt_Invoice_Cash.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jtxt_Invoice_Cash.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_Invoice_Cash.setToolTipText("");
        jtxt_Invoice_Cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Invoice_CashActionPerformed(evt);
            }
        });
        jtxt_Invoice_Cash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_CashKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setText("Balance");

        jlbl_grn_supplier_id.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_grn_supplier_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbl_grn_supplier_id.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_grn_id.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_grn_id.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbl_grn_id.setForeground(new java.awt.Color(255, 255, 255));

        jLabel48.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel48.setText("Total");

        jbtn_Invoice_Pay.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jbtn_Invoice_Pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cash pay.png"))); // NOI18N
        jbtn_Invoice_Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Invoice_PayActionPerformed(evt);
            }
        });

        jbtn_Clear_One.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clear select row_30px.png"))); // NOI18N
        jbtn_Clear_One.setFocusable(false);
        jbtn_Clear_One.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Clear_OneActionPerformed(evt);
            }
        });

        jbtn_Clear_All.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clear all rows_30px.png"))); // NOI18N
        jbtn_Clear_All.setFocusable(false);
        jbtn_Clear_All.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Clear_AllActionPerformed(evt);
            }
        });

        jbtn_ADD_New_Customer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add-customer-24.png"))); // NOI18N
        jbtn_ADD_New_Customer.setFocusable(false);
        jbtn_ADD_New_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ADD_New_CustomerActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/return-purchase-30.png"))); // NOI18N
        jButton8.setText("Return");
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jlbl_Invoice_No.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbl_Invoice_Customer_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_Customer_ID.setText("1000");
        jlbl_Invoice_Customer_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbl_Gross_Total.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Gross_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_Gross_Total.setToolTipText("");
        jlbl_Gross_Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Net_Total.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Net_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxt_Net_Total.setToolTipText("");
        jtxt_Net_Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbl_Invoice_Balance.setFont(new java.awt.Font("Euphemia", 0, 24)); // NOI18N
        jlbl_Invoice_Balance.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_Invoice_Balance.setToolTipText("");
        jlbl_Invoice_Balance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        buttonGroup3.add(customer_credit);
        customer_credit.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customer_credit.setText("Credit");
        customer_credit.setContentAreaFilled(false);
        customer_credit.setFocusable(false);
        customer_credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_creditActionPerformed(evt);
            }
        });

        buttonGroup3.add(customer_cash);
        customer_cash.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customer_cash.setSelected(true);
        customer_cash.setText("Cash");
        customer_cash.setContentAreaFilled(false);
        customer_cash.setFocusable(false);
        customer_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_cashActionPerformed(evt);
            }
        });

        buttonGroup4.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("%");
        jRadioButton1.setContentAreaFilled(false);
        jRadioButton1.setFocusable(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup4.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("0.0");
        jRadioButton2.setContentAreaFilled(false);
        jRadioButton2.setFocusable(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup5.add(sub_total_Number_Discount);
        sub_total_Number_Discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sub_total_Number_Discount.setText("0.0");
        sub_total_Number_Discount.setContentAreaFilled(false);
        sub_total_Number_Discount.setFocusable(false);
        sub_total_Number_Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_total_Number_DiscountActionPerformed(evt);
            }
        });

        buttonGroup5.add(sub_total_Precentage_Discount);
        sub_total_Precentage_Discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sub_total_Precentage_Discount.setSelected(true);
        sub_total_Precentage_Discount.setText("%");
        sub_total_Precentage_Discount.setContentAreaFilled(false);
        sub_total_Precentage_Discount.setFocusable(false);
        sub_total_Precentage_Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_total_Precentage_DiscountActionPerformed(evt);
            }
        });

        jlbl_Invoice_No1.setFont(new java.awt.Font("Euphemia", 0, 8)); // NOI18N
        jlbl_Invoice_No1.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_Net_Discount.setFont(new java.awt.Font("Euphemia", 0, 8)); // NOI18N
        jlbl_Net_Discount.setForeground(new java.awt.Color(255, 255, 255));

        Discount_Margin_Supplier.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Discount_Margin_Supplier.setForeground(new java.awt.Color(153, 153, 153));
        Discount_Margin_Supplier.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        Discount_Margin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Discount_Margin.setForeground(new java.awt.Color(153, 153, 153));

        jCheckBox1.setText("GRN Discount");
        jCheckBox1.setFocusable(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jlbl_Invoice_Gross_Total3.setFont(new java.awt.Font("Euphemia", 0, 8)); // NOI18N
        jlbl_Invoice_Gross_Total3.setForeground(new java.awt.Color(255, 255, 255));

        jtxt_Invoice_Customer_Name1.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Customer_Name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Invoice_Customer_Name1ActionPerformed(evt);
            }
        });
        jtxt_Invoice_Customer_Name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Customer_Name1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Customer_Name1KeyTyped(evt);
            }
        });

        jtxt_Invoice_Item_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Item_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_NameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_NameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_NameKeyTyped(evt);
            }
        });

        jtxt_Invoice_Item_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Item_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_IDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_IDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Item_IDKeyTyped(evt);
            }
        });

        jlbl_Invoice_Customer_ID1.setFont(new java.awt.Font("Euphemia", 0, 8)); // NOI18N
        jlbl_Invoice_Customer_ID1.setForeground(java.awt.Color.white);
        jlbl_Invoice_Customer_ID1.setText("0");

        jtxt_Invoice_Selling_Price.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Selling_Price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Invoice_Gross_Total.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Gross_Total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbl_Customer_ID_Check.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_Customer_ID_Check.setFont(new java.awt.Font("Euphemia", 0, 8)); // NOI18N
        jlbl_Customer_ID_Check.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_Customer_ID_Check.setText("1000");

        jlbl_Invoice_Customer_ID2.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_Customer_ID2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Phone Number.png"))); // NOI18N

        jbtn_Clear_All1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clear all rows_30px.png"))); // NOI18N
        jbtn_Clear_All1.setFocusable(false);
        jbtn_Clear_All1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Clear_All1ActionPerformed(evt);
            }
        });

        jbtn_Clear_One1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/clear select row_30px.png"))); // NOI18N
        jbtn_Clear_One1.setFocusable(false);
        jbtn_Clear_One1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Clear_One1ActionPerformed(evt);
            }
        });

        jtxt_Invoice_Customer_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Invoice_Customer_Name.setText("Danuma Customer");
        jtxt_Invoice_Customer_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Short_Bill.png"))); // NOI18N
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jtxt_Invoice_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jtxt_Invoice_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Discount_Margin_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Discount_Margin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jtxt_Invoice_Qty, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(338, 338, 338))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jtxt_Invoice_Selling_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel46))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(28, 28, 28)))
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jtxt_Invoice_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jlbl_Invoice_No, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jtxt_Invoice_Item_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jtxt_Invoice_Item_ID))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(245, 245, 245)
                                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jRadioButton1)
                                                .addGap(18, 18, 18)))
                                        .addGap(139, 139, 139)))
                                .addComponent(jCheckBox1)
                                .addGap(571, 571, 571)
                                .addComponent(jlbl_grn_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_grn_supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(432, 432, 432)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtn_Clear_All1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtn_Clear_One1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtn_Clear_All, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtn_Clear_One, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jtxt_Invoice_Customer_Name1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                                .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jlbl_Invoice_Customer_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                    .addComponent(jlbl_Invoice_Customer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jbtn_ADD_New_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(sub_total_Number_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(sub_total_Precentage_Discount)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel9)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jtxt_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGap(412, 412, 412)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jlbl_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jButton8)
                                                .addGap(14, 14, 14)
                                                .addComponent(jlbl_Customer_ID_Check, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlbl_Invoice_Customer_ID1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jlbl_Invoice_No1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlbl_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jlbl_Invoice_Gross_Total3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(customer_credit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(customer_cash)
                                                .addGap(10, 10, 10)
                                                .addComponent(jtxt_Invoice_Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jtxt_Net_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addGap(10, 10, 10)
                                                .addComponent(jlbl_Invoice_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtn_Invoice_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbl_Invoice_No, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxt_Invoice_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_Invoice_Item_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_Invoice_Item_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_Invoice_Selling_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxt_Invoice_Qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_Invoice_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Discount_Margin_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Discount_Margin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Invoice_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jlbl_grn_supplier_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                .addComponent(jlbl_grn_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jbtn_Clear_All1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtn_Clear_One1))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jbtn_Clear_All)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtn_Clear_One)))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbtn_ADD_New_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbl_Invoice_Customer_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                    .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(sub_total_Precentage_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbl_Invoice_Customer_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Invoice_Customer_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton8)
                                            .addComponent(jlbl_Invoice_Customer_ID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlbl_Customer_ID_Check, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbl_Net_Discount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbl_Invoice_Gross_Total3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbl_Invoice_No1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtxt_Net_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customer_credit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(customer_cash))
                                    .addComponent(jtxt_Invoice_Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlbl_Invoice_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(109, 109, 109))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jbtn_Invoice_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbl_Gross_Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtxt_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addComponent(sub_total_Number_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User Invoice.png")), jPanel7); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
        });
        jPanel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel8KeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel18.setText("Item ID");

        jLabel19.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel19.setText("Barcode");

        jLabel20.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel20.setText("Name");

        jLabel23.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel23.setText("Search");

        jCmb_Item_Status.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jCmb_Item_Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Deactive" }));
        jCmb_Item_Status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCmb_Item_StatusKeyPressed(evt);
            }
        });

        jbtn_Item_Save.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_Item_Save.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_Item_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save.png"))); // NOI18N
        jbtn_Item_Save.setFocusable(false);
        jbtn_Item_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Item_SaveActionPerformed(evt);
            }
        });

        jtxt_Item_barcode.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Item_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Item_barcodeActionPerformed(evt);
            }
        });
        jtxt_Item_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Item_barcodeKeyReleased(evt);
            }
        });

        jtxt_Item_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Item_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Item_NameActionPerformed(evt);
            }
        });

        jtbl_Item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtbl_Item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Barcode", "Item Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbl_Item.setFocusable(false);
        jtbl_Item.setRowHeight(20);
        jtbl_Item.getTableHeader().setReorderingAllowed(false);
        jtbl_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbl_ItemMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtbl_Item);
        if (jtbl_Item.getColumnModel().getColumnCount() > 0) {
            jtbl_Item.getColumnModel().getColumn(0).setMinWidth(100);
            jtbl_Item.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtbl_Item.getColumnModel().getColumn(0).setMaxWidth(100);
            jtbl_Item.getColumnModel().getColumn(1).setMinWidth(150);
            jtbl_Item.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtbl_Item.getColumnModel().getColumn(1).setMaxWidth(150);
            jtbl_Item.getColumnModel().getColumn(3).setMinWidth(70);
            jtbl_Item.getColumnModel().getColumn(3).setPreferredWidth(70);
            jtbl_Item.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        jtxt_Item_Search.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Item_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Item_SearchKeyReleased(evt);
            }
        });

        jbtn_Item_Clear.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_Item_Clear.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_Item_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Clear.png"))); // NOI18N
        jbtn_Item_Clear.setFocusable(false);
        jbtn_Item_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Item_ClearActionPerformed(evt);
            }
        });

        jbtn_Item_Update.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_Item_Update.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_Item_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Update.png"))); // NOI18N
        jbtn_Item_Update.setFocusable(false);
        jbtn_Item_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Item_UpdateActionPerformed(evt);
            }
        });

        jlbl_Item_Item_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Item_Item_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jlbl_Item_Item_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jbtn_Item_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(jbtn_Item_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jbtn_Item_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtxt_Item_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jtxt_Item_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtxt_Item_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCmb_Item_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jlbl_Item_Item_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jtxt_Item_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jtxt_Item_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCmb_Item_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbtn_Item_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_Item_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtn_Item_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtxt_Item_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User item.png")), jPanel8); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jtbl_Customer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtbl_Customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Gender", "Phone Number", "Email", "Address", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbl_Customer.setRowHeight(20);
        jtbl_Customer.getTableHeader().setReorderingAllowed(false);
        jtbl_Customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbl_Customerjtbl_ADD_CustomerMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jtbl_Customer);
        if (jtbl_Customer.getColumnModel().getColumnCount() > 0) {
            jtbl_Customer.getColumnModel().getColumn(0).setMinWidth(100);
            jtbl_Customer.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtbl_Customer.getColumnModel().getColumn(0).setMaxWidth(100);
            jtbl_Customer.getColumnModel().getColumn(2).setMinWidth(80);
            jtbl_Customer.getColumnModel().getColumn(2).setPreferredWidth(80);
            jtbl_Customer.getColumnModel().getColumn(2).setMaxWidth(80);
            jtbl_Customer.getColumnModel().getColumn(3).setMinWidth(130);
            jtbl_Customer.getColumnModel().getColumn(3).setPreferredWidth(130);
            jtbl_Customer.getColumnModel().getColumn(3).setMaxWidth(130);
            jtbl_Customer.getColumnModel().getColumn(6).setMinWidth(100);
            jtbl_Customer.getColumnModel().getColumn(6).setPreferredWidth(100);
            jtbl_Customer.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jtxt_Customer_Search.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Customer_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Customer_Searchjtxt_ADD_Customer_SearchKeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel61.setText("Search");

        jbtn_ADD_Customer_Clear.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_ADD_Customer_Clear.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_ADD_Customer_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Clear.png"))); // NOI18N
        jbtn_ADD_Customer_Clear.setFocusable(false);
        jbtn_ADD_Customer_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ADD_Customer_Clearjbtn_ADD_Customer_ClearActionPerformed(evt);
            }
        });

        jbtn_ADD_Customer_update.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_ADD_Customer_update.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_ADD_Customer_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Update.png"))); // NOI18N
        jbtn_ADD_Customer_update.setFocusable(false);
        jbtn_ADD_Customer_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ADD_Customer_updatejbtn_ADD_Customer_updateActionPerformed(evt);
            }
        });

        jbtn_ADD_Customer_Save.setBackground(new java.awt.Color(255, 255, 255));
        jbtn_ADD_Customer_Save.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_ADD_Customer_Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save.png"))); // NOI18N
        jbtn_ADD_Customer_Save.setFocusable(false);
        jbtn_ADD_Customer_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_ADD_Customer_Savejbtn_ADD_Customer_SaveActionPerformed(evt);
            }
        });

        jCmb_Customer_Status.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jCmb_Customer_Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Deactive" }));
        jCmb_Customer_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmb_Customer_StatusActionPerformed(evt);
            }
        });
        jCmb_Customer_Status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCmb_Customer_StatusKeyPressed(evt);
            }
        });

        jtxt_Customer_Address.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Customer_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Customer_Addressjtxt_ADD_Customer_AddressActionPerformed(evt);
            }
        });

        jtxt_Customer__Email.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Customer__Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Customer__Emailjtxt_ADD_Customer_EmailActionPerformed(evt);
            }
        });

        jtxt_Customer_Phone_number.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Customer_Phone_number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Customer_Phone_numberjtxt_ADD_Customer_Phone_numberActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel62.setText("Phone Number");

        jLabel90.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel90.setText("Email");

        jLabel91.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel91.setText("Address");

        jLabel92.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel92.setText("Customer ID");

        jlbl_Customer_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Customer_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Customer_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Customer_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Customer_Namejtxt_ADD_Customer_NameActionPerformed(evt);
            }
        });

        female.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        female.setText("Female");
        female.setContentAreaFilled(false);
        female.setFocusable(false);
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femalefemaleActionPerformed(evt);
            }
        });

        male.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        male.setSelected(true);
        male.setText("Male");
        male.setContentAreaFilled(false);
        male.setFocusable(false);
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                malemaleActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel93.setText("Gender");

        jLabel94.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel94.setText("Name");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93)
                            .addComponent(jLabel92)
                            .addComponent(jLabel94))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_Customer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(male)
                                .addGap(18, 18, 18)
                                .addComponent(female))
                            .addComponent(jtxt_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addComponent(jLabel91)
                            .addComponent(jLabel62))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxt_Customer_Phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtxt_Customer_Address, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jCmb_Customer_Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtn_ADD_Customer_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtxt_Customer__Email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtn_ADD_Customer_update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtn_ADD_Customer_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addGap(18, 18, 18)
                            .addComponent(jtxt_Customer_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbl_Customer_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxt_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(female))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxt_Customer_Phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxt_Customer__Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel91)
                    .addComponent(jtxt_Customer_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtn_ADD_Customer_Clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_ADD_Customer_update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_ADD_Customer_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCmb_Customer_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_Customer_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User Customer.png")), jPanel9); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jbtn_GRN_Print_GRN2.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jbtn_GRN_Print_GRN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/send-to-printer-24.png"))); // NOI18N
        jbtn_GRN_Print_GRN2.setText("Print");
        jbtn_GRN_Print_GRN2.setFocusable(false);
        jbtn_GRN_Print_GRN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_GRN_Print_GRN2ActionPerformed(evt);
            }
        });

        jlbl_Income_Full_Amount.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Income_Full_Amount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_Income_Full_Amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel98.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel98.setText("Full Amount");

        jLabel89.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel89.setText("Customer Credit");

        jLabel81.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel81.setText("Cash Amount");

        jlbl_income.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_income.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_income.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jlbl_Customer_Credit.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Customer_Credit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_Customer_Credit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel22.setBackground(new java.awt.Color(0, 102, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        lbl_Date2.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        lbl_Date2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Date2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lbl_Date2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel97.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel97.setText("Date");

        lbl_Report_ID.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        lbl_Report_ID.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Report_ID.setText("Date");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel98)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtn_GRN_Print_GRN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlbl_Income_Full_Amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Report_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbl_income, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbl_Customer_Credit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Date2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(lbl_Report_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_income, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_Customer_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_Income_Full_Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jbtn_GRN_Print_GRN2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User Report.png")), jPanel13); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane3.setFocusable(false);
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel72.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel72.setText("GRN ID");

        jLabel73.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel73.setText("GRN Date");

        jLabel74.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel74.setText("Supplier Name");

        jLabel75.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel75.setText("Credit Price");

        jtxt_Credit_Settle_Shop_Pay_Price.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_Pay_Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Credit_Settle_Shop_Pay_PriceActionPerformed(evt);
            }
        });
        jtxt_Credit_Settle_Shop_Pay_Price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Shop_Pay_PriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Shop_Pay_PriceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Shop_Pay_PriceKeyTyped(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel76.setText("Pay Price");

        jLabel77.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel77.setText("Ballence Credit");

        Jbtn_Credit_Settle_Shop_Pay.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        Jbtn_Credit_Settle_Shop_Pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/pay-30.png"))); // NOI18N
        Jbtn_Credit_Settle_Shop_Pay.setText("Pay");
        Jbtn_Credit_Settle_Shop_Pay.setFocusable(false);
        Jbtn_Credit_Settle_Shop_Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_Credit_Settle_Shop_PayActionPerformed(evt);
            }
        });

        Jtbl_Credit_Settle_Shop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Jtbl_Credit_Settle_Shop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRN ID", "GRN Date", "Supplier ID", "Supplier Name", "Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtbl_Credit_Settle_Shop.setRowHeight(20);
        Jtbl_Credit_Settle_Shop.getTableHeader().setReorderingAllowed(false);
        Jtbl_Credit_Settle_Shop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtbl_Credit_Settle_ShopMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(Jtbl_Credit_Settle_Shop);
        if (Jtbl_Credit_Settle_Shop.getColumnModel().getColumnCount() > 0) {
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(0).setMinWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(0).setPreferredWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(0).setMaxWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(1).setMinWidth(120);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(1).setPreferredWidth(120);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(1).setMaxWidth(120);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(2).setMinWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(2).setPreferredWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(2).setMaxWidth(100);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(4).setMinWidth(150);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(4).setPreferredWidth(150);
            Jtbl_Credit_Settle_Shop.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jtxt_Credit_Settle_Shop_GRN_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_GRN_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Shop_GRN_Date.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_GRN_Date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Shop_Supplier_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_Supplier_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Shop_Supplier_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_Supplier_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Shop_Credit_Price.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_Credit_Price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Shop_Ballence_Credit.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Shop_Ballence_Credit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_Credit_Settle_Shop_Pay_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_Credit_Settle_Shop_Credit_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addGap(12, 12, 12)
                                .addComponent(jtxt_Credit_Settle_Shop_Supplier_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtxt_Credit_Settle_Shop_Ballence_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jbtn_Credit_Settle_Shop_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxt_Credit_Settle_Shop_Supplier_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel77)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxt_Credit_Settle_Shop_GRN_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxt_Credit_Settle_Shop_GRN_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxt_Credit_Settle_Shop_GRN_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxt_Credit_Settle_Shop_GRN_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxt_Credit_Settle_Shop_Supplier_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxt_Credit_Settle_Shop_Supplier_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxt_Credit_Settle_Shop_Credit_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_Credit_Settle_Shop_Pay_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxt_Credit_Settle_Shop_Ballence_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Jbtn_Credit_Settle_Shop_Pay)
                .addGap(25, 25, 25)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jTabbedPane3.addTab("Shop Credit", new javax.swing.ImageIcon(getClass().getResource("/Icon/pay-30.png")), jPanel17); // NOI18N

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel79.setText("Inv. No");

        jtxt_Credit_Settle_Customer_Invoice_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Invoice_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel80.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel80.setText("Inv. Date");

        jtxt_Credit_Settle_Customer_Date.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Customer_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Customer_Customer_Name.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Customer_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Customer_Credit_Price.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Credit_Price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Credit_Settle_Customer_Pay_Price.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Pay_Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Credit_Settle_Customer_Pay_PriceActionPerformed(evt);
            }
        });
        jtxt_Credit_Settle_Customer_Pay_Price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Customer_Pay_PriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Customer_Pay_PriceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_Credit_Settle_Customer_Pay_PriceKeyTyped(evt);
            }
        });

        jtxt_Credit_Settle_Customer_Ballence_Credit.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Ballence_Credit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        Jbtn_Credit_Settle_Customer_Pay.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        Jbtn_Credit_Settle_Customer_Pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/refund-30.png"))); // NOI18N
        Jbtn_Credit_Settle_Customer_Pay.setText("Pay");
        Jbtn_Credit_Settle_Customer_Pay.setFocusable(false);
        Jbtn_Credit_Settle_Customer_Pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jbtn_Credit_Settle_Customer_PayActionPerformed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel84.setText("Balance Credit");

        jLabel85.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel85.setText("Pay Price");

        jLabel86.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel86.setText("Credit Price");

        jLabel87.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel87.setText("Customer Name");

        Jtbl_Credit_Settle_Customer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Jtbl_Credit_Settle_Customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inv. No", "Inv. Date", "Customer ID", "Customer Name", "Contact Number", "Credit"
            }
        ));
        Jtbl_Credit_Settle_Customer.setRowHeight(20);
        Jtbl_Credit_Settle_Customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtbl_Credit_Settle_CustomerMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(Jtbl_Credit_Settle_Customer);

        jtxt_Credit_Settle_Customer_Contact_Number.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Credit_Settle_Customer_Contact_Number.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel88.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel88.setText("Contact Number");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxt_Credit_Settle_Customer_Invoice_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxt_Credit_Settle_Customer_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(jLabel86)
                            .addComponent(jLabel87))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jtxt_Credit_Settle_Customer_Customer_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxt_Credit_Settle_Customer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel88)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxt_Credit_Settle_Customer_Contact_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxt_Credit_Settle_Customer_Pay_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Credit_Settle_Customer_Credit_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Jbtn_Credit_Settle_Customer_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addGap(18, 18, 18)
                                .addComponent(jtxt_Credit_Settle_Customer_Ballence_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(548, 548, 548))))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxt_Credit_Settle_Customer_Invoice_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtxt_Credit_Settle_Customer_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxt_Credit_Settle_Customer_Pay_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtxt_Credit_Settle_Customer_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Credit_Settle_Customer_Contact_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addComponent(jtxt_Credit_Settle_Customer_Credit_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtxt_Credit_Settle_Customer_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxt_Credit_Settle_Customer_Ballence_Credit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Jbtn_Credit_Settle_Customer_Pay)
                .addGap(359, 359, 359))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                    .addContainerGap(330, Short.MAX_VALUE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)))
        );

        jTabbedPane3.addTab("Customer Credit", new javax.swing.ImageIcon(getClass().getResource("/Icon/refund-30.png")), jPanel18); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User Credit Settle.png")), jPanel14); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setFocusable(false);

        zero.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        zero.setText("0");
        zero.setBorder(null);
        zero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zero.setFocusable(false);
        zero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zeroActionPerformed(evt);
            }
        });

        equals.setBackground(new java.awt.Color(204, 204, 204));
        equals.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        equals.setText("=");
        equals.setBorder(null);
        equals.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        equals.setFocusable(false);
        equals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equalsActionPerformed(evt);
            }
        });

        decpoint.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        decpoint.setText(".");
        decpoint.setBorder(null);
        decpoint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        decpoint.setFocusable(false);
        decpoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decpointActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(204, 204, 204));
        clear.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        clear.setText("CE");
        clear.setBorder(null);
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear.setFocusable(false);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        reset.setBackground(new java.awt.Color(204, 204, 204));
        reset.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        reset.setText("C");
        reset.setBorder(null);
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.setFocusable(false);
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        divide.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        divide.setText("÷");
        divide.setBorder(null);
        divide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        divide.setFocusable(false);
        divide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divideActionPerformed(evt);
            }
        });

        multiply.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        multiply.setText("x");
        multiply.setBorder(null);
        multiply.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        multiply.setFocusable(false);
        multiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplyActionPerformed(evt);
            }
        });

        subtract.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        subtract.setText("-");
        subtract.setBorder(null);
        subtract.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subtract.setFocusable(false);
        subtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtractActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add.setText("+");
        add.setBorder(null);
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add.setFocusable(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        three.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        three.setText("3");
        three.setBorder(null);
        three.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        three.setFocusable(false);
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }
        });

        two.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        two.setText("2");
        two.setBorder(null);
        two.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        two.setFocusable(false);
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }
        });

        one.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        one.setText("1");
        one.setBorder(null);
        one.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        one.setFocusable(false);
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }
        });

        six.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        six.setText("6");
        six.setBorder(null);
        six.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        six.setFocusable(false);
        six.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixActionPerformed(evt);
            }
        });

        nine.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nine.setText("9");
        nine.setBorder(null);
        nine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nine.setFocusable(false);
        nine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nineActionPerformed(evt);
            }
        });

        eight.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        eight.setText("8");
        eight.setBorder(null);
        eight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eight.setFocusable(false);
        eight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightActionPerformed(evt);
            }
        });

        five.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        five.setText("5");
        five.setBorder(null);
        five.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        five.setFocusable(false);
        five.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiveActionPerformed(evt);
            }
        });

        four.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        four.setText("4");
        four.setBorder(null);
        four.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        four.setFocusable(false);
        four.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourActionPerformed(evt);
            }
        });

        seven.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        seven.setText("7");
        seven.setBorder(null);
        seven.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seven.setFocusable(false);
        seven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sevenActionPerformed(evt);
            }
        });

        percent.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        percent.setText("%");
        percent.setBorder(null);
        percent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        percent.setFocusable(false);
        percent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentActionPerformed(evt);
            }
        });

        display1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        display1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        display1.setText("0");
        display1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        display1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        display1.setFocusable(false);

        display2.setEditable(false);
        display2.setBackground(new java.awt.Color(255, 255, 255));
        display2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        display2.setForeground(new java.awt.Color(255, 255, 255));
        display2.setBorder(null);
        display2.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(display2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(four, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(five, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(one, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(two, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(decpoint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(six, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(equals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(multiply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(subtract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(divide, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(display1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(232, 232, 232))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(display1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(percent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(divide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seven, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(eight, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nine, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(multiply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(four, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(six, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(five, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subtract, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(one, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(two, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(three, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(decpoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addComponent(display2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/User Calculator.png")), jPanel5); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','Has been Exit from the System')");
            System.exit(0);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            setExtendedState(Customer.ICONIFIED);
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','System Minimized')");
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','Back Logged into to User Menu')");
            new User_Menu().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Login')");
            new Login().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jbtn_Item_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Item_SaveActionPerformed
        if (!jlbl_Item_Item_ID.getText().isEmpty() && !jtxt_Item_Name.getText().isEmpty()) {
            try {
                int Status = 0;

                if (jCmb_Item_Status.getSelectedItem().toString().equals("Active")) {
                    Status += 1;
                }
                if (jCmb_Item_Status.getSelectedItem().toString().equals("Deactive")) {
                    Status += 0;
                }

                new Database_Connection.DB().putData("insert into item (item_id,barcode,item_name,status) values('" + jlbl_Item_Item_ID.getText() + "','" + jtxt_Item_barcode.getText() + "','" + jtxt_Item_Name.getText() + "','" + Status + "')");
                JOptionPane.showMessageDialog(this, "Item Registered Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                item_text_clean();
                jtxt_Item_barcode.grabFocus();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtn_Item_SaveActionPerformed

    private void jtbl_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbl_ItemMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Item.getModel();
        int Selected_Row = jtbl_Item.getSelectedRow();
        jlbl_Item_Item_ID.setText(dtm.getValueAt(Selected_Row, 0).toString());
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM item WHERE item_id = '" + jlbl_Item_Item_ID.getText() + "'");
            while (rs.next()) {
                jtxt_Item_barcode.setText(rs.getString("barcode"));
                jtxt_Item_Name.setText(rs.getString("item_name"));
                if (rs.getString("status").equals("1")) {
                    jCmb_Item_Status.setSelectedItem("Active");
                } else {
                    jCmb_Item_Status.setSelectedItem("Deactive");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jtbl_ItemMouseClicked
    private void jbtn_Item_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Item_UpdateActionPerformed

        if (!jlbl_Item_Item_ID.getText().isEmpty() && !jtxt_Item_Name.getText().isEmpty()) {
            try {
                int Status = 0;

                if (jCmb_Item_Status.getSelectedItem().toString().equals("Active")) {
                    Status += 1;
                }
                if (jCmb_Item_Status.getSelectedItem().toString().equals("Deactive")) {
                    Status += 0;
                }
                new DB().putData("UPDATE item SET barcode='" + jtxt_Item_barcode.getText() + "',item_name='" + jtxt_Item_Name.getText() + "',status='" + Status + "' WHERE item_id = '" + jlbl_Item_Item_ID.getText() + "'");
                JOptionPane.showMessageDialog(this, "Item Details Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                item_text_clean();

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jbtn_Item_UpdateActionPerformed

    private void jbtn_Item_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Item_ClearActionPerformed
        item_text_clean();
    }//GEN-LAST:event_jbtn_Item_ClearActionPerformed

    private void jtxt_Item_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Item_SearchKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Item.getModel();
        try {
            ResultSet rs = new DB().getData("select * from item where item_name like '%" + jtxt_Item_Search.getText() + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("item_id"));
                v.add(rs.getString("barcode"));
                v.add(rs.getString("item_name"));
                if (rs.getString("status").equals("1")) {
                    v.add("Active");
                }
                if (rs.getString("status").equals("0")) {
                    v.add("Deactive");
                }
                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jtxt_Item_SearchKeyReleased

    private void jtxt_Item_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Item_barcodeActionPerformed
        jtxt_Item_Name.grabFocus();
    }//GEN-LAST:event_jtxt_Item_barcodeActionPerformed

    private void jtxt_Item_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Item_NameActionPerformed
        jCmb_Item_Status.grabFocus();
    }//GEN-LAST:event_jtxt_Item_NameActionPerformed

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
//        jtxt_Itm_barcode.grabFocus();
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel8KeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {

            jtxt_Item_barcode.grabFocus();
        }
    }//GEN-LAST:event_jPanel8KeyPressed

    private void jTabbedPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane2KeyPressed

    }//GEN-LAST:event_jTabbedPane2KeyPressed

    private void jtxt_Invoice_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountActionPerformed

    }//GEN-LAST:event_jtxt_Invoice_DiscountActionPerformed

    private void jtxt_Invoice_DiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {
            Gross_To_Table();
            jtxt_Invoice_Barcode.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyPressed

    private void jbtn_Clear_OneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_OneActionPerformed
        int selectedRow = jtbl_Invoice_Item.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.removeRow(selectedRow);
        Auto_Total_Calculate();
    }//GEN-LAST:event_jbtn_Clear_OneActionPerformed

    private void jtxt_Invoice_DiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyTyped

    private void jtxt_Invoice_QtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_QtyKeyTyped

    private void jtxt_Invoice_QtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyReleased

        Gross_Total();

    }//GEN-LAST:event_jtxt_Invoice_QtyKeyReleased

    private void jtxt_Invoice_QtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyActionPerformed
    }//GEN-LAST:event_jtxt_Invoice_QtyActionPerformed

    private void jtbl_Invoice_GRNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbl_Invoice_GRNMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
        int Selected_Row = jtbl_Invoice_GRN.getSelectedRow();

        try {
            jlbl_grn_id.setText(dtm.getValueAt(Selected_Row, 0).toString());
            String Supplier_Name = dtm.getValueAt(Selected_Row, 2).toString();

            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM supplier WHERE name ='" + Supplier_Name + "'");
            while (rs.next()) {
                jlbl_grn_supplier_id.setText(rs.getString("supplier_id"));
                Discount_Margin_Supplier.setText(rs.getString("code"));
            }

            ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_item GI ON G.grn_id = GI.grn_id INNER JOIN grn_stock GS ON GS.grn_id = G.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jlbl_grn_id.getText() + "' and G.supplier_id = '" + jlbl_grn_supplier_id.getText() + "' and GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' and GI.item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
            while (rs1.next()) {

                jtxt_Invoice_Selling_Price.setText(rs1.getString("GS.selling_price"));
                Discount_Margin.setText(rs1.getString("GI.margin_code"));
                jlbl_Net_Discount.setText(rs1.getString("GI.supplier_price"));
                jtxt_Invoice_Discount.setText(rs1.getString("GI.discount"));
                jtxt_Invoice_Qty.grabFocus();
                if (Discount_Margin.getText().isEmpty()) {
                    jCheckBox1.setVisible(false);
                } else {
                    jCheckBox1.setVisible(true);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jtbl_Invoice_GRNMouseClicked

    private void Jbtn_Credit_Settle_Shop_PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_Credit_Settle_Shop_PayActionPerformed
        if (!jtxt_Credit_Settle_Shop_Pay_Price.getText().isEmpty()) {
            try {
                new DB().putData("UPDATE grn SET update_credit_price='" + jtxt_Credit_Settle_Shop_Ballence_Credit.getText() + "' WHERE grn_id = '" + jtxt_Credit_Settle_Shop_GRN_ID.getText() + "'");
                new DB().putData("INSERT INTO grn_payment (grn_id, payment_price) VALUES('" + jtxt_Credit_Settle_Shop_GRN_ID.getText() + "','" + jtxt_Credit_Settle_Shop_Pay_Price.getText() + "')");
                JOptionPane.showMessageDialog(this, "Payment Details Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                jtxt_Credit_Settle_Shop_GRN_ID.setText(null);
                jtxt_Credit_Settle_Shop_GRN_Date.setText(null);
                jtxt_Credit_Settle_Shop_Supplier_Name.setText(null);
                jtxt_Credit_Settle_Shop_Supplier_ID.setText(null);
                jtxt_Credit_Settle_Shop_Credit_Price.setText(null);
                jtxt_Credit_Settle_Shop_Pay_Price.setText(null);
                jtxt_Credit_Settle_Shop_Ballence_Credit.setText(null);
                Shop_Credit_Table_Loader_Data();

            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_Jbtn_Credit_Settle_Shop_PayActionPerformed

    private void Jtbl_Credit_Settle_ShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtbl_Credit_Settle_ShopMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Jtbl_Credit_Settle_Shop.getModel();
        int Selected_Row = Jtbl_Credit_Settle_Shop.getSelectedRow();
        jtxt_Credit_Settle_Shop_GRN_ID.setText(dtm.getValueAt(Selected_Row, 0).toString());
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jtxt_Credit_Settle_Shop_GRN_ID.getText() + "'");
            while (rs.next()) {
                jtxt_Credit_Settle_Shop_GRN_Date.setText(rs.getString("G.date"));
                jtxt_Credit_Settle_Shop_Supplier_ID.setText(rs.getString("G.supplier_id"));
                jtxt_Credit_Settle_Shop_Supplier_Name.setText(rs.getString("S.name"));
                jtxt_Credit_Settle_Shop_Credit_Price.setText(rs.getString("G.update_credit_price"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_Jtbl_Credit_Settle_ShopMouseClicked

    private void jbtn_Invoice_PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Invoice_PayActionPerformed

        if (!jtxt_Invoice_Cash.getText().isEmpty() && jtbl_Invoice_Item.getRowCount() != 0) {

            if (sub_total_Number_Discount.isSelected()) {

                if (customer_cash.isSelected()) {

                    if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {
                        try {
                            new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','invoice','yes','yes')");
                            new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','yes')");
                        } catch (Exception e) {
                            System.out.println("Errrr 1........ ");
                            e.printStackTrace();
                        }
                        Invoice_item();
                        ReportInvoice();
                        Clear_Invoice();

                    } else {

                        try {
                            new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','invoice','yes','yes')");
                            new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','yes')");
                        } catch (Exception e) {
                            System.out.println("Errrr 1........ ");
                            e.printStackTrace();
                        }
                        Invoice_item();
                        ReportInvoiceDiscount();
                        Clear_Invoice();
                    }

                } else {

                    if (!jlbl_Invoice_Customer_ID.getText().equals(jlbl_Customer_ID_Check.getText())) {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','invoice','yes','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "','yes')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCredit();
                            Clear_Invoice();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','invoice','yes','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "','yes')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCreditDiscount();
                            Clear_Invoice();
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Please Add Valid Customer", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                if (customer_cash.isSelected()) {

                    if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {
                        try {
                            new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','invoice','yes','yes')");
                            new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','yes')");
                        } catch (Exception e) {
                            System.out.println("Errrr 1........ ");
                            e.printStackTrace();
                        }
                        Invoice_item();
                        ReportInvoice();
                        Clear_Invoice();

                    } else {

                        try {
                            new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','invoice','yes','yes')");
                            new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','yes')");
                        } catch (Exception e) {
                            System.out.println("Errrr 1........ ");
                            e.printStackTrace();
                        }
                        Invoice_item();
                        ReportInvoiceDiscount();
                        Clear_Invoice();
                    }

                } else {

                    if (!jlbl_Invoice_Customer_ID.getText().equals(jlbl_Customer_ID_Check.getText())) {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','invoice','yes','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "','yes')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCredit();
                            Clear_Invoice();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','invoice','yes','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance,active) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "','yes')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCreditDiscount();
                            Clear_Invoice();
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Please Add Valid Customer", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "No Payment or Item add in Receipt", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtn_Invoice_PayActionPerformed

    private void jbtn_Clear_AllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_AllActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.setRowCount(0);
        jlbl_Gross_Total.setText(null);
        jtxt_Net_Discount.setText(null);
        jtxt_Net_Total.setText(null);
        jtxt_Invoice_Cash.setText(null);
        jlbl_Invoice_Balance.setText(null);
    }//GEN-LAST:event_jbtn_Clear_AllActionPerformed

    private void jtxt_Invoice_CashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_CashKeyReleased
        Invoice_Cash();
    }//GEN-LAST:event_jtxt_Invoice_CashKeyReleased

    private void jtxt_Net_DiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Net_DiscountKeyReleased
        Net_Discount();
    }//GEN-LAST:event_jtxt_Net_DiscountKeyReleased

    private void jbtn_ADD_New_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ADD_New_CustomerActionPerformed

        if (ca == null) {
            ca = new Customer_Add();
            ca.setVisible(true);
        }
    }//GEN-LAST:event_jbtn_ADD_New_CustomerActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked

    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void jTabbedPane2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseEntered
        Customer_Table_Loader_Data();
        Customer_ID_Auto_Loader();
    }//GEN-LAST:event_jTabbedPane2MouseEntered

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked

    }//GEN-LAST:event_jPanel9MouseClicked

    private void jCmb_Item_StatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmb_Item_StatusKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {

            if (!jlbl_Item_Item_ID.getText().isEmpty() && !jtxt_Item_Name.getText().isEmpty()) {
                try {
                    int Status = 0;

                    if (jCmb_Item_Status.getSelectedItem().toString().equals("Active")) {
                        Status += 1;
                    }
                    if (jCmb_Item_Status.getSelectedItem().toString().equals("Deactive")) {
                        Status += 0;
                    }

                    new Database_Connection.DB().putData("insert into item (item_id,barcode,item_name,status) values('" + jlbl_Item_Item_ID.getText() + "','" + jtxt_Item_barcode.getText() + "','" + jtxt_Item_Name.getText() + "','" + Status + "')");
                    JOptionPane.showMessageDialog(this, "Item Registered Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                    item_text_clean();
                    jtxt_Item_barcode.grabFocus();

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jCmb_Item_StatusKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Customer_Return().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jtxt_Credit_Settle_Shop_Pay_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyTyped

    }//GEN-LAST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyTyped

    private void jtxt_Credit_Settle_Shop_Pay_PriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyReleased
        if (!jtxt_Credit_Settle_Shop_Pay_Price.getText().isEmpty()) {

            double QTY = Double.parseDouble(jtxt_Credit_Settle_Shop_Credit_Price.getText().toString());
            double UP = Double.parseDouble(jtxt_Credit_Settle_Shop_Pay_Price.getText().toString());
            double tot = QTY - UP;
            jtxt_Credit_Settle_Shop_Ballence_Credit.setText(new DecimalFormat("0.00").format(tot));
        } else {
            jtxt_Credit_Settle_Shop_Ballence_Credit.setText("0.00");
        }
    }//GEN-LAST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyReleased

    private void jtxt_Credit_Settle_Shop_Pay_PriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyPressed

    }//GEN-LAST:event_jtxt_Credit_Settle_Shop_Pay_PriceKeyPressed

    private void jtxt_Credit_Settle_Shop_Pay_PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Shop_Pay_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Credit_Settle_Shop_Pay_PriceActionPerformed

    private void jtxt_Invoice_DiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyReleased
        Gross_Total();
    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyReleased

    private void customer_creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_creditActionPerformed
        if (customer_credit.isSelected()) {
            jLabel22.setText("Balance Credit");
            Invoice_Cash();
        }
    }//GEN-LAST:event_customer_creditActionPerformed

    private void customer_cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_cashActionPerformed
        if (customer_cash.isSelected()) {
            jLabel22.setText("Balance");
            Invoice_Cash();
        }
    }//GEN-LAST:event_customer_cashActionPerformed

    private void jtxt_Invoice_QtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyPressed
        if (!jtxt_Invoice_Qty.getText().isEmpty()) {
            char c1 = evt.getKeyChar();
            if (c1 == evt.VK_ENTER) {
                jtxt_Invoice_Discount.grabFocus();
            }
        } else {
        }
    }//GEN-LAST:event_jtxt_Invoice_QtyKeyPressed

    private void jtxt_Credit_Settle_Customer_Pay_PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Customer_Pay_PriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Credit_Settle_Customer_Pay_PriceActionPerformed

    private void jtxt_Credit_Settle_Customer_Pay_PriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyPressed

    private void jtxt_Credit_Settle_Customer_Pay_PriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyReleased
        double QTY = Double.parseDouble(jtxt_Credit_Settle_Customer_Credit_Price.getText().toString());
        double UP = Double.parseDouble(jtxt_Credit_Settle_Customer_Pay_Price.getText().toString());
        double tot = QTY - UP;
        jtxt_Credit_Settle_Customer_Ballence_Credit.setText(new DecimalFormat("0.00").format(tot));
    }//GEN-LAST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyReleased

    private void jtxt_Credit_Settle_Customer_Pay_PriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Credit_Settle_Customer_Pay_PriceKeyTyped

    private void Jbtn_Credit_Settle_Customer_PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jbtn_Credit_Settle_Customer_PayActionPerformed
        if (!jtxt_Credit_Settle_Customer_Pay_Price.getText().isEmpty()) {
            try {
                new DB().putData("UPDATE invoice SET customer_credit='" + jtxt_Credit_Settle_Customer_Ballence_Credit.getText() + "' WHERE invoice_id = '" + jtxt_Credit_Settle_Customer_Invoice_ID.getText() + "'");
                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id,date,time,cash,balance) VALUES('" + jtxt_Credit_Settle_Customer_Invoice_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Credit_Settle_Customer_Pay_Price.getText() + "','0')");
                JOptionPane.showMessageDialog(this, "Payment Details Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                jtxt_Credit_Settle_Customer_Date.setText(null);
                jtxt_Credit_Settle_Customer_Customer_Name.setText(null);
                jtxt_Credit_Settle_Customer_ID.setText(null);
                jtxt_Credit_Settle_Customer_Credit_Price.setText(null);
                jtxt_Credit_Settle_Customer_Pay_Price.setText(null);
                jtxt_Credit_Settle_Customer_Ballence_Credit.setText(null);
                jtxt_Credit_Settle_Customer_Contact_Number.setText(null);
                Report_Customer_Credit();
                jtxt_Credit_Settle_Customer_Invoice_ID.setText(null);
                Customer_Credit_Table_Loader_Data();
//                Invoice_Income_Table_Loader();
//                Invoice_Payment_Table_Total();
                Customer_Credit();
                income();
                Chart();

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_Jbtn_Credit_Settle_Customer_PayActionPerformed

    private void Jtbl_Credit_Settle_CustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtbl_Credit_Settle_CustomerMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) Jtbl_Credit_Settle_Customer.getModel();
        int Selected_Row = Jtbl_Credit_Settle_Customer.getSelectedRow();
        jtxt_Credit_Settle_Customer_Invoice_ID.setText(dtm.getValueAt(Selected_Row, 0).toString());
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM invoice I INNER JOIN customer C ON I.customer_id = C.customer_id WHERE I.invoice_id = '" + jtxt_Credit_Settle_Customer_Invoice_ID.getText() + "'");
            while (rs.next()) {
                jtxt_Credit_Settle_Customer_Date.setText(rs.getString("I.date"));
                jtxt_Credit_Settle_Customer_ID.setText(rs.getString("I.customer_id"));
                jtxt_Credit_Settle_Customer_Customer_Name.setText(rs.getString("C.name"));
                jtxt_Credit_Settle_Customer_Credit_Price.setText(rs.getString("I.customer_credit"));
                jtxt_Credit_Settle_Customer_Contact_Number.setText(rs.getString("C.contact_no"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_Jtbl_Credit_Settle_CustomerMouseClicked

    private void jtxt_Invoice_BarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyReleased
        if (!jtxt_Invoice_Barcode.getText().isEmpty()) {
            invoice_barcode();
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm.setRowCount(0);
            Clear_Invoice_Item();
        }
    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyReleased

    private void jtbl_Customerjtbl_ADD_CustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbl_Customerjtbl_ADD_CustomerMouseClicked
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Customer.getModel();
        int Selected_Row = jtbl_Customer.getSelectedRow();
        jlbl_Customer_ID.setText(dtm.getValueAt(Selected_Row, 0).toString());
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM customer WHERE customer_id = '" + jlbl_Customer_ID.getText() + "'");
            while (rs.next()) {
                jtxt_Customer_Name.setText(rs.getString("name"));
                if (rs.getString("gender").equals("Male")) {
                    male.setSelected(true);
                } else {
                    female.setSelected(true);
                }
                jtxt_Customer_Phone_number.setText(rs.getString("contact_no"));
                jtxt_Customer__Email.setText(rs.getString("email"));
                jtxt_Customer_Address.setText(rs.getString("address"));
                if (rs.getString("status").equals("1")) {
                    jCmb_Customer_Status.setSelectedItem("Active");
                } else {
                    jCmb_Customer_Status.setSelectedItem("Deactive");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jtbl_Customerjtbl_ADD_CustomerMouseClicked

    private void jtxt_Customer_Searchjtxt_ADD_Customer_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Customer_Searchjtxt_ADD_Customer_SearchKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Customer.getModel();
        try {
            ResultSet rs = new DB().getData("select * from customer where name like '%" + jtxt_Customer_Search.getText() + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("customer_id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("gender"));
                v.add(rs.getString("contact_no"));
                v.add(rs.getString("email"));
                v.add(rs.getString("address"));
                if (rs.getString("status").equals("1")) {
                    v.add("Active");
                }
                if (rs.getString("status").equals("0")) {
                    v.add("Deactive");
                }
                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jtxt_Customer_Searchjtxt_ADD_Customer_SearchKeyReleased

    private void jbtn_ADD_Customer_Clearjbtn_ADD_Customer_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ADD_Customer_Clearjbtn_ADD_Customer_ClearActionPerformed
        jtxt_Customer_Name.setText(null);
        male.setSelected(true);
        jtxt_Customer_Phone_number.setText(null);
        jtxt_Customer__Email.setText(null);
        jtxt_Customer_Address.setText(null);
        jCmb_Customer_Status.setSelectedIndex(0);
        jtxt_Customer_Search.setText(null);
        jtxt_Customer_Name.grabFocus();
        Customer_ID_Auto_Loader();
        Customer_Table_Loader_Data();
    }//GEN-LAST:event_jbtn_ADD_Customer_Clearjbtn_ADD_Customer_ClearActionPerformed

    private void jbtn_ADD_Customer_updatejbtn_ADD_Customer_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ADD_Customer_updatejbtn_ADD_Customer_updateActionPerformed
        if (!jlbl_Customer_ID.getText().isEmpty() && !jtxt_Customer_Name.getText().isEmpty()) {
            try {
                if (male.isSelected()) {
                    gen = "Male";
                } else {
                    gen = "Female";
                }

                int Status = 0;

                if (jCmb_Customer_Status.getSelectedItem().toString().equals("Active")) {
                    Status += 1;
                }
                if (jCmb_Customer_Status.getSelectedItem().toString().equals("Deactive")) {
                    Status += 0;
                }
                new DB().putData("UPDATE customer SET name='" + jtxt_Customer_Name.getText() + "',gender='" + gen + "',contact_no='" + jtxt_Customer_Phone_number.getText() + "',email='" + jtxt_Customer__Email.getText() + "',address='" + jtxt_Customer_Address.getText() + "',status='" + Status + "' WHERE customer_id = '" + jlbl_Customer_ID.getText() + "'");
                JOptionPane.showMessageDialog(this, "Customer Details Updated Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                jtxt_Customer_Name.setText(null);
                male.setSelected(true);
                jtxt_Customer_Phone_number.setText(null);
                jtxt_Customer__Email.setText(null);
                jtxt_Customer_Address.setText(null);
                jCmb_Customer_Status.setSelectedIndex(0);
                jtxt_Customer_Name.grabFocus();
                Customer_ID_Auto_Loader();
                Customer_Table_Loader_Data();

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jbtn_ADD_Customer_updatejbtn_ADD_Customer_updateActionPerformed

    private void jbtn_ADD_Customer_Savejbtn_ADD_Customer_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ADD_Customer_Savejbtn_ADD_Customer_SaveActionPerformed
        if (!jlbl_Customer_ID.getText().isEmpty() && !jtxt_Customer_Name.getText().isEmpty()) {
            try {

                int Status = 0;

                if (jCmb_Customer_Status.getSelectedItem().toString().equals("Active")) {
                    Status += 1;
                }
                if (jCmb_Customer_Status.getSelectedItem().toString().equals("Deactive")) {
                    Status += 0;
                }

                if (male.isSelected()) {
                    gen = "Male";
                } else {
                    gen = "Female";
                }

                new Database_Connection.DB().putData("insert into customer (customer_id,name,gender,contact_no,email,address,status) values('" + jlbl_Customer_ID.getText() + "','" + jtxt_Customer_Name.getText() + "','" + gen + "','" + jtxt_Customer_Phone_number.getText() + "','" + jtxt_Customer__Email.getText() + "','" + jtxt_Customer_Address.getText() + "','" + Status + "')");
                JOptionPane.showMessageDialog(this, "Customer Registered Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                jtxt_Customer_Name.setText(null);
                male.setSelected(true);
                jtxt_Customer_Phone_number.setText(null);
                jtxt_Customer__Email.setText(null);
                jtxt_Customer_Address.setText(null);
                jCmb_Customer_Status.setSelectedIndex(0);
                jtxt_Customer_Name.grabFocus();
                Customer_ID_Auto_Loader();
                Customer_Table_Loader_Data();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtn_ADD_Customer_Savejbtn_ADD_Customer_SaveActionPerformed

    private void jtxt_Customer_Addressjtxt_ADD_Customer_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Customer_Addressjtxt_ADD_Customer_AddressActionPerformed
        jCmb_Customer_Status.grabFocus();
    }//GEN-LAST:event_jtxt_Customer_Addressjtxt_ADD_Customer_AddressActionPerformed

    private void jtxt_Customer__Emailjtxt_ADD_Customer_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Customer__Emailjtxt_ADD_Customer_EmailActionPerformed
        jtxt_Customer_Address.grabFocus();
    }//GEN-LAST:event_jtxt_Customer__Emailjtxt_ADD_Customer_EmailActionPerformed

    private void jtxt_Customer_Phone_numberjtxt_ADD_Customer_Phone_numberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Customer_Phone_numberjtxt_ADD_Customer_Phone_numberActionPerformed
        jtxt_Customer__Email.grabFocus();
    }//GEN-LAST:event_jtxt_Customer_Phone_numberjtxt_ADD_Customer_Phone_numberActionPerformed

    private void jtxt_Customer_Namejtxt_ADD_Customer_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Customer_Namejtxt_ADD_Customer_NameActionPerformed
        jtxt_Customer_Phone_number.grabFocus();
    }//GEN-LAST:event_jtxt_Customer_Namejtxt_ADD_Customer_NameActionPerformed

    private void femalefemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femalefemaleActionPerformed
        jtxt_Customer_Phone_number.grabFocus();
    }//GEN-LAST:event_femalefemaleActionPerformed

    private void malemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_malemaleActionPerformed
        jtxt_Customer_Phone_number.grabFocus();
    }//GEN-LAST:event_malemaleActionPerformed

    private void sub_total_Number_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_total_Number_DiscountActionPerformed
        Net_Discount();
    }//GEN-LAST:event_sub_total_Number_DiscountActionPerformed

    private void sub_total_Precentage_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_total_Precentage_DiscountActionPerformed
        Net_Discount();
    }//GEN-LAST:event_sub_total_Precentage_DiscountActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        Gross_Total();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        Gross_Total();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jtxt_Net_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Net_DiscountActionPerformed
        jtxt_Invoice_Cash.grabFocus();
    }//GEN-LAST:event_jtxt_Net_DiscountActionPerformed

    private void zeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "0");
        zerodisp = true;
    }//GEN-LAST:event_zeroActionPerformed

    private void equalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalsActionPerformed
        inb = Double.parseDouble(String.valueOf(display1.getText()));
        if (op == 0) {
            out = inb;
            display2.setText(String.valueOf(inb));
        }
        if (op == 1) {
            out = ina + inb;
            display2.setText(display2.getText() + String.valueOf(inb));
        }
        if (op == 2) {
            out = ina - inb;
            display2.setText(display2.getText() + String.valueOf(inb));
        }
        if (op == 3) {
            out = ina * inb;
            display2.setText(display2.getText() + String.valueOf(inb));
        }
        if (op == 4) {
            out = ina / inb;
            display2.setText(display2.getText() + String.valueOf(inb));
        }
        if (op == 5) {
            out = ina * inb / 100;
            display2.setText(display2.getText() + String.valueOf(inb) + ")");
        }
        if (out > -100000000 && out < 100000000) {
            display1.setText(String.valueOf(out));
        } else {
            display1.setText("Error");
        }

        ina = 0;
        inb = 0;
        out = 0;

        op = 0;
        decdisp = false;
        zerodisp = false;
    }//GEN-LAST:event_equalsActionPerformed

    private void decpointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decpointActionPerformed
        if (!decdisp) {
            display1.setText(display1.getText() + ".");
            decdisp = true;
        }
    }//GEN-LAST:event_decpointActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        display1.setText("0");
        zerodisp = false;
        decdisp = false;
    }//GEN-LAST:event_clearActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        display1.setText("0");
        display2.setText("null");
        zerodisp = false;
        decdisp = false;
        ina = 0;
        inb = 0;
        out = 0;
    }//GEN-LAST:event_resetActionPerformed

    private void divideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divideActionPerformed
        if (op == 0) {
            ina = Double.parseDouble(String.valueOf(display1.getText()));
        } else {
            inb = Double.parseDouble(String.valueOf(display1.getText()));
        }
        if (op == 1) {
            ina = ina + inb;
        }
        if (op == 2) {
            ina = ina - inb;
        }
        if (op == 3) {
            ina = ina * inb;
        }
        if (op == 4) {
            ina = ina / inb;
        }
        if (op == 5) {
            ina = ina * inb / 100;
        }

        display1.setText("0");
        display2.setText(String.valueOf(ina) + "/");

        op = 4;
        decdisp = false;
        zerodisp = false;
    }//GEN-LAST:event_divideActionPerformed

    private void multiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplyActionPerformed
        if (op == 0) {
            ina = Double.parseDouble(String.valueOf(display1.getText()));
        } else {
            inb = Double.parseDouble(String.valueOf(display1.getText()));
        }
        if (op == 1) {
            ina = ina + inb;
        }
        if (op == 2) {
            ina = ina - inb;
        }
        if (op == 3) {
            ina = ina * inb;
        }
        if (op == 4) {
            ina = ina / inb;
        }
        if (op == 5) {
            ina = ina * inb / 100;
        }

        display1.setText("0");
        display2.setText(String.valueOf(ina) + "*");

        op = 3;
        decdisp = false;
        zerodisp = false;
    }//GEN-LAST:event_multiplyActionPerformed

    private void subtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtractActionPerformed
        if (op == 0) {
            ina = Double.parseDouble(String.valueOf(display1.getText()));
        } else {
            inb = Double.parseDouble(String.valueOf(display1.getText()));
        }
        if (op == 1) {
            ina = ina + inb;
        }
        if (op == 2) {
            ina = ina - inb;
        }
        if (op == 3) {
            ina = ina * inb;
        }
        if (op == 4) {
            ina = ina / inb;
        }
        if (op == 5) {
            ina = ina * inb / 100;
        }

        display1.setText("0");
        display2.setText(String.valueOf(ina) + "-");

        op = 2;
        decdisp = false;
        zerodisp = false;
    }//GEN-LAST:event_subtractActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (op == 0) {
            ina = Double.parseDouble(String.valueOf(display1.getText()));
        } else {
            inb = Double.parseDouble(String.valueOf(display1.getText()));
        }
        if (op == 1) {
            ina = ina + inb;
        }
        if (op == 2) {
            ina = ina - inb;
        }
        if (op == 3) {
            ina = ina * inb;
        }
        if (op == 4) {
            ina = ina / inb;
        }
        if (op == 5) {
            ina = ina * inb / 100;
        }

        display1.setText("0");
        display2.setText(String.valueOf(ina) + "+");

        op = 1;
        decdisp = false;
        zerodisp = false;
    }//GEN-LAST:event_addActionPerformed

    private void threeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "3");
        zerodisp = true;
    }//GEN-LAST:event_threeActionPerformed

    private void twoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "2");
        zerodisp = true;
    }//GEN-LAST:event_twoActionPerformed

    private void oneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "1");
        zerodisp = true;
    }//GEN-LAST:event_oneActionPerformed

    private void sixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "6");
        zerodisp = true;
    }//GEN-LAST:event_sixActionPerformed

    private void nineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "9");
        zerodisp = true;
    }//GEN-LAST:event_nineActionPerformed

    private void eightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "8");
        zerodisp = true;
    }//GEN-LAST:event_eightActionPerformed

    private void fiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "5");
        zerodisp = true;
    }//GEN-LAST:event_fiveActionPerformed

    private void fourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "4");
        zerodisp = true;
    }//GEN-LAST:event_fourActionPerformed

    private void sevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenActionPerformed
        if (!zerodisp && !decdisp) {
            display1.setText(null);
        }
        display1.setText(display1.getText() + "7");
        zerodisp = true;
    }//GEN-LAST:event_sevenActionPerformed

    private void percentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentActionPerformed
        ina = Double.parseDouble(String.valueOf(display1.getText()));
        display1.setText("0");
        display2.setText(String.valueOf(ina) + "%(");

        decdisp = false;
        zerodisp = false;
        op = 5;

    }//GEN-LAST:event_percentActionPerformed

    private void jCmb_Customer_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmb_Customer_StatusActionPerformed
    }//GEN-LAST:event_jCmb_Customer_StatusActionPerformed

    private void jCmb_Customer_StatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCmb_Customer_StatusKeyPressed
        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            if (!jlbl_Customer_ID.getText().isEmpty() && !jtxt_Customer_Name.getText().isEmpty()) {
                try {

                    int Status = 0;

                    if (jCmb_Customer_Status.getSelectedItem().toString().equals("Active")) {
                        Status += 1;
                    }
                    if (jCmb_Customer_Status.getSelectedItem().toString().equals("Deactive")) {
                        Status += 0;
                    }

                    if (male.isSelected()) {
                        gen = "Male";
                    } else {
                        gen = "Female";
                    }

                    new Database_Connection.DB().putData("insert into customer (customer_id,name,gender,contact_no,email,address,status) values('" + jlbl_Customer_ID.getText() + "','" + jtxt_Customer_Name.getText() + "','" + gen + "','" + jtxt_Customer_Phone_number.getText() + "','" + jtxt_Customer__Email.getText() + "','" + jtxt_Customer_Address.getText() + "','" + Status + "')");
                    JOptionPane.showMessageDialog(this, "Customer Registered Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                    jtxt_Customer_Name.setText(null);
                    male.setSelected(true);
                    jtxt_Customer_Phone_number.setText(null);
                    jtxt_Customer__Email.setText(null);
                    jtxt_Customer_Address.setText(null);
                    jCmb_Customer_Status.setSelectedIndex(0);
                    jtxt_Customer_Name.grabFocus();
                    Customer_ID_Auto_Loader();
                    Customer_Table_Loader_Data();

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jCmb_Customer_StatusKeyPressed

    private void jtxt_Invoice_BarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {
            jtxt_Invoice_Item_Name.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jRadioButton2.isSelected()) {
            double PRICE = Double.parseDouble(jtxt_Invoice_Gross_Total.getText().toString());
            double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
            double tot = PRICE - DIS;
            jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot));
        } else {
            if (jCheckBox1.isSelected()) {
                double PRICE = Double.parseDouble(jlbl_Net_Discount.getText().toString());
                double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
                double tot = (PRICE * (DIS / 100));
                jlbl_Invoice_No1.setText(new DecimalFormat("0.00").format(tot));

                double QTY1 = Double.parseDouble(jtxt_Invoice_Selling_Price.getText().toString());
                double QTY2 = Double.parseDouble(jlbl_Invoice_No1.getText().toString());
                double tot1 = QTY1 - QTY2;
                jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot1));

            } else {

                double PRICE = Double.parseDouble(jlbl_Invoice_Gross_Total3.getText().toString());
                double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
                double tot = (PRICE * (DIS / 100));
                jlbl_Invoice_No1.setText(new DecimalFormat("0.00").format(tot));
                double QTY1 = Double.parseDouble(jlbl_Invoice_Gross_Total3.getText().toString());
                double QTY2 = Double.parseDouble(jlbl_Invoice_No1.getText().toString());
                double tot1 = QTY1 - QTY2;
                jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot1));
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyReleased

    }//GEN-LAST:event_jButton3KeyReleased

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered


    }//GEN-LAST:event_jButton3MouseEntered

    private void jtxt_Invoice_Item_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_NameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Invoice_Item_NameKeyPressed

    private void jtxt_Invoice_Item_NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_NameKeyReleased
        if (!jtxt_Invoice_Item_Name.getText().isEmpty()) {
            try {

                DefaultTableModel dtm1 = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
                dtm1.setRowCount(0);
                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM item WHERE item_name ='" + jtxt_Invoice_Item_Name.getText() + "' && status !='0'");
                while (rs.next()) {

                    jtxt_Invoice_Item_ID.setText(rs.getString("item_id"));
                    jtxt_Invoice_Barcode.setText(rs.getString("barcode"));

                    ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_stock GS ON G.grn_id=GS.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' && GS.stock_qty !='0'  && I.status !=0  ");
                    while (rs1.next()) {
                        Vector v = new Vector();
                        v.add(rs1.getString("GS.grn_id"));
                        v.add(rs1.getString("GS.item_id"));
                        v.add(rs1.getString("S.name"));
                        v.add(rs1.getString("GS.stock_qty"));
                        dtm1.addRow(v);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {

            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm.setRowCount(0);
            Clear_Invoice_Item();
        }
    }//GEN-LAST:event_jtxt_Invoice_Item_NameKeyReleased

    private void jtxt_Invoice_Item_NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_NameKeyTyped
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {

            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            jtbl_Invoice_GRN.setRowSelectionInterval(0, 0);

            int Selected_Row = jtbl_Invoice_GRN.getSelectedRow();
            try {
                jlbl_grn_id.setText(dtm.getValueAt(Selected_Row, 0).toString());
                String Supplier_Name = dtm.getValueAt(Selected_Row, 2).toString();

                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM supplier WHERE name ='" + Supplier_Name + "'");
                while (rs.next()) {
                    jlbl_grn_supplier_id.setText(rs.getString("supplier_id"));
                    Discount_Margin_Supplier.setText(rs.getString("code"));
                }

                ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_item GI ON G.grn_id = GI.grn_id INNER JOIN grn_stock GS ON GS.grn_id = G.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jlbl_grn_id.getText() + "' and G.supplier_id = '" + jlbl_grn_supplier_id.getText() + "' and GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' and GI.item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
                while (rs1.next()) {

                    jtxt_Invoice_Selling_Price.setText(rs1.getString("GS.selling_price"));
                    Discount_Margin.setText(rs1.getString("GI.margin_code"));
                    jlbl_Net_Discount.setText(rs1.getString("GI.supplier_price"));
                    jtxt_Invoice_Discount.setText(rs1.getString("GI.discount"));
                    jtxt_Invoice_Qty.grabFocus();
                    if (Discount_Margin.getText().isEmpty()) {
                        jCheckBox1.setVisible(false);
                    } else {
                        jCheckBox1.setVisible(true);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_jtxt_Invoice_Item_NameKeyTyped

    private void jtxt_Invoice_Item_IDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_IDKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {

            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            jtbl_Invoice_GRN.setRowSelectionInterval(0, 0);

            int Selected_Row = jtbl_Invoice_GRN.getSelectedRow();
            try {
                jlbl_grn_id.setText(dtm.getValueAt(Selected_Row, 0).toString());
                String Supplier_Name = dtm.getValueAt(Selected_Row, 2).toString();

                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM supplier WHERE name ='" + Supplier_Name + "'");
                while (rs.next()) {
                    jlbl_grn_supplier_id.setText(rs.getString("supplier_id"));
                    Discount_Margin_Supplier.setText(rs.getString("code"));
                }

                ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_item GI ON G.grn_id = GI.grn_id INNER JOIN grn_stock GS ON GS.grn_id = G.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jlbl_grn_id.getText() + "' and G.supplier_id = '" + jlbl_grn_supplier_id.getText() + "' and GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' and GI.item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
                while (rs1.next()) {

                    jtxt_Invoice_Selling_Price.setText(rs1.getString("GS.selling_price"));
                    Discount_Margin.setText(rs1.getString("GI.margin_code"));
                    jlbl_Net_Discount.setText(rs1.getString("GI.supplier_price"));
                    jtxt_Invoice_Discount.setText(rs1.getString("GI.discount"));
                    jtxt_Invoice_Qty.grabFocus();
                    if (Discount_Margin.getText().isEmpty()) {
                        jCheckBox1.setVisible(false);
                    } else {
                        jCheckBox1.setVisible(true);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            jtxt_Invoice_Qty.grabFocus();

        }
    }//GEN-LAST:event_jtxt_Invoice_Item_IDKeyPressed

    private void jtxt_Invoice_Item_IDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_IDKeyReleased
        if (!jtxt_Invoice_Item_ID.getText().isEmpty()) {
            try {
                DefaultTableModel dtm1 = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
                dtm1.setRowCount(0);
                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM item WHERE item_id ='" + jtxt_Invoice_Item_ID.getText() + "' && status !='0'");
                while (rs.next()) {

                    jtxt_Invoice_Barcode.setText(rs.getString("barcode"));
                    jtxt_Invoice_Item_Name.setText(rs.getString("item_name"));

                    ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_stock GS ON G.grn_id=GS.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' && GS.stock_qty !='0' && I.status !=0 ");
                    while (rs1.next()) {
                        Vector v = new Vector();
                        v.add(rs1.getString("GS.grn_id"));
                        v.add(rs1.getString("GS.item_id"));
                        v.add(rs1.getString("S.name"));
                        v.add(rs1.getString("GS.stock_qty"));
                        dtm1.addRow(v);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }

            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            jtbl_Invoice_GRN.setRowSelectionInterval(0, 0);

            int Selected_Row = jtbl_Invoice_GRN.getSelectedRow();

            System.out.println(Selected_Row);

            try {

                jlbl_grn_id.setText(dtm.getValueAt(Selected_Row, 0).toString());

                String Supplier_Name = dtm.getValueAt(Selected_Row, 2).toString();

                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM supplier WHERE name ='" + Supplier_Name + "'");
                while (rs.next()) {
                    jlbl_grn_supplier_id.setText(rs.getString("supplier_id"));
                    Discount_Margin_Supplier.setText(rs.getString("code"));
                }

                ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_item GI ON G.grn_id = GI.grn_id INNER JOIN grn_stock GS ON GS.grn_id = G.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jlbl_grn_id.getText() + "' and G.supplier_id = '" + jlbl_grn_supplier_id.getText() + "' and GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' and GI.item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
                while (rs1.next()) {

                    jtxt_Invoice_Selling_Price.setText(rs1.getString("GS.selling_price"));
                    Discount_Margin.setText(rs1.getString("GI.margin_code"));
                    jlbl_Net_Discount.setText(rs1.getString("GI.supplier_price"));
                    jtxt_Invoice_Discount.setText(rs1.getString("GI.discount"));
                    jtxt_Invoice_Qty.grabFocus();
                    if (Discount_Margin.getText().isEmpty()) {
                        jCheckBox1.setVisible(false);
                    } else {
                        jCheckBox1.setVisible(true);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm.setRowCount(0);
            Clear_Invoice_Item();
            jtxt_Invoice_Barcode.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_Item_IDKeyReleased

    private void jtxt_Invoice_CashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_CashActionPerformed
        jbtn_Invoice_Pay.grabFocus();
    }//GEN-LAST:event_jtxt_Invoice_CashActionPerformed

    private void jtxt_Invoice_BarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyTyped

    private void jtxt_Item_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Item_barcodeKeyReleased
        if (!jtxt_Item_barcode.getText().isEmpty()) {

            try {
                ResultSet rs = new DB().getData("select * from item where barcode = '" + jtxt_Item_barcode.getText() + "'");
                if (rs.next()) {
                    jlbl_Item_Item_ID.setText(rs.getString("item_id"));
                    jtxt_Item_Name.setText(rs.getString("item_name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Item_ID_Auto_Loader();
            jtxt_Item_Name.setText(null);
        }
    }//GEN-LAST:event_jtxt_Item_barcodeKeyReleased

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered

    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited

    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jtxt_Invoice_Item_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_IDKeyTyped
        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jtxt_Invoice_Item_IDKeyTyped

    private void jbtn_GRN_Print_GRN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_GRN_Print_GRN2ActionPerformed
        try {
            new DB().putData("insert into report (report_id,date1,date2,income,payment,shop_credit,customer_credit,profit_with_credit,payment_with_credit,reporting_date,reporting_time) values('" + lbl_Report_ID.getText() + "','" + lbl_Date2.getText() + "','" + lbl_Date2.getText() + "','" + jlbl_income.getText() + "','" + "0" + "','" + "0" + "','" + jlbl_Customer_Credit.getText() + "','" + jlbl_Income_Full_Amount.getText() + "','" + "0" + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "')");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        Report();
        Report_Loader();
    }//GEN-LAST:event_jbtn_GRN_Print_GRN2ActionPerformed

    private void jbtn_Clear_All1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_All1ActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.setRowCount(0);
        jlbl_Gross_Total.setText(null);
        jtxt_Net_Discount.setText(null);
        jtxt_Net_Total.setText(null);
        jtxt_Invoice_Cash.setText(null);
        jlbl_Invoice_Balance.setText(null);
    }//GEN-LAST:event_jbtn_Clear_All1ActionPerformed

    private void jbtn_Clear_One1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_One1ActionPerformed
        int selectedRow = jtbl_Invoice_Item.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.removeRow(selectedRow);
        Auto_Total_Calculate();
    }//GEN-LAST:event_jbtn_Clear_One1ActionPerformed

    private void jtxt_Invoice_Customer_Name1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Customer_Name1KeyTyped
        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            jtxt_Invoice_Cash.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_Customer_Name1KeyTyped

    private void jtxt_Invoice_Customer_Name1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Customer_Name1KeyReleased
        if (!jtxt_Invoice_Customer_Name1.getText().isEmpty()) {
            try {
                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM customer WHERE contact_no ='" + jtxt_Invoice_Customer_Name1.getText() + "'");
                while (rs.next()) {

                    int ID = Integer.parseInt(rs.getString("customer_id"));
                    jlbl_Invoice_Customer_ID.setText(ID + "");
                    jtxt_Invoice_Customer_Name.setText(rs.getString("name"));

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {
            jtxt_Invoice_Customer_Name.setText("My Customer");
            jlbl_Invoice_Customer_ID.setText("1000");
            jtxt_Invoice_Customer_Name1.setText(null);
        }
    }//GEN-LAST:event_jtxt_Invoice_Customer_Name1KeyReleased

    private void jtxt_Invoice_Customer_Name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Customer_Name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Invoice_Customer_Name1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (sb == null) {
            sb = new Short_Bill();
            sb.setVisible(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Discount_Margin;
    private javax.swing.JLabel Discount_Margin_Supplier;
    private javax.swing.JButton Jbtn_Credit_Settle_Customer_Pay;
    private javax.swing.JButton Jbtn_Credit_Settle_Shop_Pay;
    private javax.swing.JTable Jtbl_Credit_Settle_Customer;
    private javax.swing.JTable Jtbl_Credit_Settle_Shop;
    private javax.swing.JButton add;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JButton clear;
    private javax.swing.JRadioButton customer_cash;
    private javax.swing.JRadioButton customer_credit;
    private javax.swing.JButton decpoint;
    private javax.swing.JTextField display1;
    private javax.swing.JTextField display2;
    private javax.swing.JButton divide;
    private javax.swing.JButton eight;
    private javax.swing.JButton equals;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton five;
    private javax.swing.JButton four;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jCmb_Customer_Status;
    private javax.swing.JComboBox jCmb_Item_Status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JButton jbtn_ADD_Customer_Clear;
    private javax.swing.JButton jbtn_ADD_Customer_Save;
    private javax.swing.JButton jbtn_ADD_Customer_update;
    private javax.swing.JButton jbtn_ADD_New_Customer;
    private javax.swing.JButton jbtn_Clear_All;
    private javax.swing.JButton jbtn_Clear_All1;
    private javax.swing.JButton jbtn_Clear_One;
    private javax.swing.JButton jbtn_Clear_One1;
    private javax.swing.JButton jbtn_GRN_Print_GRN2;
    private javax.swing.JButton jbtn_Invoice_Pay;
    private javax.swing.JButton jbtn_Item_Clear;
    private javax.swing.JButton jbtn_Item_Save;
    private javax.swing.JButton jbtn_Item_Update;
    private javax.swing.JLabel jlbl_Customer_Credit;
    private javax.swing.JLabel jlbl_Customer_ID;
    private javax.swing.JLabel jlbl_Customer_ID_Check;
    private javax.swing.JLabel jlbl_Gross_Total;
    private javax.swing.JLabel jlbl_Income_Full_Amount;
    private javax.swing.JLabel jlbl_Invoice_Balance;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID1;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID2;
    private javax.swing.JLabel jlbl_Invoice_Gross_Total3;
    private javax.swing.JLabel jlbl_Invoice_No;
    private javax.swing.JLabel jlbl_Invoice_No1;
    private javax.swing.JLabel jlbl_Item_Item_ID;
    private javax.swing.JLabel jlbl_Net_Discount;
    private javax.swing.JLabel jlbl_grn_id;
    private javax.swing.JLabel jlbl_grn_supplier_id;
    private javax.swing.JLabel jlbl_income;
    private javax.swing.JTable jtbl_Customer;
    private javax.swing.JTable jtbl_Invoice_GRN;
    private javax.swing.JTable jtbl_Invoice_Item;
    private javax.swing.JTable jtbl_Item;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Ballence_Credit;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Contact_Number;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Credit_Price;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Customer_Name;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Date;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_ID;
    private javax.swing.JLabel jtxt_Credit_Settle_Customer_Invoice_ID;
    private javax.swing.JTextField jtxt_Credit_Settle_Customer_Pay_Price;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_Ballence_Credit;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_Credit_Price;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_GRN_Date;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_GRN_ID;
    private javax.swing.JTextField jtxt_Credit_Settle_Shop_Pay_Price;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_Supplier_ID;
    private javax.swing.JLabel jtxt_Credit_Settle_Shop_Supplier_Name;
    private javax.swing.JTextField jtxt_Customer_Address;
    private javax.swing.JTextField jtxt_Customer_Name;
    private javax.swing.JTextField jtxt_Customer_Phone_number;
    private javax.swing.JTextField jtxt_Customer_Search;
    private javax.swing.JTextField jtxt_Customer__Email;
    private javax.swing.JTextField jtxt_Invoice_Barcode;
    private javax.swing.JTextField jtxt_Invoice_Cash;
    private javax.swing.JLabel jtxt_Invoice_Customer_Name;
    private javax.swing.JTextField jtxt_Invoice_Customer_Name1;
    private javax.swing.JTextField jtxt_Invoice_Discount;
    private javax.swing.JLabel jtxt_Invoice_Gross_Total;
    private javax.swing.JTextField jtxt_Invoice_Item_ID;
    private javax.swing.JTextField jtxt_Invoice_Item_Name;
    private javax.swing.JTextField jtxt_Invoice_Qty;
    private javax.swing.JLabel jtxt_Invoice_Selling_Price;
    private javax.swing.JTextField jtxt_Item_Name;
    private javax.swing.JTextField jtxt_Item_Search;
    private javax.swing.JTextField jtxt_Item_barcode;
    private javax.swing.JTextField jtxt_Net_Discount;
    private javax.swing.JLabel jtxt_Net_Total;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Date1;
    private javax.swing.JLabel lbl_Date2;
    private javax.swing.JLabel lbl_Report_ID;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JLabel lbl_Time1;
    private javax.swing.JLabel lbl_user_name;
    private javax.swing.JRadioButton male;
    private javax.swing.JButton multiply;
    private javax.swing.JButton nine;
    private javax.swing.JButton one;
    private javax.swing.JButton percent;
    private javax.swing.JButton reset;
    private javax.swing.JButton seven;
    private javax.swing.JButton six;
    private javax.swing.JRadioButton sub_total_Number_Discount;
    private javax.swing.JRadioButton sub_total_Precentage_Discount;
    private javax.swing.JButton subtract;
    private javax.swing.JButton three;
    private javax.swing.JButton two;
    private javax.swing.JButton zero;
    // End of variables declaration//GEN-END:variables

    private void DateandTime() {
        Timer t = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat sdf_Time = new SimpleDateFormat("hh:mm:ss aa");
                SimpleDateFormat sdf_Date = new SimpleDateFormat("YYYY  MMMM  dd EEEE");
                SimpleDateFormat sdf_Date1 = new SimpleDateFormat("YYYY-MM-dd");
                String time = sdf_Time.format(d);
                String Date = sdf_Date.format(d);
                String Date1 = sdf_Date1.format(d);
                lbl_Time.setText(time);
                lbl_Date.setText(Date);
                lbl_Date1.setText(Date1);
            }
        });
        t.start();
    }

    private void Invoice_ID_Auto_Loader() {
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT MAX(invoice_id) AS LargestID FROM invoice;");
            if (rs.next()) {
                String id = rs.getString("LargestID");
                jlbl_Invoice_No.setText(Integer.parseInt(id) + 1 + "");
            }
        } catch (Exception e) {
            System.out.println(e);
            jlbl_Invoice_No.setText("10000000");
            jtxt_Invoice_Barcode.grabFocus();
        }
    }

    private void Item_ID_Auto_Loader() {
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT MAX(item_id) AS LargestID FROM item;");
            if (rs.next()) {
                String id = rs.getString("LargestID");
                jlbl_Item_Item_ID.setText(Integer.parseInt(id) + 1 + "");
            }
        } catch (Exception e) {
            System.out.println(e);
            jlbl_Item_Item_ID.setText("10000");
            jtxt_Item_barcode.grabFocus();
        }
    }

    private void Customer_ID_Auto_Loader() {
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT MAX(customer_id) AS LargestID FROM customer;");
            if (rs.next()) {
                String id = rs.getString("LargestID");
                jlbl_Customer_ID.setText(Integer.parseInt(id) + 1 + "");
            }
        } catch (Exception e) {
            System.out.println(e);
            jlbl_Customer_ID.setText("1000");
        }
    }

    private void Report_Loader() {
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT MAX(report_id) AS LargestID FROM report;");
            if (rs.next()) {
                String id = rs.getString("LargestID");
                lbl_Report_ID.setText(Integer.parseInt(id) + 1 + "");
            }
        } catch (Exception e) {
            System.out.println(e);
            lbl_Report_ID.setText("1000000");
        }
    }

    private void Customer_Table_Loader_Data() {
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Customer.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet set = new Database_Connection.DB().getData("SELECT * FROM customer where customer_id !='1000'");

            while (set.next()) {
                Vector v = new Vector();
                v.add(set.getString("customer_id"));
                v.add(set.getString("name"));
                v.add(set.getString("gender"));
                v.add(set.getString("contact_no"));
                v.add(set.getString("email"));
                v.add(set.getString("address"));
                if (set.getString("status").equals("1")) {
                    v.add("Active");
                }
                if (set.getString("status").equals("0")) {
                    v.add("Deactive");
                }
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Item_Table_Loader_Data() {
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Item.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet set = new DB().getData("Select * from item");

            while (set.next()) {
                Vector v = new Vector();
                v.add(set.getString("item_id"));
                v.add(set.getString("barcode"));
                v.add(set.getString("item_name"));
                if (set.getString("status").equals("1")) {
                    v.add("Active");
                }
                if (set.getString("status").equals("0")) {
                    v.add("Deactive");
                }
                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    private void Auto_Total_Calculate() {
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();

        double Total_Amount = 0;

        for (int i = 0; i < jtbl_Invoice_Item.getRowCount(); i++) {
            String Item_value = dtm.getValueAt(i, 7).toString();
            double Total = Double.parseDouble(Item_value);
            Total_Amount += Total;
            jlbl_Gross_Total.setText(new DecimalFormat("0.00").format(Total_Amount));
            jtxt_Net_Total.setText(new DecimalFormat("0.00").format(Total_Amount));

        }
    }

    private void Shop_Credit_Table_Loader_Data() {
        DefaultTableModel dtm = (DefaultTableModel) Jtbl_Credit_Settle_Shop.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet set = new DB().getData("SELECT * FROM grn G INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.update_credit_price !='0' ");

            while (set.next()) {
                Vector v = new Vector();
                v.add(set.getString("G.grn_id"));
                v.add(set.getString("G.date"));
                v.add(set.getString("G.supplier_id"));
                v.add(set.getString("S.name"));
                v.add(set.getString("G.update_credit_price"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    private void ReportInvoice() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Invoice.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jlbl_Invoice_No.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ReportInvoiceDiscount() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Invoice_Discount.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jlbl_Invoice_No.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ReportInvoiceCredit() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Invoice_Credit_WithOut_Discount.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jlbl_Invoice_No.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ReportInvoiceCreditDiscount() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Invoice_Credit.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jlbl_Invoice_No.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Report_Customer_Credit() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Customer_Credit_Payment.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jtxt_Credit_Settle_Customer_Invoice_ID.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void Report() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Report_Admin.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("Report_ID", Integer.parseInt(lbl_Report_ID.getText()));
            JasperReport jasperReport = JasperCompileManager.compileReport(reportSourse);
            Connection connseacrch = new Database_Connection.DB().myCon();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connseacrch);
            JasperPrintManager.printReport(jasperPrint, false);
            connseacrch.close();
        } catch (Exception e) {
        }
    }

    private void income() {
        try {

            ResultSet rs = new DB().getData("select * from invoice_payment where invoice_payment.date = '" + lbl_Date2.getText() + "'");
            double tot = 0;
            while (rs.next()) {
                tot += Double.valueOf(rs.getString("cash")) - Double.valueOf(rs.getString("balance"));

            }
            jlbl_income.setText(String.valueOf(new DecimalFormat("0.00").format(tot)));
            invtot = tot;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Customer_Credit() {
        try {
            ResultSet rs = new DB().getData("select customer_credit  from invoice where date = '" + lbl_Date2.getText() + "'");

            double tot = 0;
            while (rs.next()) {
                tot += (Double.valueOf(rs.getString("customer_credit")));
            }
            jlbl_Customer_Credit.setText(String.valueOf(new DecimalFormat("0.00").format(tot)));
            customer_credits = tot;
        } catch (Exception e) {
        }
    }

    private void Chart() {

        String Income = jlbl_income.getText();
        String Customers_Credit = jlbl_Customer_Credit.getText();

        DefaultPieDataset PieDataset = new DefaultPieDataset();
        PieDataset.setValue("Cash Amount", new Double(Income));
        PieDataset.setValue("Customers Credit", new Double(Customers_Credit));

        JFreeChart chart = ChartFactory.createPieChart("", PieDataset, true, true, true);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("", chart);

        ChartPanel chartpanel = new ChartPanel(chart);
        jPanel16.removeAll();
        jPanel16.add(chartpanel, BorderLayout.CENTER);
        jPanel16.validate();

        double QTY = Double.parseDouble(jlbl_income.getText().toString());
        double UP = Double.parseDouble(jlbl_Customer_Credit.getText().toString());
        double tot = QTY + UP;
        jlbl_Income_Full_Amount.setText(new DecimalFormat("0.00").format(tot));

    }

    private void item_text_clean() {
        jtxt_Item_barcode.setText(null);
        jtxt_Item_Name.setText(null);
        jCmb_Item_Status.setSelectedIndex(0);
        jtxt_Item_Search.setText(null);
        Item_ID_Auto_Loader();
        Item_Table_Loader_Data();
    }

    private void customer_text_clean() {
        jtxt_Customer_Name.setText(null);
        male.setSelected(true);
        jtxt_Customer_Phone_number.setText(null);
        jtxt_Customer__Email.setText(null);
        jtxt_Customer_Address.setText(null);
        jCmb_Customer_Status.setSelectedIndex(0);
        jtxt_Customer_Search.setText(null);
        Customer_ID_Auto_Loader();
        Customer_Table_Loader_Data();
    }

    private void Customer_Credit_Table_Loader_Data() {
        DefaultTableModel dtm = (DefaultTableModel) Jtbl_Credit_Settle_Customer.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet set = new DB().getData("SELECT * FROM invoice I INNER JOIN customer C ON I.customer_id = C.customer_id WHERE I.customer_credit !='0' ");

            while (set.next()) {
                Vector v = new Vector();
                v.add(set.getString("I.invoice_id"));
                v.add(set.getString("I.date"));
                v.add(set.getString("I.customer_id"));
                v.add(set.getString("C.name"));
                v.add(set.getString("C.contact_no"));
                v.add(set.getString("I.customer_credit"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    private void Invoice_Cash() {
        if (!jtxt_Invoice_Cash.getText().isEmpty()) {
            if (customer_cash.isSelected()) {
                double QTY = Double.parseDouble(jtxt_Invoice_Cash.getText().toString());
                double UP = Double.parseDouble(jtxt_Net_Total.getText().toString());
                double tot = QTY - UP;
                jlbl_Invoice_Balance.setText(new DecimalFormat("0.00").format(tot));
            } else {
                double QTY = Double.parseDouble(jtxt_Net_Total.getText().toString());
                double UP = Double.parseDouble(jtxt_Invoice_Cash.getText().toString());
                double tot = QTY - UP;
                jlbl_Invoice_Balance.setText(new DecimalFormat("0.00").format(tot));
            }
        } else {
            jlbl_Invoice_Balance.setText("0.00");
        }
    }

    private void invoice_barcode() {
        try {
            DefaultTableModel dtm1 = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm1.setRowCount(0);
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM item WHERE barcode ='" + jtxt_Invoice_Barcode.getText() + "' && status !='0'");
            while (rs.next()) {

                jtxt_Invoice_Item_ID.setText(rs.getString("item_id"));
                jtxt_Invoice_Item_Name.setText(rs.getString("item_name"));

                ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_stock GS ON G.grn_id=GS.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' && GS.stock_qty !='0' && I.status !=0 ");
                while (rs1.next()) {
                    Vector v = new Vector();
                    v.add(rs1.getString("GS.grn_id"));
                    v.add(rs1.getString("GS.item_id"));
                    v.add(rs1.getString("S.name"));
                    v.add(rs1.getString("GS.stock_qty"));
                    dtm1.addRow(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
        jtbl_Invoice_GRN.setRowSelectionInterval(0, 0);

        int Selected_Row = jtbl_Invoice_GRN.getSelectedRow();

        System.out.println(Selected_Row);

        try {

            jlbl_grn_id.setText(dtm.getValueAt(Selected_Row, 0).toString());

            String Supplier_Name = dtm.getValueAt(Selected_Row, 2).toString();

            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM supplier WHERE name ='" + Supplier_Name + "'");
            while (rs.next()) {
                jlbl_grn_supplier_id.setText(rs.getString("supplier_id"));
                Discount_Margin_Supplier.setText(rs.getString("code"));
            }

            ResultSet rs1 = new Database_Connection.DB().getData("SELECT * FROM grn G INNER JOIN grn_item GI ON G.grn_id = GI.grn_id INNER JOIN grn_stock GS ON GS.grn_id = G.grn_id INNER JOIN item I ON GS.item_id = I.item_id INNER JOIN supplier S ON G.supplier_id = S.supplier_id WHERE G.grn_id = '" + jlbl_grn_id.getText() + "' and G.supplier_id = '" + jlbl_grn_supplier_id.getText() + "' and GS.item_id = '" + jtxt_Invoice_Item_ID.getText() + "' and GI.item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
            while (rs1.next()) {

                jtxt_Invoice_Selling_Price.setText(rs1.getString("GS.selling_price"));
                    Discount_Margin.setText(rs1.getString("GI.margin_code"));
                    jlbl_Net_Discount.setText(rs1.getString("GI.supplier_price"));
                    jtxt_Invoice_Discount.setText(rs1.getString("GI.discount"));
                    jtxt_Invoice_Qty.grabFocus();
                    if (Discount_Margin.getText().isEmpty()) {
                        jCheckBox1.setVisible(false);
                    } else {
                        jCheckBox1.setVisible(true);
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        jtxt_Invoice_Qty.grabFocus();
    }

    private void Net_Discount() {
        if (!jtxt_Net_Discount.getText().isEmpty()) {
            if (sub_total_Number_Discount.isSelected()) {
                double QTY = Double.parseDouble(jlbl_Gross_Total.getText().toString());
                double UP = Double.parseDouble(jtxt_Net_Discount.getText().toString());
                double tot = QTY - UP;
                jtxt_Net_Total.setText(new DecimalFormat("0.00").format(tot));
            } else {
                double QTY = Double.parseDouble(jlbl_Gross_Total.getText().toString());
                double UP = Double.parseDouble(jtxt_Net_Discount.getText().toString());
                double tot = QTY / 100 * UP;
                jlbl_Net_Discount.setText(new DecimalFormat("0.00").format(tot));

                double QTY1 = Double.parseDouble(jlbl_Gross_Total.getText().toString());
                double QTY2 = Double.parseDouble(jlbl_Net_Discount.getText().toString());
                double tot1 = QTY1 - QTY2;
                jtxt_Net_Total.setText(new DecimalFormat("0.00").format(tot1));
            }

            double QTY1 = Double.parseDouble(jtxt_Invoice_Cash.getText().toString());
            double QTY2 = Double.parseDouble(jtxt_Net_Total.getText().toString());
            double tot1 = QTY1 - QTY2;
            jlbl_Invoice_Balance.setText(new DecimalFormat("0.00").format(tot1));

        } else {
            Auto_Total_Calculate();
            double QTY1 = Double.parseDouble(jtxt_Invoice_Cash.getText().toString());
            double QTY2 = Double.parseDouble(jtxt_Net_Total.getText().toString());
            double tot1 = QTY1 - QTY2;
            jlbl_Invoice_Balance.setText(new DecimalFormat("0.00").format(tot1));
        }
    }

    private void Gross_Total() {

        if (!jtxt_Invoice_Qty.getText().isEmpty()) {
            try {
                double DB_Qty = 0;
                ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM grn_stock where grn_id = '" + jlbl_grn_id.getText() + "' and item_id = '" + jtxt_Invoice_Item_ID.getText() + "'");
                while (rs.next()) {
                    DB_Qty = Double.parseDouble(rs.getString("grn_stock.stock_qty").toString());
                    System.out.println(DB_Qty);
                    if (DB_Qty >= Double.parseDouble(jtxt_Invoice_Qty.getText().toString())) {
                        System.out.println("Ok");

                        double QTY = Double.parseDouble(jtxt_Invoice_Selling_Price.getText().toString());
                        double UP = Double.parseDouble(jtxt_Invoice_Qty.getText().toString());
                        double tot = QTY * UP;
                        jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot));
                        jlbl_Invoice_Gross_Total3.setText(new DecimalFormat("0.00").format(tot));

                    } else {
                        JOptionPane.showMessageDialog(this, "This Quantity Not Available in Stock", "Information", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Worng");
                        jtxt_Invoice_Qty.setText(rs.getString("grn_stock.stock_qty"));

                        double QTY = Double.parseDouble(jtxt_Invoice_Selling_Price.getText().toString());
                        double UP = Double.parseDouble(jtxt_Invoice_Qty.getText().toString());
                        double tot = QTY * UP;
                        jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("E");
            }

            if (!jtxt_Invoice_Discount.getText().isEmpty()) {
                if (jRadioButton2.isSelected()) {
                    double PRICE = Double.parseDouble(jtxt_Invoice_Gross_Total.getText().toString());
                    double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
                    double tot = PRICE - DIS;
                    jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot));
                } else {

                    if (jCheckBox1.isSelected()) {
                        double PRICE = Double.parseDouble(jlbl_Net_Discount.getText().toString());
                        double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
                        double tot = (PRICE * (DIS / 100));
                        jlbl_Invoice_No1.setText(new DecimalFormat("0.00").format(tot));

                        double QTY1 = Double.parseDouble(jtxt_Invoice_Selling_Price.getText().toString());
                        double QTY2 = Double.parseDouble(jlbl_Invoice_No1.getText().toString());
                        double tot1 = QTY1 - QTY2;
                        jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot1));

                    } else {

                        double PRICE = Double.parseDouble(jlbl_Invoice_Gross_Total3.getText().toString());
                        double DIS = Double.parseDouble(jtxt_Invoice_Discount.getText().toString());
                        double tot = (PRICE * (DIS / 100));
                        jlbl_Invoice_No1.setText(new DecimalFormat("0.00").format(tot));
                        double QTY1 = Double.parseDouble(jlbl_Invoice_Gross_Total3.getText().toString());
                        double QTY2 = Double.parseDouble(jlbl_Invoice_No1.getText().toString());
                        double tot1 = QTY1 - QTY2;
                        jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot1));
                    }

                }

            } else {
                double QTY = Double.parseDouble(jtxt_Invoice_Selling_Price.getText().toString());
                double UP = Double.parseDouble(jtxt_Invoice_Qty.getText().toString());
                double tot = QTY * UP;
                jtxt_Invoice_Gross_Total.setText(new DecimalFormat("0.00").format(tot));
                jlbl_Invoice_No1.setText("0.00");
            }

        } else {
            jtxt_Invoice_Gross_Total.setText("0.00");
        }

    }

    private void Gross_To_Table() {
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();

        if (!jtxt_Invoice_Item_ID.getText().isEmpty() && !jtxt_Invoice_Qty.getText().isEmpty() && !jtxt_Invoice_Selling_Price.getText().isEmpty() && !jtxt_Invoice_Gross_Total.getText().isEmpty()) {
            if (Double.parseDouble(jtxt_Invoice_Qty.getText()) > 0) {

                if (!jtxt_Invoice_Discount.getText().isEmpty()) {
                    if (jRadioButton2.isSelected()) {
                        try {
                            Vector v = new Vector();
                            v.add(jtxt_Invoice_Item_ID.getText());
                            v.add(jtxt_Invoice_Item_Name.getText());
                            v.add(jlbl_grn_id.getText());
                            v.add(jlbl_grn_supplier_id.getText());
                            v.add(jtxt_Invoice_Selling_Price.getText());
                            v.add(jtxt_Invoice_Qty.getText());
                            v.add(jtxt_Invoice_Discount.getText());
                            v.add(jtxt_Invoice_Gross_Total.getText());
                            dtm.addRow(v);

                            Auto_Total_Calculate();
                            Clear_Invoice_Item();

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else {
                        try {
                            Vector v = new Vector();
                            v.add(jtxt_Invoice_Item_ID.getText());
                            v.add(jtxt_Invoice_Item_Name.getText());
                            v.add(jlbl_grn_id.getText());
                            v.add(jlbl_grn_supplier_id.getText());
                            v.add(jtxt_Invoice_Selling_Price.getText());
                            v.add(jtxt_Invoice_Qty.getText());
                            v.add(jlbl_Invoice_No1.getText());
                            v.add(jtxt_Invoice_Gross_Total.getText());
                            dtm.addRow(v);

                            Auto_Total_Calculate();
                            Clear_Invoice_Item();

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                } else {
                    try {
                        Vector v = new Vector();
                        v.add(jtxt_Invoice_Item_ID.getText());
                        v.add(jtxt_Invoice_Item_Name.getText());
                        v.add(jlbl_grn_id.getText());
                        v.add(jlbl_grn_supplier_id.getText());
                        v.add(jtxt_Invoice_Selling_Price.getText());
                        v.add(jtxt_Invoice_Qty.getText());
                        v.add("0.00");
                        v.add(jtxt_Invoice_Gross_Total.getText());
                        dtm.addRow(v);

                        Auto_Total_Calculate();
                        Clear_Invoice_Item();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Can't Add Product Quantity. \n Please Infrom Supplier.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void Invoice_item() {

        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        DefaultTableModel dtm1 = (DefaultTableModel) jtbl_Invoice_GRN.getModel();

        for (int i = 0; i < jtbl_Invoice_Item.getRowCount(); i++) {
            try {
                String Pro_ID = dtm.getValueAt(i, 0).toString();

                new Database_Connection.DB().putData("INSERT INTO invoice_item (invoice_id, qty, discount, total, item_id, item_price,grn_id) VALUES('" + jlbl_Invoice_No.getText() + "','" + Double.parseDouble(dtm.getValueAt(i, 5).toString()) + "','" + Double.parseDouble(dtm.getValueAt(i, 6).toString()) + "','" + Double.parseDouble(dtm.getValueAt(i, 7).toString()) + "','" + Pro_ID + "','" + Double.parseDouble(dtm.getValueAt(i, 4).toString()) + "','" + Integer.parseInt(dtm.getValueAt(i, 2).toString()) + "')");

                String GR_ID = dtm.getValueAt(i, 2).toString();
                String SU_ID = dtm.getValueAt(i, 3).toString();
                String Item_ID = dtm.getValueAt(i, 0).toString();

                try {
                    ResultSet rs = new Database_Connection.DB().getData("SELECT stock_qty FROM grn_stock WHERE grn_id = '" + Integer.parseInt(GR_ID) + "' and item_id='" + Integer.parseInt(Pro_ID) + "'");
                    while (rs.next()) {
                        double Table_PQTY = Double.parseDouble(dtm.getValueAt(i, 5).toString());
                        double Database_PQTY = Double.parseDouble(rs.getString("stock_qty").toString());
                        double New_PQTY = Database_PQTY - Table_PQTY;

                        new Database_Connection.DB().putData("Update grn_stock Set stock_qty = '" + New_PQTY + "' WHERE grn_id = '" + GR_ID + "' && item_id = '" + Item_ID + "' ");
                    }
                } catch (Exception e) {
                    System.out.println("Error 2 .......");
                    e.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println("Error 3 ..... ");
                e.printStackTrace();
            }
        }
    }

    private void Clear_Invoice() {
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        DefaultTableModel dtm1 = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
        dtm.setRowCount(0);
        Invoice_ID_Auto_Loader();
        jlbl_Gross_Total.setText(null);
        jtxt_Net_Discount.setText("0");
        jtxt_Invoice_Customer_Name.setText("Danuma Customer");
        jlbl_Invoice_Customer_ID.setText("1000");
        jtxt_Invoice_Customer_Name1.setText(null);
        jtxt_Net_Total.setText(null);
        jtxt_Invoice_Cash.setText(null);
        jlbl_Invoice_Balance.setText(null);
        customer_cash.setSelected(true);
        dtm1.setRowCount(0);
        Clear_Invoice_Item();
        income();
        Customer_Credit();
        Chart();
        jtxt_Invoice_Barcode.grabFocus();

    }

    private void Clear_Invoice_Item() {
        jtxt_Invoice_Barcode.setText(null);
        jtxt_Invoice_Item_ID.setText(null);
        jlbl_grn_id.setText(null);
        jlbl_grn_supplier_id.setText(null);
        jtxt_Invoice_Item_Name.setText(null);
        jtxt_Invoice_Selling_Price.setText(null);
        jtxt_Invoice_Qty.setText(null);
        jtxt_Invoice_Gross_Total.setText("0.00");
        jlbl_Invoice_No1.setText(null);
        jtxt_Invoice_Discount.setText(null);
        Discount_Margin_Supplier.setText(null);
        Discount_Margin.setText(null);
        jCheckBox1.setVisible(false);
    }

    private void System_dispose() {
        this.dispose();
    }

    private void check_box() {
        jCheckBox1.setVisible(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon.png")));
    }

}
