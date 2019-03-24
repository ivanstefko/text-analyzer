# att-project

git clone git@github.com:ivanstefko/text-analyzer.git
cd text-anayzer
mvn clean install -f demo-be/pom.xml
mvn dockerfile:build -f demo-be/pom.xml
docker-compose up --build