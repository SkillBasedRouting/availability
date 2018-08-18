# Availability Service

holds all available users

## Test & Build

run 
```bash
mvn clean package
docker build -t availabilityservice:beta .
```

or simply type
```bash
sh build.sh
```

## Run

run
```bash
docker run -d --rm -p "8080:8080" -v routing_availabilityservice:/persistence availabilityservice:beta
```

or simple type
```bash
sh run.sh
```

## Access

run
```bash
curl -G http://localhost:8080/api/v1/users
```

the response should look like this:
```json
[]
```

## Enable Security

1. make sure to have a running keycloak instance in the docker network which is accessable under the dns name 'keycloak'
2. export keycloak.json and replace it with the existing one under /resources/webapp/WEB-INF
3. uncomment security section in /resources/project-defaults.yml



