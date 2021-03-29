# Github-webscrapper
Webscrapping for statistic files from repo.

### Getting Started

All Maven plugins and dependencies are available from [Maven Central](https://search.maven.org/). Please have installed

* JDK 11 (tested OpenJDK)
* Maven 3.3+ (also tested with 3.5.x)
* Docker 20.10.5, build 55c4c88

### Building and local running

* `mvn clean install`
* `docker-compose up -d`

#### Test the Endpoint

```
# curl -v --location --request GET 'http://localhost:8080/api/github/{{owner}}/repo/{{repo}}'

Ex: curl -v --location --request GET 'http://localhost:8080/api/github/malikoski/repo/playmusic'
```

