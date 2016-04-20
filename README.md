# Teaching-HEIGVD-RES-2016-Labo-SMTP

## Authors

* URL repo : 
* Combremont Rosanne, rosanne.combremont@heig-vd.ch
* Ponce Kevin, kevin.ponce@heig-vd.ch

## Description
In this lab, we have developed a client application in java. This client application use the Socket API to communicate with a SMTP server.
The client application plays automatically pranks on a list of victims. The application read a file with all e-mail address and choose victims.
The client application choose randoms e-mails and a random message. We use a random function to choose the sender and recipients. 

##Diagram of classes

##Implementation
We use a file properties for  default values (server address, port and number of group).
When the application start  we read whole file configuration.
We generate prank 

Then we start the communication with the server. When the connection is etablished we send e-mail information
* EHLO
* MAIL FROM: address of sender
* RCPT TO: addresses of receiver
* DATA message
** SUBJECT the email subject
** BODY the body of the message

all address, the subject and the body  are in MAIL class.
The MAIL class is passed with the call function

When all whole prank are sended we close the socket and out/in buffer

## Mock server
In this lab we use an OpenSource Mock smtp server. 
* https://github.com/tweakers-dev/MockMock
A smtp mock server intercept emails and by using the web interface we can see emails send. With Mock server we can work on local machine
this fake server doesn't have all security.

After downloaded the repository, use the maven command line to build and launch test
* mvn clean install 

Then execute the next line command(execute the jar file) to run the smtp mock server. -p the port listen by the smtp server
* java -jar MockMock-1.4.0.one-jar.jar -p 2525

After all this step we are able to open a connection with smtp mock server with the next command line.
* telnet localhost 2525

## Test
We have checked our client application by using smtp mock server and the HEIG smtp server.
* localhost 2525 smtp mock server
* stmp.heig-vd.ch 25 HEIG stmp server

