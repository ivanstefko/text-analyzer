# text-analyzer project
text-analyzer

## About project
This project creates text analysis with Lucene Analyzer of given web page. 

Project contains of 2 separate parts
- frontend part (React app)
- backend part (Spring boot app)

Both application parts runs as instance in docker container and communicate between each other via REST API. 

The backend part has implemented Swagger to easily work directly with APIs if needed. The Swagger is available at http://localhost:8080/api/swagger-ui.html

## Pre-conditions
- [Git](https://git-scm.com/downloads)
- [Docker](https://docs.docker.com/install/)
- [docker-compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html)


## How to start 
```
git clone git@github.com:ivanstefko/text-analyzer.git
cd text-analyzer
mvn clean install -f demo-be/pom.xml
mvn dockerfile:build -f demo-be/pom.xml
docker-compose up --build
```

The application will be available at: http://localhost:3000/

### Notes
Frontend part is up&running if you see in console: 
```
att-fe-app    | You can now view demo-fe in the browser.
att-fe-app    |
att-fe-app    |   Local:            http://localhost:3000/
att-fe-app    |   On Your Network:  http://172.20.0.3:3000/
att-fe-app    |
att-fe-app    | Note that the development build is not optimized.
att-fe-app    | To create a production build, use npm run build.
```

Backend part is up&running if you see in console: 
```
att-be-app    | 2019-03-24 21:28:07.800  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path '/api'
att-be-app    | 2019-03-24 21:28:07.830  INFO 1 --- [           main] com.att.app.AttApplication               : Started AttApplication in 17.602 seconds (JVM running for 19.998)
```

## Other notes
I would like to make two implementations on backend side: 

First one (existing) as in memory analyzer which use Lucene Analyzer. I didn't create Lucene index for this case becuase I create only some text statistic/analysis with relatively small among of data/text. I've started (just for test) with Lucene index on local/in memory and made some search.

Second implementation should be about Solr running in docker container - in this case I would like to demostrate that we can use separate datasource as instance in docker container and use Lucene query to get all what is needed. Unfortunatelly for this solution I had not enough time. 

## Frontend part
- simply said: my first React app

