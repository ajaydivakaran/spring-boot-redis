# Spring Data Redis

#### Requirements to run demo application
* Docker
* JDK 17 (preferably SDKMAN)
* NVM

##### Steps to start application
* Run containers in background `docker-compose up -d`
* Start Redis UI `npm run redis-ui:start`
* Browse Redis UI `npm run redis-ui:open`
* Run Spring Boot application `./gradlew bootRun`

##### Sample requests
Refer `requests.http`


##### References
* [Spring Redis transactions](https://github.com/spring-projects/spring-data-redis/blob/main/src/main/asciidoc/reference/redis-transactions.adoc)
