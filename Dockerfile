FROM openjdk:24-jdk AS build
WORKDIR /Elevator
COPY . /Elevator
RUN ./mvnw clean package -DskipTests

FROM openjdk:24-jdk
WORKDIR /Elevator
COPY --from=build /Elevator/target/*.jar /Elevator/elevator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "elevator-0.0.1-SNAPSHOT.jar"]
