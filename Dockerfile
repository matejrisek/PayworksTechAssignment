FROM openjdk:8-jre
MAINTAINER Matej Risek <mrisek@gmail.com>
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/payworks/PayworksTechAssignment.jar"]
ADD target/PayworksTechAssignment.jar /usr/share/payworks/PayworksTechAssignment.jar

