package com.ems;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame {
    JTextField idField, nameField, salaryField, departmentField, positionField;
    JButton btnUpdate;

    public UpdateEmployee() {
        setTitle("UPDATE EMPLOYEE DETAILS");
        setSize(500, 400);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new java.awt.Color(139, 69, 19));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTitle = new JLabel("UPDATE EMPLOYEE DETAILS");
        lblTitle.setBounds(140, 10, 300, 30);
        getContentPane().add(lblTitle);

        JLabel lblId = new JLabel("Employee id:");
        lblId.setBounds(50, 60, 100, 25);
        getContentPane().add(lblId);

        idField = new JTextField();
        idField.setBounds(150, 60, 100, 25);
        getContentPane().add(idField);

        JLabel lblName = new JLabel("name:");
        lblName.setBounds(50, 140, 100, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setBounds(150, 140, 200, 25);
        getContentPane().add(nameField);

        JLabel lblSalary = new JLabel("salary:");
        lblSalary.setBounds(50, 180, 100, 25);
        getContentPane().add(lblSalary);

        salaryField = new JTextField();
        salaryField.setBounds(150, 180, 200, 25);
        getContentPane().add(salaryField);

        JLabel lblDept = new JLabel("Department:");
        lblDept.setBounds(50, 220, 100, 25);
        getContentPane().add(lblDept);

        departmentField = new JTextField();
        departmentField.setBounds(150, 220, 200, 25);
        getContentPane().add(departmentField);

        JLabel lblPos = new JLabel("Position:");
        lblPos.setBounds(50, 260, 100, 25);
        getContentPane().add(lblPos);

        positionField = new JTextField();
        positionField.setBounds(150, 260, 200, 25);
        getContentPane().add(positionField);

        btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(180, 310, 120, 30);
        getContentPane().add(btnUpdate);

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
     // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 310, 100, 30); // You can adjust position if needed
        getContentPane().add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeMS home = new EmployeeMS();
                home.setVisible(true);
                dispose();
            }
        });


        setVisible(true);
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            Connection con = DBConnection.getConnection();

            // Step 1: Get current data from DB
            String selectSql = "SELECT * FROM employees WHERE id = ?";
            PreparedStatement psSelect = con.prepareStatement(selectSql);
            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Employee ID not found!");
                return;
            }

            // Step 2: Preserve current values if user left any field empty
            String currentName = rs.getString("name");
            String currentSalary = rs.getString("salary");
            String currentDepartment = rs.getString("department");
            String currentPosition = rs.getString("position");

            String newName = nameField.getText().trim().isEmpty() ? currentName : nameField.getText().trim();
            String newSalary = salaryField.getText().trim().isEmpty() ? currentSalary : salaryField.getText().trim();
            String newDept = departmentField.getText().trim().isEmpty() ? currentDepartment : departmentField.getText().trim();
            String newPosition = positionField.getText().trim().isEmpty() ? currentPosition : positionField.getText().trim();

            // Step 3: Update the database
            String updateSql = "UPDATE employees SET name=?, salary=?, department=?, position=? WHERE id=?";
            PreparedStatement psUpdate = con.prepareStatement(updateSql);
            psUpdate.setString(1, newName);
            psUpdate.setString(2, newSalary);
            psUpdate.setString(3, newDept);
            psUpdate.setString(4, newPosition);
            psUpdate.setInt(5, id);

            int rowsUpdated = psUpdate.executeUpdate();
            con.close();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while updating employee.");
        }
    }


    public static void main(String[] args) {
        new UpdateEmployee();
    }
}
