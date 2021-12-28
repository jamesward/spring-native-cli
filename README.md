# Spring Native CLI

This works:
```
SPRING_PROFILES_ACTIVE=init ./gradlew bootRun
```

This does not:
```
./gradlew bootBuildImage
docker run -it -eSPRING_PROFILES_ACTIVE=init spring-native-cli
```
