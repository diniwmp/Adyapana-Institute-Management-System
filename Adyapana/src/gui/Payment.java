
package gui;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.Connection;
import utils.ValidationException;

/**
 *
 * @author Dini
 */
public class Payment extends javax.swing.JFrame {

    private final HashMap<String, Integer> studentData = new HashMap<>();
    private final HashMap<String, Integer> classesData = new HashMap<>();

    public Payment() {
        initComponents();

        loadStudents();
        loadClasses();
        loadPayment("SELECT `invoices`.`invoice_id` AS `invoice_id`,"
                + "`invoices`.`yr` AS `year`,"
                + "`invoices`.`month` AS `month`,"
                + "`invoices`.`created_at` AS `date`,"
                + "`invoices`.`value` AS `payment`,"
                + "`students`.`name` AS `stu_name`,"
                + "`classes`.`title` AS `class` FROM `invoices`"
                + " INNER JOIN `students` ON `invoices`.`students_s_no` = `students`.`s_no` "
                + " INNER JOIN `classes` ON `invoices`.`classes_class_no` = `classes`.`class_no`");
    }

    private void loadClasses() {

        try {

            Vector<String> data = new Vector<>();
            data.add("Select");

            ResultSet rs = Connection.search("SELECT `class_no`, `title` FROM adyapana.classes;");
            while (rs.next()) {

                data.add(rs.getString("title"));
                classesData.put(rs.getString("title"), rs.getInt("class_no"));
            }

            ComboClass.setModel(new DefaultComboBoxModel(data));

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudents() {

        try {

            Vector<String> data = new Vector<>();
            data.add("Select");

            ResultSet rs = Connection.search("SELECT `s_no`, `name` FROM adyapana.students;");
            while (rs.next()) {

                data.add(rs.getString("name"));
                studentData.put(rs.getString("name"), rs.getInt("s_no"));
            }

            ComboName.setModel(new DefaultComboBoxModel(data));

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadPayment(String query) {
        try {

            DefaultTableModel modal = (DefaultTableModel) table.getModel();
            modal.setRowCount(0);

            ResultSet rs = Connection.search(query);
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("invoice_id"));
                row.add(rs.getString("stu_name"));
                row.add(rs.getString("class"));
                row.add(rs.getString("year"));
                row.add(rs.getString("month"));
                row.add(rs.getString("payment"));
                row.add(rs.getString("date"));

                modal.addRow(row);
            }

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refresh() {
        loadPayment("SELECT `invoices`.`invoice_id` AS `invoice_id`,"
                + "`invoices`.`yr` AS `year`,"
                + "`invoices`.`month` AS `month`,"
                + "`invoices`.`created_at` AS `date`,"
                + "`invoices`.`value` AS `payment`,"
                + "`students`.`name` AS `stu_name`,"
                + "`classes`.`title` AS `class` FROM `invoices`"
                + " INNER JOIN `students` ON `invoices`.`students_s_no` = `students`.`s_no` "
                + " INNER JOIN `classes` ON `invoices`.`classes_class_no` = `classes`.`class_no`");

        txtId.setText("");
        ComboName.setSelectedIndex(0);
        ComboClass.setSelectedIndex(0);
        txtYear.setText("");
        txtMonth.setText("");
        txtPayment.setText("");
        choDate.setDate(null);
        txtSearch.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAddPayment = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ComboClass = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        ComboName = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtMonth = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        choDate = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtPayment = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAddPayment.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnAddPayment.setText("Add");
        btnAddPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPaymentActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-refresh-30.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel6.setText("Id:");

        jLabel4.setText("Class:");

        ComboClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Student Name:");

        ComboName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Month:");

        jLabel10.setText("Year:");

        txtYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYearActionPerformed(evt);
            }
        });

        jLabel8.setText("Date:");

        jLabel11.setText("Payment:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPayment)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(choDate, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtYear)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboClass, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMonth)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(ComboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(ComboName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(choDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setText("Payment   Management");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Student Name", "Class", "Year", "Month", "Payment", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setText("Search By Student Name :");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnAddPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPaymentActionPerformed

        try {

            String invoiceId = txtId.getText();
            String studentName = String.valueOf(ComboName.getSelectedItem());
            String className = String.valueOf(ComboClass.getSelectedItem());
            String year = txtYear.getText();
            String month = txtMonth.getText();
            String payment = txtPayment.getText();
            java.util.Date date = choDate.getDate();

            if (invoiceId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Id fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (studentName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Student Name is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (className.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Class Name is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (month.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Month is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (year.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Year is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (date == null) {
                JOptionPane.showMessageDialog(this, "Date is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (payment.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Payment is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (Double.parseDouble(payment) < 0) {
                throw new ValidationException("Not enough cash!");
            }

            try {
                Integer.parseInt(year);
                Integer.parseInt(month);
                Double.parseDouble(payment);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Year, month, and payment must be numeric.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date today = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(date);
            Connection.iud("INSERT INTO `invoices` "
                    + "(`yr`, `month`,`created_at`, `students_s_no`, `classes_class_no`, `value`) "
                    + "VALUES "
                    + "('" + new SimpleDateFormat("yyyy").format(today) + "',"
                    + " '" + new SimpleDateFormat("MM").format(today) + "','" + formattedDate + "', "
                    + "'" + studentData.get(studentName) + "', "
                    + "'" + classesData.get(className) + "',"
                    + " " + payment + ")");

            try {

                InputStream inputStream = this.getClass().getResourceAsStream("/report/PaymentReceiptSad.jasper");

                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("ReceiptNo", txtId.getText());
                parameters.put("Student", String.valueOf(ComboName.getSelectedItem()) );
                parameters.put("Grade",String.valueOf(ComboClass.getSelectedItem()) );
                parameters.put("ClassFee", txtPayment.getText());
                parameters.put("Total", txtPayment.getText());
                
                JREmptyDataSource source = new JREmptyDataSource();

                JasperPrint report = JasperFillManager.fillReport(inputStream, parameters,source);
                JasperViewer.viewReport(report, false);

            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Invoice Added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            refresh();
        } catch (ValidationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddPaymentActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            String invoiceId = txtId.getText();
            String studentName = String.valueOf(ComboName.getSelectedItem());
            String className = String.valueOf(ComboClass.getSelectedItem());
            String year = txtYear.getText();
            String month = txtMonth.getText();
            String payment = txtPayment.getText();
            java.util.Date date = choDate.getDate();

            if (invoiceId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Id fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (studentName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Student Name is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (className.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Class Name is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (month.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Month is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (year.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Year is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (date == null) {
                JOptionPane.showMessageDialog(this, "Date is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (payment.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Payment is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Integer.parseInt(year);
                Integer.parseInt(month);
                Double.parseDouble(payment);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Year, month, and payment must be numeric.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(date);

            ResultSet rs = Connection.search("SELECT * FROM `invoices` WHERE `invoice_id` = '" + invoiceId + "'");

            if (rs.next()) {
                boolean canUpdate = false;
                if (rs.next()) {

                    if (!rs.getString("invoice_id").equals(invoiceId)) {
                        JOptionPane.showMessageDialog(this, "This Invoice Id already used", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        canUpdate = true;
                    }

                } else {
                    canUpdate = true;
                }

                if (canUpdate) {

                    Connection.iud("UPDATE `invoices` SET "
                            + "`yr` = '" + year + "', "
                            + "`month` = '" + month + "', "
                            + "`created_at` = '" + formattedDate + "', "
                            + "`students_s_no` =  '" + studentData.get(studentName) + "', "
                            + "`value` = '" + payment + "', "
                            + "`classes_class_no` = '" + classesData.get(className) + "' "
                            + " WHERE `invoice_id` = '" + invoiceId + "'");
                    JOptionPane.showMessageDialog(this, "Invoice updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                }

            } else {
                JOptionPane.showMessageDialog(this, "No invoice found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, "An unknown error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYearActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() != 2) {

            try {
                int selectedRow = table.getSelectedRow();

                txtId.setText(String.valueOf(table.getValueAt(selectedRow, 0)));
                ComboName.setSelectedItem(String.valueOf(table.getValueAt(selectedRow, 1)));
                ComboClass.setSelectedItem(String.valueOf(table.getValueAt(selectedRow, 2)));
                txtYear.setText(String.valueOf(table.getValueAt(selectedRow, 3)));
                txtMonth.setText(String.valueOf(table.getValueAt(selectedRow, 4)));
                txtPayment.setText(String.valueOf(table.getValueAt(selectedRow, 5)));
                String date = String.valueOf(table.getValueAt(selectedRow, 6));
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(date);
                    choDate.setDate(parsedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Invalid date format: " + date, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a valid row!", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_tableMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:

        String name = txtSearch.getText();
        loadPayment("SELECT `invoices`.`invoice_id` AS `invoice_id`,"
                + "`invoices`.`yr` AS `year`,"
                + "`invoices`.`month` AS `month`,"
                + "`invoices`.`created_at` AS `date`,"
                + "`invoices`.`value` AS `payment`,"
                + "`students`.`name` AS `stu_name`,"
                + "`classes`.`title` AS `class` FROM `invoices`"
                + " INNER JOIN `students` ON `invoices`.`students_s_no` = `students`.`s_no` "
                + " INNER JOIN `classes` ON `invoices`.`classes_class_no` = `classes`.`class_no` WHERE `name` LIKE '" + name + "'");
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboClass;
    private javax.swing.JComboBox<String> ComboName;
    private javax.swing.JButton btnAddPayment;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser choDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMonth;
    private javax.swing.JFormattedTextField txtPayment;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
