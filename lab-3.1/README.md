# Exercise 3.1 - Relational Database

Create a relational database container and access the database.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances.
Start the VM if it isn’t running and connect using SSH.

### Step 2

Clone the repository for lesson 3 and change to the lab directory.

`git clone https://github.com/simplilearn-devops/devops-lesson-3.git`  
`cd devops-lesson-3/lab-3.1`  

Unpack zipped dump files

`cd test_db`  
`gunzip *gz`  
`cd ..`  
`chmod +x *`  


### Step 3

Initialize directory structure

Carry out the following steps to set up the directory for the lab.

Create a Docker data volume to hold the database.

`docker volume create --name mysql_data`  

Confirm that the data volume was created.

`docker volume ls`  

List the contents of the directory 'test_db' and notice that it contains sample data for this exercise.

`ls -l test_db`  

Pull down the MySQL image for release 5.7 from Docker Hub.

`docker pull mysql:5.7` 

Examine the script that will run and create the student database structure.

`cat runserver_first`  

Run the script to create a container with MySQL running and create the database.

`./runserver_first`  

You will need to monitor the logs to see when MySQL has completed creating
the student database and is waiting for connections. This may take several minutes
to complete.  
`docker logs mysql`  

When you see the following in the logs you may continue. Run the command until you see.  
`mysqld: ready for connections.`  

Run the script to run MySQL in a container and mount the data volume.  
`./runserver`  

Examine the logs from MySQL and wait for it to be ready to accept connections.  
`docker logs mysql`  

Find the IP address of the server.  
`docker inspect mysql`  

### Step 4

Load data into the student database

Examine the script that will run the MySQL client. Notice that we are using the same image.  
`cat runclient`  

Run the MySQL client container.  
`./runclient`  

You will be placed inside the client container running the Bash command shell.  
You can now type commands to use the database. You may need to change the IP address to that of the server.  
`mysql -h 172.17.0.2 -u student -p student`  

You will be prompted for the password. Enter 'student' as the password  
`Enter password:`  

When you connect you will see the MySQL client prompt  
`mysql>`  

At the prompt read in the definition for the employee information
Be patient! this will take several minutes to complete  
`mysql> source /data/employees.sql;`  

### Step 5

Examine the database tables in the database by selecting the first 10 rows from each

`mysql> show tables;`  

Get the first 10 records from the current employee department table.

`mysql> select * from current_dept_emp limit 10;`  

Write queries to observe the output to gain some familiarity with the data

Get the first 10 departments.

`mysql> select * from departments limit 10;`  

Get the first 10 records from the employee department table.

`mysql> select * from dept_emp limit 10;`  


Get the first 10 records from the employee date table.

`mysql> select * from dept_emp_latest_date limit 10;`  


Get the first 10 records from the department manager table.

`mysql> select * from dept_manager limit 10;`  


Get the first 10 records from the employee table.

`mysql> select * from employees limit 10;`  

1Get the first 10 records from the salary table.

`mysql> select * from salaries limit 10;`  

Get the first 10 records from the titles table.

`mysql> select * from titles limit 10;`  


### Step 4

Answer business questions with SQL queries

Q1. Who is the oldest employee and what is their age?

`SELECT first_name, last_name, TIMESTAMPDIFF(YEAR,birth_date,CURDATE()) AS age FROM employees ORDER BY birth_date DESC limit 1;`  

Q2. Which department has the most employees?

`select dept_no, count(*) AS number  from dept_emp WHERE to_date='9999-01-01' group by dept_no order by number DESC limit 1;`  

Q3. Which employee has the highest salary and how much is it

`SELECT t1.first_name, t1.last_name, t2.salary FROM employees AS t1 INNER JOIN salaries as t2 on t1.emp_no = t2.emp_no ORDER BY salary DESC limit 1;`  


Q4. Which employee manages the smallest department and how many employees are in that department?

See if you can answer this one yourself.

### Step 5
 
Clean up after the lab

 Exit from the MySQL client  
`mysql> quit;`  

 Exit from the client container  
 `exit`  

 Clean up the lab artifacts  
 `./cleanup`  

 
