# training.vertica.services
Training Vertica Backend Services

# start spring boot app from shell
```
cd target
java -Dspring.profiles.active=dev -jar training.vertica.services-0.0.1-SNAPSHOT.jar
```

# regerate angular 6.x services from api
```
java -jar swagger-codegen-cli.jar generate -i http://localhost:3000/v2/api-docs -l typescript-angular -o sdk
```
