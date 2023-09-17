# Ido Ben Ami - Flink Home Assigntment

There are many strategies to invoke, stream, aggregate and filter data in Flink.
I could use the Table API strategy, which makes implementation more clear but I wanted to stick to code.

## Features
Created 3 layers:
- Data Generator for generating devices' events. Also Configurations class (can change the defaults from env variables), for example (thread fire every 15 seconds 25  events => 100 per minute)
- Calculate every 1 minute the average per device (using Sliding window and custom derived functions)
- Join 2 streams and filter only the ones which is still in the deviation boundries.

## Running the application
Very sorry for not having more time for building and creating docker compose!
- Clone repository to Intellij
- Make sure Flink 1.17 is installed
- Build project (Maven) and install all dependencies - use JDK version 11.x (Project Settings)
- Run DataStreamJob.java file

## TODOs
- Create a docker for my application
- Create a docker compose file for running Flink cluster and my job
 
