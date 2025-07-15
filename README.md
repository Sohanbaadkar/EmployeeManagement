# 🧑‍💼 Java Swing Employee Management System

A desktop-based **Employee Management System** built using **Java Swing** and **MySQL**.  
It provides a graphical user interface to **add**, **view**, **update**, and **delete** employee records.

---

## 🚀 Features

- Add new employee details
- View all employee records in a table
- Search single employee by ID
- Update employee data
- Delete employee records
- JDBC database connection using `config.properties`

---

## 🛠️ Technologies Used

- Java SE 23
- Java Swing (GUI)
- MySQL (Database)
- JDBC API
- Eclipse IDE
- MySQL Connector JAR

---

## 📦 Folder Structure

EmployeeManagement/
├── src/com/ems/
│ ├── addEmployee.java
│ ├── ViewAllEmployee.java
│ ├── SingleEmployee.java
│ ├── UpdateEmployee.java
│ ├── Delete.java
│ ├── DBConnection.java
│ ├── DBconfig.java
│ ├── Employee.java
│ └── EmployeeMS.java ← Main Class
├── config-sample.properties
├── .classpath
├── .project
├── README.md

yaml
Copy
Edit

---

## ✅ Prerequisites

- Java JDK 17 or higher
- MySQL Server installed and running
- MySQL Connector JAR (e.g., `mysql-connector-java-8.0.11.jar`)

---

## 🧰 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/Sohanbaadkar/EmployeeManagement.git
cd EmployeeManagement

2. Import into IDE
Open Eclipse, VS Code, or IntelliJ
Import the folder as an existing Java project

3. Create MySQL Database
Run this SQL in your MySQL console or GUI (like MySQL Workbench):


CREATE DATABASE ems;
USE ems;

CREATE TABLE employee (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DOUBLE,
    department VARCHAR(100),
    position VARCHAR(100)
);

4. Configure Database Properties
👉 IMPORTANT: Rename config-sample.properties to config.properties

mv config-sample.properties config.properties
Edit config.properties and enter your database credentials:

db.url=jdbc:mysql://localhost:3306/ems
db.username=your_mysql_username
db.password=your_mysql_password

5. Add MySQL Connector JAR to Classpath
Download MySQL connector from:
MySQL Connector JAR (Official)
Add the JAR to your project build path:
Eclipse: Right-click project → Build Path → Configure Build Path → Libraries → Add External JARs
IntelliJ: File → Project Structure → Modules → Dependencies → Add JAR
VS Code: Update .classpath or configure build.gradle if using Gradle

6. Run the Project
Run the EmployeeMS.java file
The GUI will launch allowing you to manage employee data

📝 Notes
Make sure MySQL is running
Ensure the table is created and credentials in config.properties are correct
If you see conn is null error, it means connection failed – check config and JAR path
