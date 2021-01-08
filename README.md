# Carrel Morgan Project 1
Scala-Hive application to answer interesting questions about large datasets, using Hive.

## Example
1. Which Wikipedia article received the most traffic during the month of January, 2020

## Technologies
- YARN
- HDFS
- Scala
- Hive
- Git + GitHub

## Dataset
- https://dumps.wikimedia.org/other/clickstream/

## Initializing the Hadoop-Hive docker
1. cd ~
2. cd docker-hive/
3. docker-compose up -d

### Executing Queries directly in Hive
1. docker-compose exec hive-server bash
2. {in hive} > hive
