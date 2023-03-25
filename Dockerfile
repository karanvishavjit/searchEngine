FROM openjdk:17-jdk-alpine as build
COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /usr/app/target/*.jar winnocare-backend-image.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/searchengineimage.jar"]