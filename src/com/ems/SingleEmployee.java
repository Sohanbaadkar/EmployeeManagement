package com.ems;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class SingleEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SingleEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(162, 10, 136, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(159, 63, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(45, 66, 74, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Get Detail");
		btnNewButton.setBounds(159, 100, 96, 21);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String inputId = textField.getText().trim();
		        if (!inputId.matches("\\d+")) {
		            javax.swing.JOptionPane.showMessageDialog(null, "Enter valid numeric ID");
		            return;
		        }

		        int empId = Integer.parseInt(inputId);
		        Connection conn = null;
		        java.sql.PreparedStatement stmt = null;
		        java.sql.ResultSet rs = null;

		        try {
		            conn = DBConnection.getConnection();
		            String query = "SELECT department, salary, name, id, position FROM employees WHERE id = ?";
		            stmt = conn.prepareStatement(query);
		            stmt.setInt(1, empId);
		            rs = stmt.executeQuery();

		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            model.setRowCount(0); // Clear previous results

		            if (rs.next()) {
		                model.addRow(new Object[] {
		                    rs.getString("id"),
		                    rs.getString("name"),
		                    rs.getString("salary"),
		                    rs.getString("department"),
		                    rs.getString("position")
		                });
		            } else {
		                javax.swing.JOptionPane.showMessageDialog(null, "No employee found for ID: " + empId);
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            javax.swing.JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		        } finally {
		            try { if (rs != null) rs.close(); } catch (Exception ex) {}
		            try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
		            try { if (conn != null) conn.close(); } catch (Exception ex) {}
		        }
		    }
		});


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 161, 386, 78);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Salary", "Department", "Position"
			}
		));
		// Back button
		JButton backButton = new JButton("Back");
		backButton.setBounds(320, 100, 80, 25);
		contentPane.add(backButton);

		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        EmployeeMS home = new EmployeeMS();
		        home.setVisible(true);
		        dispose();
		    }
		});

	}
}
