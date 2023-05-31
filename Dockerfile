FROM openjdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} c317-back.jar

ENTRYPOINT ["java","-jar","/c317-back.jar"]