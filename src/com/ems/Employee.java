package com.ems;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Employee  {

	private static int counter = 1;
	private int id;
	private String name;
	private String salary;
	private String department;
	private String position;

	/**
	 * Create the frame.
	 */
	public Employee(String name, String salary, String department, String position) {
		this.id = counter++;
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.position = position;	
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Object[] toRow() {
		return new Object[] {id, name, salary,department, position};
		
		
	}

}
