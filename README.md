# LogsAlert

## Steps to run the Application
### Step 1
Clone the project into workspace
### Step 2
As it is Maven build, run below command, it will create snapshot with dependencies.
```
mvn clean package
```
### Step 3
Run below command to start the application
```
java -jar target/credit-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
### Step 4

It will prompt for logfile.txt path, paste complete path for the file.<br/>
It will log the details in command prompt.

<br/><br/>
## Requirements:
All the basic functionalities mentioned in the document is completed. <br/><br/>

Additional points will be granted for: <br/><br/>
Proper use of info and debug logging<br/>
Used : apache log4j2 <br/><br/>
Proper use of Object Oriented programming <br/>
Used : Abstraction , Encapsulation<br/><br/>
Unit test coverage<br/>
jUnit Test cases result 74% of code coverage <br/><br/>
Multi-threaded solution<br/>
Not implemented <br/><br/>
