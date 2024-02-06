SQL CRUD Operations on 'Registration' Table:
This project demonstrates the implementation of CRUD operations on a SQL table named 'Registration'.
The table is designed to store user registration information, including fields such as ID (Primary Key), Name, Email, Contact Number, and any other relevant information.
Sample SQL Table Script:
CREATE TABLE Register (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    ContactNumber VARCHAR(255),
);
Implementation of CRUD Operations:
The CRUD operations (Create, Read, Update, Delete) have been implemented using Java and JDBC.
Proper error handling and validation have been incorporated for each operation.
Steps to Run the Code Locally:
  1. Clone the GitHub repository: git clone https://github.com/Harshabhonsle/INI8-Labs-Private-Limited-Software-Development-internship-Assessment/tree/master.git
Navigate to the project directory:
  2. cd your-repository
Run the application on your IDE or run command for your application.
