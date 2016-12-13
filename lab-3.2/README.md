# Devops Lab 3.2 Docker Volumes

Working with Docker volumes.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances. Start the VM if it isn’t running and connect using SSH.


### Step 2

Create a Docker volume to which you will add persistent data

`docker volume create --name mydata`  

### Step 3

Create a Docker container, attach the data volume and add persistent data

Pull a light weight Alpine distribution image

`docker pull alpine`  

Create a container from Alpine and mount the volume mydata to /mnt

`docker run -ti --name client -v mydata:/mnt alpine /bin/sh`  

Go to the /mnt directory and touch (create) two files and verify the exist

`cd /mnt`  
`touch foo.txt`  
`touch bar.txt`  
`ls`  

Exit the container

`exit`  

Verfiy that the container exists and is stopped

`docker ps -a`  

Delete the container and then verify it is gone

`docker rm client`  
`docker ps -a`  

Run a new container and mount the volume mydata to /mnt again

`docker run --rm -ti -v mydata:/mnt alpine /bin/sh`  

Go to the /mnt directory and observer the files still exist

`cd /mnt`  
`ls`  

Exit the container

`exit`  

Verify that the volume still exists

`docker volume ls`  

### Step 4

Clean up artifacts

`docker rm -f $(docker ps -aq)`  
`docker rmi $(docker images -q)`  
`docker volume rm $(docker volume ls -q)`  

