# Hospital Management System

A **console-based Java application** to manage hospital operations such as patients, doctors, and appointments. The system connects to a MySQL database to perform CRUD operations and appointment booking with doctor availability checks.

---

## Features

- Add and view patients
- View doctors
- Book appointments with availability check
- Uses MySQL for persistent storage
- Simple and intuitive console menu interface

---

## Technologies Used

- Java (JDK 17+ recommended)
- MySQL
- JDBC (Java Database Connectivity)

---

## Database Setup

Make sure you have MySQL installed and running. Create the database and tables using the following schema:

```sql
CREATE DATABASE hospital;

USE hospital;

CREATE TABLE doctors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  specialization VARCHAR(100) NOT NULL
);

CREATE TABLE patients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(10) NOT NULL
);

CREATE TABLE appointments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  appointment_date DATE,
  FOREIGN KEY (patient_id) REFERENCES patients(id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);



Clone the Repository
git clone https://github.com/yourusername/hospital-management-system.git
cd hospital-management-system


private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "root";
private static final String password = "your_password_here";


javac HospitalManagementSystem.java Patient.java Doctor.java
java HospitalManagementSystem

HOSPITAL MANAGEMENT SYSTEM
1. Add Patient
2. View Patients
3. View Doctors
4. Book Appointments
5. Exit
