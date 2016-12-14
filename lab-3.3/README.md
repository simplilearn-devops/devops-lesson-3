# DevOps Lab 3.3 Hadoop

Set up Hadoop and run a map reduce job.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances. Start the VM if it isn’t running and connect using SSH.

### Step 2

Change to the lab directory.

`cd ~/devlops-lesson-3/lab-3.3`  

 Take a look at the files in the directory.

 Install and configure Hadoop.
 Read and run the script which configures Hadoop.

`chmod +x setup_hadoop`  
`./setup_hadoop`  

### Step 3

Configure the HDFS file system.  
First format the HDFS file system.  
It will generate a lot of output.  

`hadoop/bin/hdfs namenode -format`  

Start the name node - it will generate output and some warnings.  
The warnings can be safely ignored.  

`hadoop/sbin/start-dfs.sh`  

Start Yarn.  

`hadoop/sbin/start-yarn.sh`  

### Step 4

Create a home directory on HDFS for your user.  

`hadoop/bin/hdfs dfs -mkdir /user`  
`hadoop/bin/hdfs dfs -mkdir /user/student`  

Check out the contents on the input directory.  
Now copy the input directory into HDFS.  

`hadoop/bin/hdfs dfs -put input`  

Verify that the input directory was copied.  

`hadoop/bin/hdfs dfs -ls`  
`hadoop/bin/hdfs dfs -ls input`  

### Step 5

Check out the code in the src directory and the file in the input directory.
The code will count the number of occurences of each word in the input.

Build the code.

`mvn package`  

See what got created.  

`ls -l target`  

### Step 6

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

