# Spring Data Redis

#### Requirements to run demo application
* Docker
* JDK 17 (preferably SDKMAN)
* NVM

##### Steps to start application
* Run containers in background `docker-compose up -d`
* Start Redis UI (foreground command) `npm run redis-ui:start`
* Browse Redis UI `npm run redis-ui:open`
* Run Spring Boot application `./gradlew bootRun`

##### Sample requests
Refer `requests.http`

##### Redis UI commands
* To view hash entry `HGETALL <user-uuid>`
* To view key entry `GET <user-uuid>`

##### Persistence approaches
* Refer branch `value-approach` to view Redis repository using KEY/VALUE pairs
* Refer branch `main` to view Redis repository using HASHES

##### Spring Boot and Redis observations
* Connection with Redis is established on first web request i.e lazy initialisation
* By default, uses byte serializers. Serializers can be overridden to use JSON.
* For immutable entries KEY/VALUE is simpler compared to HASHES

##### References
* [Spring Redis transactions](https://github.com/spring-projects/spring-data-redis/blob/main/src/main/asciidoc/reference/redis-transactions.adoc)
