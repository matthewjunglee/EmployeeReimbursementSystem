# Employee Reimbursement System

## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time.
All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests.
Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers 
are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java
* JDBC
* Servlets
* PostgreSQL
* HTML/CSS/CSS Bootstrap
* JavaScript
* AJAX
* Agile-Scrum

## Features

* Employees can create a new account
* Employees and Finance Managers can log in and view their personal portal
* Employees can create and delete requests
* Managers can approve and deny requests
* The requests tables will update automatically based on user input

To-do list:
* Only managers can create employees
* Add table filters that allow you to filter reimbursements based on status and type

## Getting Started

1. Open up your terminal and create a new directory.
  - mkdir <directory_name>
2. Run the following command:
  - git clone https://github.com/matthewjunglee/EmployeeReimbursementSystem.git
3. Open up the project in a Java IDE.
4. Install and set up Apache Tomcat. See Apache documentation for more information.
5. Create a SQL database and change the project's environment variables to match your database.
6. If you would like to implement your own changes on your localhost, change the url and uri in the servlets and js files.

## Usage

#### Home Page
* This is the first page you see
![Home Page](https://github.com/matthewjunglee/EmployeeReimbursementSystem/blob/master/images/LandingPage.JPG?raw=true)

#### Create a new account
* If you do not have an employee account, you can press the "Create Account" button
![Home Page](https://github.com/matthewjunglee/EmployeeReimbursementSystem/blob/master/images/CreateAccountModalForm.JPG?raw=true)

#### Employee Portal
* As an employee, you can create a reimbursement request, delete a request and view your pending / resolved requests
![Home Page](https://github.com/matthewjunglee/EmployeeReimbursementSystem/blob/master/images/CreateRequestModalForm.JPG?raw=true)

![Home Page](https://github.com/matthewjunglee/EmployeeReimbursementSystem/blob/master/images/EmployeePortal.JPG?raw=true)

#### Manager Portal
* As a manager, you can accept/deny requests, and view all pending / resolved requests
![Home Page](https://github.com/matthewjunglee/EmployeeReimbursementSystem/blob/master/images/ManagerPortal.JPG?raw=true)

