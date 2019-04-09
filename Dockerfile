# #####################################################################
# Build stage with the runtime jar and resources
# #####################################################################
FROM tomcat:9.0.10-jre8

MAINTAINER srinivas_thungathueri@intuit.com
COPY  target/springboottemplate*.war /usr/local/tomcat/webapps/api.war
RUN mkdir -p /usr/local/tomcat/webapps/api \
  && unzip /usr/local/tomcat/webapps/api.war -d /usr/local/tomcat/webapps/api/ \
  && rm -f /usr/local/tomcat/webapps/api.war

EXPOSE 8080

CMD ["catalina.sh", "run"]