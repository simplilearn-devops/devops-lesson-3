# DevOps Lab 3.3 Hadoop

Set up Hadoop and run a map reduce job.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances. Start the VM if it isn’t running and connect using SSH.

### Step 2

Download and install Maven.

`cd ~/Downloads`  
`wget http://apache.crihan.fr/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz`  
`cd`
`tar zxf Downloads/apache-maven-3.3.9-bin.tar.gz`  
`ln -s apache-maven-3.3.9 maven`  

### Step 3

Change to the lab directory.

`cd ~/devops-lesson-3/lab-3.3`  

 Take a look at the files in the directory.

Get Hadoop.

`wget http://apache.mirrors.ovh.net/ftp.apache.org/dist/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz`  

 Install and configure Hadoop.
 Read and run the script which configures Hadoop.

`chmod +x setup_hadoop`  
`./setup_hadoop`  

### Step 4

Configure the HDFS file system.  
First format the HDFS file system.  
It will generate a lot of output.  

`hadoop/bin/hdfs namenode -format`  

Start the name node - it will generate output and some warnings.  
The warnings can be safely ignored.  

`hadoop/sbin/start-dfs.sh`  

Start Yarn.  

`hadoop/sbin/start-yarn.sh`  

### Step 5

Create a home directory on HDFS for your user.  

`hadoop/bin/hdfs dfs -mkdir /user`  
`hadoop/bin/hdfs dfs -mkdir /user/student`  

Check out the contents on the input directory.  
Now copy the input directory into HDFS.  

`hadoop/bin/hdfs dfs -put input`  

Verify that the input directory was copied.  

`hadoop/bin/hdfs dfs -ls`  
`hadoop/bin/hdfs dfs -ls input`  

### Step 6

Check out the code in the src directory and the file in the input directory.
The code will count the number of occurences of each word in the input.

Build the code.

`~/maven/bin/mvn package`  

See what got created.  

`ls -l target`  

### Step 7

Run the map-reduce code.  

`hadoop/bin/hadoop jar target/Hadoop-1.0.jar hadoop.WordCount`  

See what got created.

`hadoop/bin/hdfs dfs -ls`  
`hadoop/bin/hdfs dfs -ls output`  

See the results - each word and how many times it occurs in the input.

`hadoop/bin/hdfs dfs -cat output/part-r-00000`  

### Step 7

Finally cleanup

`chmod +x cleanup`  
`./cleanup`  

Disconnect from the SSH session and stop your virtual machine.
