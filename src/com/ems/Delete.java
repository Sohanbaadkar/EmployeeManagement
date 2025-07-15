package com.ems;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class Delete extends JFrame {
    JTextField idField;
    JTable table;
    DefaultTableModel model;

    public Delete() {
        setTitle("EMPLOYEE EXIT");
        setSize(500, 400);
        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(255, 140, 0));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("EMPLOYEE EXIT");
        lblTitle.setBounds(180, 10, 150, 30);
        add(lblTitle);

        JLabel lblId = new JLabel("Employee id:");
        lblId.setBounds(50, 60, 100, 25);
        add(lblId);

        idField = new JTextField();
        idField.setBounds(150, 60, 100, 25);
        add(idField);

        JButton btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBounds(270, 60, 100, 25);
        add(btnSubmit);

        // Table with 5 columns (including ID)
        String[] columns = { "ID", "Name", "Salary", "Department", "Position" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 120, 380, 150);
        add(pane);

        btnSubmit.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                Employee emp = getEmployeeById(id);
                
                if (emp != null) {
                    // Show in table before deleting
                    model.setRowCount(0);
                    model.addRow(new Object[]{
                        emp.getId(),
                        emp.getName(),
                        emp.getSalary(),
                        emp.getDepartment(),
                        emp.getPosition()
                    });

                    // Delete from DB
                    boolean deleted = deleteEmployeeById(id);
                    if (deleted) {
                        JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
                        idField.setText(""); // clear field
                    } else {
                        JOptionPane.showMessageDialog(this, "Error while deleting employee.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID entered.");
            }
        });
     // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 300, 100, 25);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeMS home = new EmployeeMS();
                home.setVisible(true);
                dispose();
            }
        });


        setVisible(true);
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        Employee employee = null;

        try {
            Connection con = DBConnection.getConnection(); // use your shared class
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM employees WHERE id = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String salary = rs.getString("salary"); // assuming column is VARCHAR
                String department = rs.getString("department");
                String position = rs.getString("position");

                employee = new Employee(name, salary, department, position);
                employee.setId(id); // set ID after creation
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }
    
 // Method to delete employee by ID
    public boolean deleteEmployeeById(int id) {
        boolean result = false;

        try {
            Connection con = DBConnection.getConnection(); // Reuse your DB connection
            PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE id = ?");
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static void main(String[] args) {
        new Delete(); // fix class name here
    }
}
