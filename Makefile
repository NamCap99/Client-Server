build:
	javac -cp "libs/*" aggregationserver/*.java client/*.java contentserver/*.java shares/*.java AggregationServer.java GETClient.java ContentServer.java

.PHONY: build