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
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JButton;

public class addEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtName;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public addEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Onboarding");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(158, 21, 145, 13);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(116, 93, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NAME: ");
		lblNewLabel_1.setBounds(38, 96, 53, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 122, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(116, 151, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(116, 180, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SALARY: ");
		lblNewLabel_2.setBackground(new Color(240, 240, 240));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(37, 125, 54, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DEPARTMENT:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(25, 154, 81, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("POSITION:");
		lblNewLabel_4.setBounds(38, 183, 63, 13);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("ONBOARD");
		btnNewButton.setBounds(116, 218, 96, 21);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String salary = textField_1.getText();
				String department = textField_2.getText();
				String position = textField_3.getText();
				
				Employee emp = new Employee(name, salary, department, position);
				try {
					Connection con = DBConnection.getConnection();
					String sql = "insert into employees (name, salary, department, position) values (?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, salary);
					ps.setString(3, department);
					ps.setString(4, position);
					ps.executeUpdate();
					con.close();
					
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
				javax.swing.JOptionPane.showMessageDialog(null, "Employee Onboarded!");
				
			}
		});
		// Back button
		JButton backButton = new JButton("Back");
		backButton.setBounds(250, 218, 80, 21);
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
