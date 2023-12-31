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
public class Short_Bill extends javax.swing.JFrame {

    private String gen;
    private String pay;

    static Customer_Add ca;

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
    public Short_Bill() {
        initComponents();
        DateandTime();
        setIcon();
        lbl_user_name.setText(Login.User_Name);
        Invoice_ID_Auto_Loader();
        Invoice_Item_Name_Autocomplete();

        Auto_Total_Calculate();

        jtbl_Invoice_GRN.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jtbl_Invoice_Item.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));

        check_box();

        Action PrintAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Invoice_Cash.grabFocus();
            }
        };
        String key = "print";
        jPanel7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), key);
        jPanel7.getActionMap().put(key, PrintAction);

        //////////////
        Action PrintAction1 = new AbstractAction("print1") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Invoice_Barcode.grabFocus();
            }
        };
        String key1 = "print1";
        jPanel7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), key1);
        jPanel7.getActionMap().put(key1, PrintAction1);

        //////////////
        Action PrintAction2 = new AbstractAction("print2") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxt_Net_Discount.grabFocus();
            }
        };
        String key2 = "print2";
        jPanel7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), key2);
        jPanel7.getActionMap().put(key2, PrintAction2);

        //////////////
        Action PrintAction4 = new AbstractAction("print4") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Customer_Return().setVisible(true);
                System_dispose();
            }
        };
        String key4 = "print4";
        jPanel7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK), key4);
        jPanel7.getActionMap().put(key4, PrintAction4);

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
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Time1 = new javax.swing.JLabel();
        lbl_user_name = new javax.swing.JLabel();
        lbl_Date1 = new javax.swing.JLabel();
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
                .addGap(153, 153, 153)
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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Time1)
                    .addComponent(lbl_user_name))
                .addGap(30, 30, 30))
        );

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

        customer_credit.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customer_credit.setText("Credit");
        customer_credit.setContentAreaFilled(false);
        customer_credit.setFocusable(false);
        customer_credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_creditActionPerformed(evt);
            }
        });

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

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("0.0");
        jRadioButton2.setContentAreaFilled(false);
        jRadioButton2.setFocusable(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        sub_total_Number_Discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sub_total_Number_Discount.setText("0.0");
        sub_total_Number_Discount.setContentAreaFilled(false);
        sub_total_Number_Discount.setFocusable(false);
        sub_total_Number_Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_total_Number_DiscountActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
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
                                        .addGap(0, 0, Short.MAX_VALUE))))
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
                        .addGap(3, 3, 3)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(238, 238, 238)))
                        .addGap(104, 104, 104))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtn_Clear_All1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtn_Clear_One1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxt_Invoice_Customer_Name1)
                                    .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbl_Invoice_Customer_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jlbl_Invoice_Customer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtn_ADD_New_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addGap(83, 83, 83)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(customer_credit)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(customer_cash)
                                            .addGap(10, 10, 10)
                                            .addComponent(jtxt_Invoice_Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(10, 10, 10)
                                            .addComponent(jlbl_Invoice_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtxt_Net_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtn_Invoice_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(sub_total_Number_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sub_total_Precentage_Discount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxt_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbl_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(551, 551, 551)
                .addComponent(jlbl_grn_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_grn_supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtn_Clear_All, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_Clear_One, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbl_Invoice_No, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(11, 11, 11)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxt_Invoice_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Discount_Margin_Supplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Discount_Margin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Invoice_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbl_grn_supplier_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                            .addComponent(jlbl_grn_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
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
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtn_ADD_New_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlbl_Invoice_Customer_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(sub_total_Precentage_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbl_Gross_Total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jtxt_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9))
                                    .addComponent(sub_total_Number_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
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
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jbtn_Invoice_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Admin_Main.sb = null;
        User_Main.sb = null;
        this.dispose();
        System.gc();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered

    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited

    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jtxt_Invoice_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountActionPerformed

    }//GEN-LAST:event_jtxt_Invoice_DiscountActionPerformed

    private void jtxt_Invoice_DiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {
            Gross_To_Table();
            jtxt_Invoice_Barcode.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyPressed

    private void jtxt_Invoice_DiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyReleased
        Gross_Total();
    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyReleased

    private void jtxt_Invoice_DiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_DiscountKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_DiscountKeyTyped

    private void jtxt_Invoice_BarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyPressed
        char c1 = evt.getKeyChar();
        if (c1 == evt.VK_ENTER) {
            jtxt_Invoice_Item_Name.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyPressed

    private void jtxt_Invoice_BarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyReleased
        if (!jtxt_Invoice_Barcode.getText().isEmpty()) {
            invoice_barcode();
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm.setRowCount(0);
            Clear_Invoice_Item();
        }
    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyReleased

    private void jtxt_Invoice_BarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyTyped

    private void jtxt_Net_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Net_DiscountActionPerformed
        jtxt_Invoice_Cash.grabFocus();
    }//GEN-LAST:event_jtxt_Net_DiscountActionPerformed

    private void jtxt_Net_DiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Net_DiscountKeyReleased
        Net_Discount();
    }//GEN-LAST:event_jtxt_Net_DiscountKeyReleased

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

    private void jtxt_Invoice_QtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyActionPerformed

    }//GEN-LAST:event_jtxt_Invoice_QtyActionPerformed

    private void jtxt_Invoice_QtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyPressed
        if (!jtxt_Invoice_Qty.getText().isEmpty()) {
            char c1 = evt.getKeyChar();
            if (c1 == evt.VK_ENTER) {
                jtxt_Invoice_Discount.grabFocus();
            }
        } else {
        }
    }//GEN-LAST:event_jtxt_Invoice_QtyKeyPressed

    private void jtxt_Invoice_QtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyReleased

        Gross_Total();
    }//GEN-LAST:event_jtxt_Invoice_QtyKeyReleased

    private void jtxt_Invoice_QtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_QtyKeyTyped

    }//GEN-LAST:event_jtxt_Invoice_QtyKeyTyped

    private void jtxt_Invoice_CashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_CashActionPerformed
        jbtn_Invoice_Pay.grabFocus();
    }//GEN-LAST:event_jtxt_Invoice_CashActionPerformed

    private void jtxt_Invoice_CashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_CashKeyReleased
        Invoice_Cash();
    }//GEN-LAST:event_jtxt_Invoice_CashKeyReleased

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
                        this.dispose();

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
                        this.dispose();
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
                            this.dispose();

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
                            this.dispose();
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
                        this.dispose();

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
                        this.dispose();
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
                            this.dispose();

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
                            this.dispose();
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

    private void jbtn_Clear_OneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_OneActionPerformed
        int selectedRow = jtbl_Invoice_Item.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.removeRow(selectedRow);
        Auto_Total_Calculate();
    }//GEN-LAST:event_jbtn_Clear_OneActionPerformed

    private void jbtn_Clear_AllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Clear_AllActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_Item.getModel();
        dtm.setRowCount(0);
        jlbl_Gross_Total.setText(null);
        jtxt_Net_Discount.setText(null);
        jtxt_Net_Total.setText(null);
        jtxt_Invoice_Cash.setText(null);
        jlbl_Invoice_Balance.setText(null);
    }//GEN-LAST:event_jbtn_Clear_AllActionPerformed

    private void jbtn_ADD_New_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_ADD_New_CustomerActionPerformed

        if (ca == null) {
            ca = new Customer_Add();
            ca.setVisible(true);
        }
    }//GEN-LAST:event_jbtn_ADD_New_CustomerActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Customer_Return().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

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

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        Gross_Total();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        Gross_Total();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void sub_total_Number_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_total_Number_DiscountActionPerformed
        Net_Discount();
    }//GEN-LAST:event_sub_total_Number_DiscountActionPerformed

    private void sub_total_Precentage_DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_total_Precentage_DiscountActionPerformed
        Net_Discount();
    }//GEN-LAST:event_sub_total_Precentage_DiscountActionPerformed

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

    private void jtxt_Invoice_Customer_Name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Customer_Name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Invoice_Customer_Name1ActionPerformed

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

    private void jtxt_Invoice_Customer_Name1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Customer_Name1KeyTyped
        char c = evt.getKeyChar();
        if (c == evt.VK_ENTER) {
            jtxt_Invoice_Cash.grabFocus();
        }
    }//GEN-LAST:event_jtxt_Invoice_Customer_Name1KeyTyped

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

    private void jtxt_Invoice_Item_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Item_IDKeyTyped
        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jtxt_Invoice_Item_IDKeyTyped

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
            java.util.logging.Logger.getLogger(Short_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Short_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Short_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Short_Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Short_Bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Discount_Margin;
    private javax.swing.JLabel Discount_Margin_Supplier;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JRadioButton customer_cash;
    private javax.swing.JRadioButton customer_credit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtn_ADD_New_Customer;
    private javax.swing.JButton jbtn_Clear_All;
    private javax.swing.JButton jbtn_Clear_All1;
    private javax.swing.JButton jbtn_Clear_One;
    private javax.swing.JButton jbtn_Clear_One1;
    private javax.swing.JButton jbtn_Invoice_Pay;
    private javax.swing.JLabel jlbl_Customer_ID_Check;
    private javax.swing.JLabel jlbl_Gross_Total;
    private javax.swing.JLabel jlbl_Invoice_Balance;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID1;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID2;
    private javax.swing.JLabel jlbl_Invoice_Gross_Total3;
    private javax.swing.JLabel jlbl_Invoice_No;
    private javax.swing.JLabel jlbl_Invoice_No1;
    private javax.swing.JLabel jlbl_Net_Discount;
    private javax.swing.JLabel jlbl_grn_id;
    private javax.swing.JLabel jlbl_grn_supplier_id;
    private javax.swing.JTable jtbl_Invoice_GRN;
    private javax.swing.JTable jtbl_Invoice_Item;
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
    private javax.swing.JTextField jtxt_Net_Discount;
    private javax.swing.JLabel jtxt_Net_Total;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Date1;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JLabel lbl_Time1;
    private javax.swing.JLabel lbl_user_name;
    private javax.swing.JRadioButton sub_total_Number_Discount;
    private javax.swing.JRadioButton sub_total_Precentage_Discount;
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
                jlbl_Invoice_No.setText(Integer.parseInt(id) + 2 + "");
            }
        } catch (Exception e) {
            System.out.println(e);
            jlbl_Invoice_No.setText("10000000");
            jtxt_Invoice_Barcode.grabFocus();
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
