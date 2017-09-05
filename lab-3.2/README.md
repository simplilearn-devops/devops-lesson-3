# Exercise 6.2 Subversion

In this exercise we will install Subversion into a Docker image and
use the image as both a client and a server.

### Step 1

Connect to the Google Compute Engine virtual machine.

### Step 2

Change to the directory for this exercise.  
`cd`  
`cd devops-lesson-6/lab-6.2`  

Check out the Dockerfile.  
`cat Dockerfile`  

Build the image.  
`docker build -t svn .`  
` docker images`  

### Step 3

Create two directories to work with.  
`mkdir SVN Work`  

Start a client container.  
`docker run -it --rm -v $PWD/SVN:/opt/SVN svn /bin/bash`  

Create a repository. It is basically a database, configuration and scripts.   
`mkdir /opt/SVN/monitoring`  
`svnadmin create /opt/SVN/monitoring`  
`ls -l /opt/SVN/monitoring`  

Edit the server configuration file and give anonymous users write access by adding the line `anon-access = write` to the `[general]` section. You save and quit `vi` by typing `:wq`.  
`vi /opt/SVN/monitoring/conf/svnserve.conf`  

Edit the password file in the repositor and add the entry `root = rootpw` to the end of the file.  
`vi /opt/SVN/monitoring/conf/passwd`  

Exit the container with control-D.  

### Step 4

Run the server and find its IP address.  
`docker run -d --name svn -v $PWD/SVN:/opt/SVN -p 3690:3690 svn`  
`docker ps`  
`docker inspect svn`  

### Step 5

Run the client.  
`docker run -it --rm -v $PWD/Work:/opt/Work svn /bin/bash`  

Check out the repository.  
`cd /opt/Work`  
`svn co svn://172.17.0.2/monitoring`  
`ls -la`  
`cd monitoring`  

### Step 6

Try out some Subversion commands.  
`echo "Message 1 " > message1.txt`  
`svn status`  
`svn add message1.txt`  
`svn status`  
`ls -la`  
`svn log message1.txt`  

Try other commands and adding other files.

### Step 7

Exit the container with control-D.

Delete the server container.  
`docker rm -f svn`  


