Instructions to run
1. Launch 4 EC2 instances
2. Reserve an elastic IP address for each, so that they're not changed mid-way by AWS
3. Change the IP address in each program file to correspond with the received elastic IP addresses:
   - In Send.java on the lines 30 and 37, change IP address to that of EC2 instance which will host Mediate1.java and Mediate2.java respectively
   - In Mediate1.java on line 38 and Mediate2.java on line 63, change IP address to that of EC2 instance which will host Receive.java
   - In Receive.java on lines 44 and 54, change IP address to that of EC2 instance which will host Mediate1.java and Mediate2.java respectively
4. Compile and run Mediate1.java and Mediate2.java in their respective EC2 instances
5. Compile Sender.java. Then run with 4 letter digit, example: "java Sender word" in its EC2 instance
6. Compile and run Receive.java in its EC2 instance. Example output for input in 5: "word"

Program flow
1. Sender splits four digit input into half and send 2 to Mediate1 and 2 to Mediate2
2. When on continuous run mode, Mediate 1 and 2 will print to terminal to indicate when message successfully sent to receiver after receiver sends confirmation back
3. When receiver runs, it waits until a message is received before sending confirmation (in UDP) message to Mediate 1 and 2, displaying the message (it combines the messages by Mediate 1 and 2), and exiting. Otherwise, ctrl+c will be needed to exit it manually.

  
