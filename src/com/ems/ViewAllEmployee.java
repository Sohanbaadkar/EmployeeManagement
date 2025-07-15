package com.ems;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class ViewAllEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ViewAllEmployee() {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("List Of All Employees");

	    // Use BorderLayout for the frame
	    contentPane = new JPanel(new BorderLayout());
	    contentPane.setBackground(Color.YELLOW);
	    setContentPane(contentPane);

	    // Title label
	    JLabel lblTitle = new JLabel("List Of All Employees", JLabel.CENTER);
	    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
	    contentPane.add(lblTitle, BorderLayout.NORTH);

	    // Table model
	    DefaultTableModel model = new DefaultTableModel(
	        new String[] {"ID", "Name", "Salary", "Department", "Position"}, 0
	    ) {
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

	    // Fetch data from DB
	    try {
	        Connection con = DBConnection.getConnection();
	        String sql = "SELECT * FROM employees";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String salary = rs.getString("salary");
	            String dept = rs.getString("department");
	            String pos = rs.getString("position");

	            model.addRow(new Object[]{id, name, salary, dept, pos});
	        }
	        con.close();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    // JTable setup
	    table = new JTable(model);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

	    table.getColumnModel().getColumn(0).setPreferredWidth(30);  // ID
	    table.getColumnModel().getColumn(1).setPreferredWidth(100); // Name
	    table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Salary
	    table.getColumnModel().getColumn(3).setPreferredWidth(120); // Department
	    table.getColumnModel().getColumn(4).setPreferredWidth(120); // Position

	    // Row color renderer
	    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(
	                JTable table, Object value, boolean isSelected,
	                boolean hasFocus, int row, int column) {

	            Component c = super.getTableCellRendererComponent(
	                    table, value, isSelected, hasFocus, row, column);

	            // Center the text
	            setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

	            // Background coloring
	            if (!isSelected) {
	                if (row % 2 == 0) {
	                    c.setBackground(new Color(255, 102, 102)); // Light red
	                } else {
	                    c.setBackground(new Color(153, 204, 255)); // Light blue
	                }
	            } else {
	                c.setBackground(table.getSelectionBackground());
	            }

	            return c;
	        }
	    };


	    for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumnModel().getColumn(i).setCellRenderer(renderer);
	    }

	    // Scroll pane
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPane.add(scrollPane, BorderLayout.CENTER);

	    // Back button panel
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setBackground(Color.YELLOW);
	    JButton backButton = new JButton("Back");
	    buttonPanel.add(backButton);
	    contentPane.add(buttonPanel, BorderLayout.SOUTH);

	    // Action for back button
	    backButton.addActionListener(e -> {
	        new EmployeeMS().setVisible(true);
	        dispose();
	    });

	    // Final window adjustments
	    pack(); // Adjust size based on components
	    setLocationRelativeTo(null); // Center the window
	    setVisible(true); // Show the frame
	}


		
	
}
