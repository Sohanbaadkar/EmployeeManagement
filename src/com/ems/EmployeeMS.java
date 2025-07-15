package com.ems;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class EmployeeMS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// Shared List
	public static java.util.List<Employee> employeeList = new java.util.ArrayList<>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeMS frame = new EmployeeMS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeMS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setBounds(129, 10, 215, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View all Employye");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setBounds(46, 93, 129, 33);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAllEmployee allEmpWindow = new ViewAllEmployee();
				allEmpWindow.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("View Employee");
		btnNewButton_1.setBounds(256, 93, 123, 33);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SingleEmployee EmpWindow = new SingleEmployee();
				EmpWindow.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Add Employee");
		btnNewButton_2.setBounds(46, 154, 129, 33);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployee addEmpWindow = new addEmployee();
				addEmpWindow.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_3 = new JButton("Delete Employee");
		btnNewButton_3.setBounds(256, 156, 123, 28);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete deleteEmpWindow = new Delete();
				deleteEmpWindow.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_4 = new JButton("Update Employee");
		btnNewButton_4.setBounds(162, 206, 112, 33);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmployee updateEmpWindow = new UpdateEmployee();
				updateEmpWindow.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(31, 77, -176, 240);
		contentPane.add(btnNewButton_5);
	}
}
