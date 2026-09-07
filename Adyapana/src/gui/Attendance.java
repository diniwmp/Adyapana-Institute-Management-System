package gui;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Connection;
import utils.ValidationException;

/**
 *
 * @author Dini
 */
public class Attendance extends javax.swing.JFrame {

    private static HashMap<String, String> statusMap = new HashMap<>();
    private static HashMap<String, String> classMap = new HashMap<>();

    public Attendance() {
        initComponents();
        loadStatus();
        loadClass();
        loadAttendanceTable("SELECT `attendance`.`id` AS `id`,"
                + "`attendance`.`maked_at` AS `date`,"
                + "`students`.`name`AS `name`,"
                + "`classes`.`title` AS `class`,"
                + "`attendance_status`.`status` AS `status` "
                + " FROM `attendance`"
                + " INNER JOIN `students` ON `attendance`.`students_s_no` = `students`.`s_no` "
                + "INNER JOIN `classes` ON `attendance`.`classes_class_no` = `classes`.`class_no`"
                + "INNER JOIN `attendance_status` ON `attendance`.`attendance_status_id` = `attendance_status`.`id`");
    }

    private void loadStatus() {

        try {

            ResultSet rs = Connection.search("SELECT * FROM `attendance_status`");
            Vector<String> vector = new Vector<>();
            vector.add("Select Attendance Staus");
            while (rs.next()) {
                vector.add(rs.getString("status"));
                statusMap.put(rs.getString("status"), rs.getString("id"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            comboAttendance.setModel(model);

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClass() {

        try {

            ResultSet rs = Connection.search("SELECT * FROM `classes`");
            Vector<String> vector = new Vector<>();
            vector.add("Select Class");
            while (rs.next()) {
                vector.add(rs.getString("title"));
                classMap.put(rs.getString("title"), rs.getString("class_no"));

            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            comboClass.setModel(model);

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAttendanceTable(String query) {
        try {

            DefaultTableModel modal = (DefaultTableModel) table.getModel();
            modal.setRowCount(0);

            ResultSet rs = Connection.search(query);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("class"));
                row.add(rs.getString("date"));
                row.add(rs.getString("status"));
                modal.addRow(row);
            }

        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reset() {
        txtId.setText("");
        comboClass.setSelectedIndex(0);
        comboAttendance.setSelectedIndex(0);
        loadAttendanceTable("SELECT `attendance`.`id` AS `id`,"
                + "`attendance`.`maked_at` AS `date`,"
                + "`students`.`name`AS `name`,"
                + "`classes`.`title` AS `class`,"
                + "`attendance_status`.`status` AS `status` "
                + " FROM `attendance`"
                + " INNER JOIN `students` ON `attendance`.`students_s_no` = `students`.`s_no` "
                + "INNER JOIN `classes` ON `attendance`.`classes_class_no` = `classes`.`class_no`"
                + "INNER JOIN `attendance_status` ON `attendance`.`attendance_status_id` = `attendance_status`.`id`");
        
        txtStatus.setText("");
        txtSearch.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAttendance = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        comboAttendance = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboClass = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setText("Mark Attendance :");

        btnAttendance.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnAttendance.setText("Mark Attendance");
        btnAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttendanceActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setText("Student Id:");

        txtId.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        btnRefresh.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-refresh-30.png"))); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        comboAttendance.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setText("Select Class:");

        comboClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboAttendance, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setText("Student Attendance");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Student Name", "Class", "Marked At", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setText("Search By Name :");

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

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setText("Search By Status:");

        txtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusActionPerformed(evt);
            }
        });
        txtStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStatusKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void btnAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttendanceActionPerformed

        try {
            String studentId = txtId.getText();
            String selectedClass = comboClass.getSelectedItem().toString();
            String selectedStatus = comboAttendance.getSelectedItem().toString();

            if (studentId.isEmpty()) {
                throw new ValidationException("Please Enter Student No");
            }
            try {
                Integer.parseInt(studentId);
            } catch (NumberFormatException e) {
                throw new ValidationException("Student ID must be a numeric value");
            }
            if (selectedClass.equals("Select Class")) {
                throw new ValidationException("Please Enter Class Name");
            }
            if (selectedStatus.equals("Select Attendance Staus")) {
                throw new ValidationException("Please Enter Class Name");

            }

            String classId = classMap.get(selectedClass);
            String statusId = statusMap.get(selectedStatus);
            if (classId == null || statusId == null) {
                throw new ValidationException("Invalid Class or Status selection");
            }
            Connection.iud("INSERT INTO `attendance` ("
                    + "`maked_at`, "
                    + "`students_s_no`, `attendance_status_id`, `classes_class_no`"
                    + ") VALUES (CURRENT_TIMESTAMP, '"
                    + studentId + "', '"
                    + statusId + "', '"
                    + classId + "')");

            JOptionPane.showMessageDialog(this, "Attendance Marked Succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            reset();

        } catch (ValidationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            AdminLogin.logger.log(Level.WARNING, e.getMessage(), e);
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unkonown Error Occured", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAttendanceActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

     reset();

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:

        String name = txtSearch.getText();
        loadAttendanceTable("SELECT `attendance`.`id` AS `id`,"
                + "`attendance`.`maked_at` AS `date`,"
                + "`students`.`name`AS `name`,"
                + "`classes`.`title` AS `class`,"
                + "`attendance_status`.`status` AS `status` "
                + "FROM `attendance`"
                + " INNER JOIN `students` ON `attendance`.`students_s_no` = `students`.`s_no` "
                + "INNER JOIN `classes` ON `attendance`.`classes_class_no` = `classes`.`class_no`"
                + "INNER JOIN `attendance_status` ON `attendance`.`attendance_status_id` = `attendance_status`.`id` WHERE `name` LIKE '" + name + "'");

    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusActionPerformed
    }//GEN-LAST:event_txtStatusActionPerformed

    private void txtStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStatusKeyReleased
        String status = txtStatus.getText();
        loadAttendanceTable("SELECT `attendance`.`id` AS `id`,"
                + "`attendance`.`maked_at` AS `date`,"
                + "`students`.`name`AS `name`,"
                + "`classes`.`title` AS `class`,"
                + "`attendance_status`.`status` AS `status` "
                + "FROM `attendance`"
                + " INNER JOIN `students` ON `attendance`.`students_s_no` = `students`.`s_no` "
                + "INNER JOIN `classes` ON `attendance`.`classes_class_no` = `classes`.`class_no`"
                + "INNER JOIN `attendance_status` ON `attendance`.`attendance_status_id` = `attendance_status`.`id` WHERE `name` LIKE '" + status + "'");

    }//GEN-LAST:event_txtStatusKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttendance;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> comboAttendance;
    private javax.swing.JComboBox<String> comboClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
