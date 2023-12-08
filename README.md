## Description
- **GETClient** calls api get weather and gets weather list from Aggregation Server and updates timestamp lamport, then displays in console
- **Aggregation Server** will provide put api for content server and get api for client, it will receive weather content from content server, update timestamp lamport. Then push the weather content into the queue sorted in ascending order of timestamp. HandlerDataService will continuously scan the queue for weather content and save it as a list of weather into the file weather.json
- **Content Server** continuously scans weather.json files in the files directory, then calls the put api to continuously put content on the Aggregation Server and the response updates the timestamp lamport
- Run the AggregationServer.java first, then comes run ContentServer.java and finally run GETClient.java

## Setup
- Install JDK 11

## Build
```shell
javac -cp "libs/*" aggregationserver/*.java client/*.java contentserver/*.java shares/*.java AggregationServer.java GETClient.java ContentServer.java
```

## Run
```shell
java -cp .:libs/* AggregationServer aggregationserver/files/weather.json
java -cp .:libs/* ContentServer contentserver/files/
java -cp .:libs/* GETClient
```
