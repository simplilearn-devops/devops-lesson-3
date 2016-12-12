# Exercise 3.1 - Relaional Database

Create a relational database container and access the database.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances.
Start the VM if it isn’t running and connect using SSH.

### Step 2

NEED TO CLONE THE REPO AS IT HAS TEST DATA - TEST THIS AND THE REST OF THE STEPS

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

### Step 4

Load data into the student database

Examine the script that will run the MySQL client. Notice that we are using the same image.

`cat runclient`  

Run the MySQL client container

`./runclient`  

You will be placed inside the client container running the Bash command shell.
You can now type commands to use the database

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

Observe the following output

`+----------------------+  
| Tables_in_student    |  
+----------------------+  
| current_dept_emp     |  
| departments          |  
| dept_emp             |  
| dept_emp_latest_date |  
| dept_manager         |  
| employees            |  
| salaries             |  
| titles               |  
+----------------------+  
8 rows in set (0.00 sec)`  

Get the first 10 records from the current employee department table.

`mysql> select * from current_dept_emp limit 10;`  

Observe the following output:

`+--------+---------+------------+------------+  
| emp_no | dept_no | from_date  | to_date    |  
+--------+---------+------------+------------+  
|  10001 | d005    | 1986-06-26 | 9999-01-01 |  
|  10002 | d007    | 1996-08-03 | 9999-01-01 |  
|  10003 | d004    | 1995-12-03 | 9999-01-01 |  
|  10004 | d004    | 1986-12-01 | 9999-01-01 |  
|  10005 | d003    | 1989-09-12 | 9999-01-01 |  
|  10006 | d005    | 1990-08-05 | 9999-01-01 |  
|  10007 | d008    | 1989-02-10 | 9999-01-01 |  
|  10008 | d005    | 1998-03-11 | 2000-07-31 |  
|  10009 | d006    | 1985-02-18 | 9999-01-01 |  
|  10010 | d006    | 2000-06-26 | 9999-01-01 |  
+--------+---------+------------+------------+  
10 rows in set (0.59 sec)`  

Write queries to observe the output to gain some familiarity with the data

Get the first 10 departments.

`mysql> select * from departments limit 10;`  

`+---------+--------------------+  
| dept_no | dept_name          |  
+---------+--------------------+  
| d009    | Customer Service   |  
| d005    | Development        |  
| d002    | Finance            |  
| d003    | Human Resources    |  
| d001    | Marketing          |  
| d004    | Production         |  
| d006    | Quality Management |  
| d008    | Research           |  
| d007    | Sales              |  
+---------+--------------------+  
9 rows in set (0.00 sec)`  

Get the first 10 records from the employee department table.

`mysql> select * from dept_emp limit 10;`  

`+--------+---------+------------+------------+  
| emp_no | dept_no | from_date  | to_date    |  
+--------+---------+------------+------------+  
|  10001 | d005    | 1986-06-26 | 9999-01-01 |  
|  10002 | d007    | 1996-08-03 | 9999-01-01 |  
|  10003 | d004    | 1995-12-03 | 9999-01-01 |  
|  10004 | d004    | 1986-12-01 | 9999-01-01 |  
|  10005 | d003    | 1989-09-12 | 9999-01-01 |  
|  10006 | d005    | 1990-08-05 | 9999-01-01 |  
|  10007 | d008    | 1989-02-10 | 9999-01-01 |  
|  10008 | d005    | 1998-03-11 | 2000-07-31 |  
|  10009 | d006    | 1985-02-18 | 9999-01-01 |  
|  10010 | d004    | 1996-11-24 | 2000-06-26 |  
+--------+---------+------------+------------+  
10 rows in set (0.01 sec)`  

Get the first 10 records from the employee date table.

`mysql> select * from dept_emp_latest_date limit 10;`  

`+--------+------------+------------+  
| emp_no | from_date  | to_date    |  
+--------+------------+------------+  
|  10001 | 1986-06-26 | 9999-01-01 |  
|  10002 | 1996-08-03 | 9999-01-01 |  
|  10003 | 1995-12-03 | 9999-01-01 |  
|  10004 | 1986-12-01 | 9999-01-01 |  
|  10005 | 1989-09-12 | 9999-01-01 |  
|  10006 | 1990-08-05 | 9999-01-01 |  
|  10007 | 1989-02-10 | 9999-01-01 |  
|  10008 | 1998-03-11 | 2000-07-31 |  
|  10009 | 1985-02-18 | 9999-01-01 |  
|  10010 | 2000-06-26 | 9999-01-01 |  
+--------+------------+------------+  
10 rows in set (0.59 sec)`  

Get the first 10 records from the department manager table.

`mysql> select * from dept_manager limit 10;`  

`+--------+---------+------------+------------+  
| emp_no | dept_no | from_date  | to_date    |  
+--------+---------+------------+------------+  
| 110022 | d001    | 1985-01-01 | 1991-10-01 |  
| 110039 | d001    | 1991-10-01 | 9999-01-01 |  
| 110085 | d002    | 1985-01-01 | 1989-12-17 |  
| 110114 | d002    | 1989-12-17 | 9999-01-01 |  
| 110183 | d003    | 1985-01-01 | 1992-03-21 |  
| 110228 | d003    | 1992-03-21 | 9999-01-01 |  
| 110303 | d004    | 1985-01-01 | 1988-09-09 |  
| 110344 | d004    | 1988-09-09 | 1992-08-02 |  
| 110386 | d004    | 1992-08-02 | 1996-08-30 |  
| 110420 | d004    | 1996-08-30 | 9999-01-01 |  
+--------+---------+------------+------------+  
10 rows in set (0.00 sec)`  

Get the first 10 records from the employee table.

`mysql> select * from employees limit 10;`  

`+--------+------------+------------+-----------+--------+------------+  
| emp_no | birth_date | first_name | last_name | gender | hire_date  |  
+--------+------------+------------+-----------+--------+------------+  
|  10001 | 1953-09-02 | Georgi     | Facello   | M      | 1986-06-26 |  
|  10002 | 1964-06-02 | Bezalel    | Simmel    | F      | 1985-11-21 |  
|  10003 | 1959-12-03 | Parto      | Bamford   | M      | 1986-08-28 |  
|  10004 | 1954-05-01 | Chirstian  | Koblick   | M      | 1986-12-01 |  
|  10005 | 1955-01-21 | Kyoichi    | Maliniak  | M      | 1989-09-12 |  
|  10006 | 1953-04-20 | Anneke     | Preusig   | F      | 1989-06-02 |  
|  10007 | 1957-05-23 | Tzvetan    | Zielinski | F      | 1989-02-10 |  
|  10008 | 1958-02-19 | Saniya     | Kalloufi  | M      | 1994-09-15 |  
|  10009 | 1952-04-19 | Sumant     | Peac      | F      | 1985-02-18 |  
|  10010 | 1963-06-01 | Duangkaew  | Piveteau  | F      | 1989-08-24 |  
+--------+------------+------------+-----------+--------+------------+  
10 rows in set (0.00 sec)`  

Get the first 10 records from the salary table.

`mysql> select * from salaries limit 10;`  

`+--------+--------+------------+------------+  
| emp_no | salary | from_date  | to_date    |  
+--------+--------+------------+------------+  
|  10001 |  60117 | 1986-06-26 | 1987-06-26 |  
|  10001 |  62102 | 1987-06-26 | 1988-06-25 |  
|  10001 |  66074 | 1988-06-25 | 1989-06-25 |  
|  10001 |  66596 | 1989-06-25 | 1990-06-25 |  
|  10001 |  66961 | 1990-06-25 | 1991-06-25 |  
|  10001 |  71046 | 1991-06-25 | 1992-06-24 |  
|  10001 |  74333 | 1992-06-24 | 1993-06-24 |  
|  10001 |  75286 | 1993-06-24 | 1994-06-24 |  
|  10001 |  75994 | 1994-06-24 | 1995-06-24 |  
|  10001 |  76884 | 1995-06-24 | 1996-06-23 |  
+--------+--------+------------+------------+  
10 rows in set (0.00 sec)`  

Get the first 10 records from the titles table.

`mysql> select * from titles limit 10;`  

`+--------+-----------------+------------+------------+  
| emp_no | title           | from_date  | to_date    |  
+--------+-----------------+------------+------------+  
|  10001 | Senior Engineer | 1986-06-26 | 9999-01-01 |  
|  10002 | Staff           | 1996-08-03 | 9999-01-01 |  
|  10003 | Senior Engineer | 1995-12-03 | 9999-01-01 |  
|  10004 | Engineer        | 1986-12-01 | 1995-12-01 |  
|  10004 | Senior Engineer | 1995-12-01 | 9999-01-01 |  
|  10005 | Senior Staff    | 1996-09-12 | 9999-01-01 |  
|  10005 | Staff           | 1989-09-12 | 1996-09-12 |  
|  10006 | Senior Engineer | 1990-08-05 | 9999-01-01 |  
|  10007 | Senior Staff    | 1996-02-11 | 9999-01-01 |  
|  10007 | Staff           | 1989-02-10 | 1996-02-11 |  
+--------+-----------------+------------+------------+  
10 rows in set (0.00 sec)`  

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

 
