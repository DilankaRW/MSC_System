/*nn
 

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
public class Pre_Order extends javax.swing.JFrame {

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
    public Pre_Order() {
        initComponents();
        setIcon();

        DateandTime();
        Invoice_ID_Auto_Loader();
        Invoice_Item_Name_Autocomplete();
        Auto_Total_Calculate();
        jtbl_Invoice_GRN.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jtbl_Invoice_Item.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jTable2.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));
        jTable1.getTableHeader().setFont(new Font("Helvetica", Font.PLAIN, 15));

        check_box();
        Order_Table();

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

    }

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
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lbl_Time = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JLabel();
        lbl_Date1 = new javax.swing.JLabel();
        lbl_Time1 = new javax.swing.JLabel();
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
        jlbl_Invoice_No = new javax.swing.JLabel();
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
        jlbl_Invoice_Customer_ID = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jtxt_Item_Search = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jlbl_Invoice_No2 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jlbl_Invoice_No3 = new javax.swing.JLabel();
        jlbl_Invoice_No4 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jlbl_Invoice_No5 = new javax.swing.JLabel();
        jlbl_Invoice_No6 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jlbl_Invoice_Balance1 = new javax.swing.JLabel();
        jtxt_Invoice_Cash1 = new javax.swing.JTextField();
        customer_cash1 = new javax.swing.JRadioButton();
        customer_credit1 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jtxt_Net_Total1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jbtn_Invoice_Pay1 = new javax.swing.JButton();
        jtxt_Net_Total2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlbl_Invoice_No7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtxt_Net_Total3 = new javax.swing.JLabel();
        jtxt_Net_Total4 = new javax.swing.JLabel();

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

        lbl_Date1.setFont(new java.awt.Font("Euphemia", 0, 16)); // NOI18N
        lbl_Date1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Date1.setText("Date");

        lbl_Time1.setFont(new java.awt.Font("Euphemia", 0, 30)); // NOI18N
        lbl_Time1.setForeground(new java.awt.Color(204, 204, 204));
        lbl_Time1.setText("Pre Order");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(228, 228, 228)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(628, Short.MAX_VALUE)
                    .addComponent(lbl_Time1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(236, Short.MAX_VALUE)))
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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(lbl_Time1)
                    .addContainerGap(19, Short.MAX_VALUE)))
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

        jlbl_Invoice_No.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

        jlbl_Invoice_Customer_ID.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_Customer_ID.setText("1000");
        jlbl_Invoice_Customer_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
                                                .addGap(26, 26, 26)
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
                                                .addGap(265, 265, 265)
                                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jRadioButton1)))
                                        .addGap(139, 139, 139)))
                                .addComponent(jCheckBox1)
                                .addGap(571, 571, 571)
                                .addComponent(jlbl_grn_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_grn_supplier_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxt_Invoice_Customer_Name1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbl_Gross_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(jtxt_Net_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(123, 123, 123)
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
                        .addComponent(jbtn_Invoice_Pay, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtn_ADD_New_Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sub_total_Precentage_Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtxt_Invoice_Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlbl_Invoice_Customer_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbl_Invoice_Customer_ID2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_Invoice_Customer_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlbl_Invoice_Customer_ID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/Order.png")), jPanel7); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ItemID", "GRNID", "Item", "Qty", "Item Price", "Discount", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setMinWidth(0);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(3).setMinWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(150);
            jTable1.getColumnModel().getColumn(5).setMinWidth(120);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(120);
            jTable1.getColumnModel().getColumn(6).setMinWidth(150);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order No", "Customer", "Contact No.", "Order Date", "Order Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(20);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setMinWidth(150);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(150);
            jTable2.getColumnModel().getColumn(3).setMinWidth(120);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(120);
            jTable2.getColumnModel().getColumn(4).setMinWidth(150);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jLabel23.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel23.setText("Search");

        jtxt_Item_Search.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Item_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Item_SearchKeyReleased(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setText("Order No");

        jlbl_Invoice_No2.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel50.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(153, 153, 153));
        jLabel50.setText("Contact No.");

        jlbl_Invoice_No3.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jlbl_Invoice_No4.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel51.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(153, 153, 153));
        jLabel51.setText("Customer");

        jLabel52.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(153, 153, 153));
        jLabel52.setText("Order Date");

        jlbl_Invoice_No5.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jlbl_Invoice_No6.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Balance");

        jlbl_Invoice_Balance1.setFont(new java.awt.Font("Euphemia", 0, 24)); // NOI18N
        jlbl_Invoice_Balance1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbl_Invoice_Balance1.setToolTipText("");
        jlbl_Invoice_Balance1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Invoice_Cash1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jtxt_Invoice_Cash1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxt_Invoice_Cash1.setToolTipText("");
        jtxt_Invoice_Cash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_Invoice_Cash1ActionPerformed(evt);
            }
        });
        jtxt_Invoice_Cash1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxt_Invoice_Cash1KeyReleased(evt);
            }
        });

        buttonGroup7.add(customer_cash1);
        customer_cash1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customer_cash1.setSelected(true);
        customer_cash1.setText("Cash");
        customer_cash1.setContentAreaFilled(false);
        customer_cash1.setFocusable(false);
        customer_cash1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_cash1ActionPerformed(evt);
            }
        });

        buttonGroup7.add(customer_credit1);
        customer_credit1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        customer_credit1.setText("Credit");
        customer_credit1.setContentAreaFilled(false);
        customer_credit1.setFocusable(false);
        customer_credit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_credit1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Order Amount");

        jtxt_Net_Total1.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Net_Total1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxt_Net_Total1.setToolTipText("");
        jtxt_Net_Total1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton6.setBackground(new java.awt.Color(255, 0, 51));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("Cancel Order");
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jbtn_Invoice_Pay1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jbtn_Invoice_Pay1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Release.png"))); // NOI18N
        jbtn_Invoice_Pay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_Invoice_Pay1ActionPerformed(evt);
            }
        });

        jtxt_Net_Total2.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Net_Total2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jtxt_Net_Total2.setToolTipText("");
        jtxt_Net_Total2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Payed");

        jlbl_Invoice_No7.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jlbl_Invoice_No7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Credit Amount");

        jtxt_Net_Total3.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Net_Total3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxt_Net_Total3.setToolTipText("");
        jtxt_Net_Total3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jtxt_Net_Total4.setFont(new java.awt.Font("Euphemia", 0, 18)); // NOI18N
        jtxt_Net_Total4.setForeground(new java.awt.Color(255, 255, 255));
        jtxt_Net_Total4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jtxt_Net_Total4.setText("0.0");
        jtxt_Net_Total4.setToolTipText("");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbl_Invoice_No2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_Invoice_No4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbl_Invoice_No7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jlbl_Invoice_No5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_Invoice_No6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jlbl_Invoice_No3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtxt_Item_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jtxt_Net_Total1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlbl_Invoice_Balance1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addComponent(customer_credit1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(customer_cash1)
                                                .addGap(10, 10, 10)
                                                .addComponent(jtxt_Invoice_Cash1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jtxt_Net_Total3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addComponent(jbtn_Invoice_Pay1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                            .addGap(38, 38, 38)
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(jtxt_Net_Total2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(jtxt_Net_Total4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlbl_Invoice_No2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jlbl_Invoice_No5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jlbl_Invoice_No6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbl_Invoice_No4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbl_Invoice_No7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbl_Invoice_No3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtxt_Net_Total1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtxt_Net_Total3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customer_credit1)
                                        .addComponent(customer_cash1))
                                    .addComponent(jtxt_Invoice_Cash1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbl_Invoice_Balance1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jbtn_Invoice_Pay1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxt_Net_Total2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_Net_Total4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_Item_Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/Icon/Order Relese.png")), jPanel14); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, Short.MAX_VALUE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            new DB().putData("INSERT INTO logger (user_name,date, time,description) VALUE('" + Login.User_Name + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','Back Logged into to Admin Menu')");
            new Admin_Menu().setVisible(true);
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

    private void jbtn_Invoice_PayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Invoice_PayActionPerformed
        if (!jlbl_Invoice_Customer_ID.getText().equals(jlbl_Customer_ID_Check.getText())) {
            
            if (!jtxt_Invoice_Cash.getText().isEmpty() && jtbl_Invoice_Item.getRowCount() != 0) {

                if (sub_total_Number_Discount.isSelected()) {

                    if (customer_cash.isSelected()) {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {
                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoice();
                            Clear_Invoice();
                            Order_Table();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceDiscount();
                            Clear_Invoice();
                            Order_Table();
                        }

                    } else {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCredit();
                            Clear_Invoice();
                            Order_Table();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jtxt_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCreditDiscount();
                            Clear_Invoice();
                            Order_Table();
                        }

                    }
                } else {
                    if (customer_cash.isSelected()) {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {
                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoice();
                            Clear_Invoice();
                            Order_Table();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','0','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceDiscount();
                            Clear_Invoice();
                            Order_Table();
                        }

                    } else {

                        if (jtxt_Net_Discount.getText().isEmpty() || jtxt_Net_Discount.getText().equals(jlbl_Invoice_Customer_ID1.getText())) {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCredit();
                            Clear_Invoice();
                            Order_Table();

                        } else {

                            try {
                                new DB().putData("INSERT INTO invoice (invoice.invoice_id, invoice.customer_id, invoice.date, invoice.time, invoice.gross_total, invoice.discount, invoice.net_total, invoice.user_id, invoice.customer_credit, invoice.status, invoice.user_name, invoice.type, invoice.release, invoice.active) VALUES('" + jlbl_Invoice_No.getText() + "','" + jlbl_Invoice_Customer_ID.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jlbl_Gross_Total.getText() + "','" + jlbl_Net_Discount.getText() + "','" + Double.parseDouble(jtxt_Net_Total.getText().toString()) + "','" + Login.User_ID + "','" + Double.parseDouble(jlbl_Invoice_Balance.getText().toString()) + "','Active','" + Login.User_Name + "','order','no','yes')");
                                new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id, date, time, cash, balance) VALUES('" + jlbl_Invoice_No.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash.getText() + "','" + "0" + "')");
                            } catch (Exception e) {
                                System.out.println("Errrr 1........ ");
                                e.printStackTrace();
                            }
                            Invoice_item();
                            ReportInvoiceCreditDiscount();
                            Clear_Invoice();
                            Order_Table();
                        }

                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "No Payment or Item add in Receipt", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Add Valid Customer", "Infromation Message", JOptionPane.INFORMATION_MESSAGE);
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
    }//GEN-LAST:event_jTabbedPane2MouseEntered

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

    private void jtxt_Invoice_BarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_BarcodeKeyReleased
        if (!jtxt_Invoice_Barcode.getText().isEmpty()) {
            invoice_barcode();
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jtbl_Invoice_GRN.getModel();
            dtm.setRowCount(0);
            Clear_Invoice_Item();
        }
    }//GEN-LAST:event_jtxt_Invoice_BarcodeKeyReleased

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

    private void jtxt_Item_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Item_SearchKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
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

    private void jtxt_Invoice_Cash1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Cash1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_Invoice_Cash1ActionPerformed

    private void jtxt_Invoice_Cash1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_Invoice_Cash1KeyReleased
        Order_Payment();
    }//GEN-LAST:event_jtxt_Invoice_Cash1KeyReleased

    private void customer_cash1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_cash1ActionPerformed
        if (customer_cash1.isSelected()) {
            jLabel24.setText("Balance");
            Order_Payment();
        }
    }//GEN-LAST:event_customer_cash1ActionPerformed

    private void customer_credit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_credit1ActionPerformed
        if (customer_credit1.isSelected()) {
            jLabel24.setText("Balance Credit");
            Order_Payment();
        }
    }//GEN-LAST:event_customer_credit1ActionPerformed

    private void jbtn_Invoice_Pay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_Invoice_Pay1ActionPerformed
        if (!jlbl_Invoice_No2.getText().isEmpty()) {

            if (jtxt_Net_Total3.getText().isEmpty() || jtxt_Net_Total3.getText().equals(jtxt_Net_Total4.getText())) {
                try {
                    new DB().putData("UPDATE invoice SET invoice.release='yes' WHERE invoice_id = '" + jlbl_Invoice_No2.getText() + "'");
                    JOptionPane.showMessageDialog(this, "Order Released Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);
                    Order_Release();
                    jlbl_Invoice_No2.setText(null);
                    jlbl_Invoice_No5.setText(null);
                    jlbl_Invoice_No6.setText(null);
                    jlbl_Invoice_No4.setText(null);
                    jlbl_Invoice_No7.setText(null);
                    jlbl_Invoice_No3.setText(null);
                    jtxt_Net_Total1.setText(null);
                    jtxt_Net_Total3.setText(null);
                    jtxt_Net_Total2.setText(null);
                    jtxt_Invoice_Cash1.setText(null);
                    jlbl_Invoice_Balance1.setText(null);
                    jtxt_Item_Search.setText(null);
                    DefaultTableModel dtm1 = (DefaultTableModel) jTable1.getModel();
                    dtm1.setRowCount(0);
                    Order_Table();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    new DB().putData("UPDATE invoice SET invoice.customer_credit='" + jtxt_Net_Total3.getText() + "',invoice.release='yes' WHERE invoice.invoice_id = '" + jlbl_Invoice_No2.getText() + "'");
                    new DB().putData("INSERT INTO invoice_payment (invoice_invoice_id,date,time,cash,balance) VALUES('" + jlbl_Invoice_No2.getText() + "','" + lbl_Date1.getText() + "','" + lbl_Time.getText() + "','" + jtxt_Invoice_Cash1.getText() + "','" + jlbl_Invoice_Balance1.getText() + "')");
                    JOptionPane.showMessageDialog(this, "Order Released Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                    Order_Release();
                    jlbl_Invoice_No2.setText(null);
                    jlbl_Invoice_No5.setText(null);
                    jlbl_Invoice_No6.setText(null);
                    jlbl_Invoice_No4.setText(null);
                    jlbl_Invoice_No7.setText(null);
                    jlbl_Invoice_No3.setText(null);
                    jtxt_Net_Total1.setText(null);
                    jtxt_Net_Total3.setText(null);
                    jtxt_Net_Total2.setText(null);
                    jtxt_Invoice_Cash1.setText(null);
                    jlbl_Invoice_Balance1.setText(null);
                    jtxt_Item_Search.setText(null);
                    DefaultTableModel dtm1 = (DefaultTableModel) jTable1.getModel();
                    dtm1.setRowCount(0);
                    Order_Table();

                } catch (Exception e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jbtn_Invoice_Pay1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        int Selected_Row = jTable2.getSelectedRow();
        jlbl_Invoice_No2.setText(dtm.getValueAt(Selected_Row, 0).toString());

        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM invoice I INNER JOIN customer C ON I.customer_id = C.customer_id INNER JOIN invoice_payment P ON I.invoice_id = P.invoice_invoice_id WHERE I.invoice_id = '" + jlbl_Invoice_No2.getText() + "'");
            while (rs.next()) {

                Vector v = new Vector();
                jlbl_Invoice_No5.setText(rs.getString("I.date"));
                jlbl_Invoice_No6.setText(rs.getString("I.time"));
                jlbl_Invoice_No4.setText(rs.getString("C.name"));
                jlbl_Invoice_No3.setText(rs.getString("C.contact_no"));

                double OAmount = Double.parseDouble(rs.getString("I.net_total").toString());
                jtxt_Net_Total1.setText(new DecimalFormat("0.00").format(OAmount));

                double CCredit = Double.parseDouble(rs.getString("I.customer_credit").toString());
                jtxt_Net_Total3.setText(new DecimalFormat("0.00").format(CCredit));

                double Cash = Double.parseDouble(rs.getString("P.cash").toString());
                double Balance = Double.parseDouble(rs.getString("P.balance").toString());
                double Payed = Cash - Balance;
                jtxt_Net_Total2.setText(new DecimalFormat("0.00").format(Payed));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        DefaultTableModel dtm1 = (DefaultTableModel) jTable1.getModel();
        dtm1.setRowCount(0);
        try {
            ResultSet rs = new Database_Connection.DB().getData("SELECT * FROM invoice I INNER JOIN invoice_item IT ON I.invoice_id = IT.invoice_id INNER JOIN item ITM ON IT.item_id = ITM.item_id WHERE IT.invoice_id = '" + jlbl_Invoice_No2.getText() + "'");
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("IT.grn_id"));
                v.add(rs.getString("IT.item_id"));
                v.add(rs.getString("ITM.item_name"));
                v.add(rs.getString("IT.qty"));
                v.add(rs.getString("IT.item_price"));
                v.add(rs.getString("IT.discount"));
                v.add(rs.getString("IT.total"));

                dtm1.addRow(v);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (!jlbl_Invoice_No2.getText().isEmpty()) {

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                String Item_ID = dtm.getValueAt(i, 0).toString();
                String GR_ID = dtm.getValueAt(i, 1).toString();

                try {
                    ResultSet rs = new Database_Connection.DB().getData("SELECT stock_qty FROM grn_stock WHERE grn_id = '" + Integer.parseInt(GR_ID) + "' and item_id='" + Integer.parseInt(Item_ID) + "'");
                    while (rs.next()) {
                        double Table_PQTY = Double.parseDouble(dtm.getValueAt(i, 3).toString());
                        double Database_PQTY = Double.parseDouble(rs.getString("stock_qty").toString());
                        double New_PQTY = Database_PQTY + Table_PQTY;

                        new Database_Connection.DB().putData("Update grn_stock Set stock_qty = '" + New_PQTY + "' WHERE grn_id = '" + GR_ID + "' && item_id = '" + Item_ID + "' ");
                        JOptionPane.showMessageDialog(this, "Order is Canceled Successfully", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);

                        jlbl_Invoice_No2.setText(null);
                        jlbl_Invoice_No5.setText(null);
                        jlbl_Invoice_No6.setText(null);
                        jlbl_Invoice_No4.setText(null);
                        jlbl_Invoice_No7.setText(null);
                        jlbl_Invoice_No3.setText(null);
                        jtxt_Net_Total1.setText(null);
                        jtxt_Net_Total3.setText(null);
                        jtxt_Net_Total2.setText(null);
                        jtxt_Invoice_Cash1.setText(null);
                        jlbl_Invoice_Balance1.setText(null);
                        jtxt_Item_Search.setText(null);
                        DefaultTableModel dtm1 = (DefaultTableModel) jTable1.getModel();
                        dtm1.setRowCount(0);
                        Order_Table();

                    }
                } catch (Exception e) {
                    System.out.println("Error 2 .......");
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Check All Input Field", "Information Message", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(Pre_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pre_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pre_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pre_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pre_Order().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JRadioButton customer_cash;
    private javax.swing.JRadioButton customer_cash1;
    private javax.swing.JRadioButton customer_credit;
    private javax.swing.JRadioButton customer_credit1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton jbtn_ADD_New_Customer;
    private javax.swing.JButton jbtn_Clear_All;
    private javax.swing.JButton jbtn_Clear_All1;
    private javax.swing.JButton jbtn_Clear_One;
    private javax.swing.JButton jbtn_Clear_One1;
    private javax.swing.JButton jbtn_Invoice_Pay;
    private javax.swing.JButton jbtn_Invoice_Pay1;
    private javax.swing.JLabel jlbl_Customer_ID_Check;
    private javax.swing.JLabel jlbl_Gross_Total;
    private javax.swing.JLabel jlbl_Invoice_Balance;
    private javax.swing.JLabel jlbl_Invoice_Balance1;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID1;
    private javax.swing.JLabel jlbl_Invoice_Customer_ID2;
    private javax.swing.JLabel jlbl_Invoice_Gross_Total3;
    private javax.swing.JLabel jlbl_Invoice_No;
    private javax.swing.JLabel jlbl_Invoice_No1;
    private javax.swing.JLabel jlbl_Invoice_No2;
    private javax.swing.JLabel jlbl_Invoice_No3;
    private javax.swing.JLabel jlbl_Invoice_No4;
    private javax.swing.JLabel jlbl_Invoice_No5;
    private javax.swing.JLabel jlbl_Invoice_No6;
    private javax.swing.JLabel jlbl_Invoice_No7;
    private javax.swing.JLabel jlbl_Net_Discount;
    private javax.swing.JLabel jlbl_grn_id;
    private javax.swing.JLabel jlbl_grn_supplier_id;
    private javax.swing.JTable jtbl_Invoice_GRN;
    private javax.swing.JTable jtbl_Invoice_Item;
    private javax.swing.JTextField jtxt_Invoice_Barcode;
    private javax.swing.JTextField jtxt_Invoice_Cash;
    private javax.swing.JTextField jtxt_Invoice_Cash1;
    private javax.swing.JLabel jtxt_Invoice_Customer_Name;
    private javax.swing.JTextField jtxt_Invoice_Customer_Name1;
    private javax.swing.JTextField jtxt_Invoice_Discount;
    private javax.swing.JLabel jtxt_Invoice_Gross_Total;
    private javax.swing.JTextField jtxt_Invoice_Item_ID;
    private javax.swing.JTextField jtxt_Invoice_Item_Name;
    private javax.swing.JTextField jtxt_Invoice_Qty;
    private javax.swing.JLabel jtxt_Invoice_Selling_Price;
    private javax.swing.JTextField jtxt_Item_Search;
    private javax.swing.JTextField jtxt_Net_Discount;
    private javax.swing.JLabel jtxt_Net_Total;
    private javax.swing.JLabel jtxt_Net_Total1;
    private javax.swing.JLabel jtxt_Net_Total2;
    private javax.swing.JLabel jtxt_Net_Total3;
    private javax.swing.JLabel jtxt_Net_Total4;
    private javax.swing.JLabel lbl_Date;
    private javax.swing.JLabel lbl_Date1;
    private javax.swing.JLabel lbl_Time;
    private javax.swing.JLabel lbl_Time1;
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
                jlbl_Invoice_No.setText(Integer.parseInt(id) + 1 + "");
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
    
    private void Order_Release() {
        try {
            String reportSourse = "src\\report\\VIRUVO_Invoice_Credit.jrxml";
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("InvoiceID", Integer.parseInt(jlbl_Invoice_No2.getText()));
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

    private void Order_Table() {
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();

        try {
            dtm.setRowCount(0);
            ResultSet rs = new DB().getData("select * from invoice INNER JOIN customer ON customer.customer_id = invoice.customer_id where invoice.type='order' && invoice.release='no' && active='yes'");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("invoice.invoice_id"));
                v.add(rs.getString("customer.name"));
                v.add(rs.getString("customer.contact_no"));
                v.add(rs.getString("invoice.date"));
                v.add(rs.getString("invoice.net_total"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Order_Payment() {
        if (!jtxt_Invoice_Cash1.getText().isEmpty()) {
            if (customer_cash1.isSelected()) {
                double QTY = Double.parseDouble(jtxt_Invoice_Cash1.getText().toString());
                double UP = Double.parseDouble(jtxt_Net_Total3.getText().toString());
                double tot = QTY - UP;
                jlbl_Invoice_Balance1.setText(new DecimalFormat("0.00").format(tot));
            } else {
                double QTY = Double.parseDouble(jtxt_Net_Total3.getText().toString());
                double UP = Double.parseDouble(jtxt_Invoice_Cash1.getText().toString());
                double tot = QTY - UP;
                jlbl_Invoice_Balance1.setText(new DecimalFormat("0.00").format(tot));
            }
        } else {
            jlbl_Invoice_Balance1.setText("0.00");
        }
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
