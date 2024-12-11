# Live Cricket Score Application
This project is a full-stack application that provides live cricket scores, match updates, and tournament point tables. Built using Java Spring Boot, Angular, and MySQL, it allows users to stay updated on their favorite cricket matches in real-time.

# Getting Started
These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes. Follow the steps below to set up the project and see it in action.

# Prerequisites
Before you begin, ensure you have the following installed on your system:

Java Development Kit (JDK) 11 or higher
Maven for managing Java dependencies
Node.js and npm for running the Angular frontend
MySQL for the database
You can install these on your system using the following commands:

bash
Copy code
# Install Java JDK 11
sudo apt-get install openjdk-11-jdk

# Install Maven
sudo apt-get install maven

# Install Node.js and npm
sudo apt-get install nodejs
sudo apt-get install npm

# Install MySQL
sudo apt-get install mysql-server
Installing
Follow these steps to get the development environment running:

Navigate to the backend directory and build the Spring Boot project using Maven:

# bash
Copy code
cd backend
mvn clean install
Update the application.properties file with your MySQL database credentials:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/cricscorer
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the Spring Boot application:

# bash
Copy code
mvn spring-boot:run
Set Up the Frontend:

Navigate to the frontend directory and install the necessary Node.js packages:

# bash
Copy code
cd ../frontend
npm install
Run the Angular application:

# bash
Copy code
ng serve
Open your browser and go to http://localhost:4200 to see the application in action.

# Running the Tests
To ensure everything is working as expected, you can run the automated tests included with the project.

End-to-End Tests
These tests simulate user interactions with the application to verify that everything works from start to finish.

bash
Copy code
# In the frontend directory
ng e2e
Unit Tests
These tests focus on individual components of the application to ensure they function correctly.


# In the frontend directory
ng test

# In the backend directory
mvn test
Deployment
To deploy the application on a live system:

# Backend Deployment:

Package the Spring Boot application into a JAR file:

# bash
Copy code
mvn clean package
Deploy the JAR file on a server like Apache Tomcat or any cloud service provider (AWS, Azure).

# Frontend Deployment:

Build the Angular project:

# bash
Copy code
ng build --prod
Deploy the contents of the dist directory to a web server like Apache or Nginx.

# Built With
Spring Boot - The web framework used for the backend
Angular - The framework used for the frontend
MySQL - Database management
Maven - Dependency management for the backend
npm - Package management for the frontend


# Authors
AYUSH GUPTA - Initial work -Ayushgupta1201
