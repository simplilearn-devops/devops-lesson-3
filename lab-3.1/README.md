# Exercise 6.1 Ticketing

We are going to install the open source ticketing system JTrac into a docker container and use it.

### Step 1

Connect to the Google Compute engine virtual machine.

We need to enable port 8080 in the firewall.  
Go to the triple bar icon at the top left and select Networking. Then select Firewall Rules.

Select Create Firewall Rule.
Fill in the form:
Name: default-allow-http-8080
Source IP: 0.0.0.0/0
Allow protocols and ports: tcp:8080
Hit Create

### Step 2

Clone out the GitHub repository for this lesson.  
`cd`  
`git clone https://github.com/simplilearn-devops/devops-lesson-6`  

### Step 3

Change to the lab directory.  
`cd devops-lesson-6/lab-6.1`  

Check out the Dockerfile.  
`cat Dockerfile`  

Build the Docker image.  
`docker build -t jtrac .`  
`docker images`  

### Step 4

Run the container.  
`docker run -d --name jtrac -p 8080:80 jtrac`  
`docker ps`  

Find the external IP address of your VM.  
In a browser on your local machine enter the URL x.x.x.x:8080/jtrac replacing
x.x.x.x with your external IP address.

You should see the Jtrac home page.  
Log in as admin with passord admin.

### Step 5

Select `Options`.  
Select `Manage Spaces`.  
Select `Create new space`.  
Give the space a `Display Name`, `Space Key` and `Description` and hit `Next`.  
The `Space Key` must be only upper case letters.  
Select `Next` and then `Save`.  
Hit `Allocate` to add the admin user to the space.  
Create a new user and allocate it to the space.  

Create a few issues. Try out features on the application. It should be fairly intuative.
Check out this Web page for more information `http://jtrac.info/doc/html/features.html#features-dashboard`  

### Step 6

Tidy up.  
`docker stop jtrac`  

If you choose to remove the container you will lose any data. We won't be using this tool again.

