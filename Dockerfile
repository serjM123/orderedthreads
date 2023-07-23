FROM amazoncorretto:20.0.2
COPY . /app
WORKDIR /app/target
#RUN javac ./src/main/java/org/example/App.java
#CMD java -jar target/orderedthread-1.0-SNAPSHOT.jar
CMD ["java","-cp","orderedthread-1.0-SNAPSHOT.jar","org.example.App"]
