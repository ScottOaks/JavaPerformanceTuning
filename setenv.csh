setenv JAVAX_PERSISTENCE /export/appservers/glassfish4/glassfish/modules/javax.persistence.jar
setenv JAVAX_EJB /export/appservers/glassfish4/glassfish/modules/javax.ejb-api.jar
setenv JAVAX_SERVLET /export/appservers/glassfish4/glassfish/modules/javax.servlet-api.jar
setenv JSON_P /export/appservers/glassfish4/glassfish/modules/javax.json.jar
 setenv JAVAX_RS /export/appservers/glassfish4/glassfish/modules/javax.ws.rs-api.jar

setenv SIZEOF /home/sdo/NetBeansProjects/sizeof/SizeOf.jar

setenv JACKSON /home/sdo/NetBeansProjects/jackson/jackson-core-2.1.4.jar 

setenv FABAN_DRIVER /home/sdo/workload/utilities/faban/lib/fabandriver.jar 

setenv ECLIPSELINK_JAR /home/sdo/netbeans-7.4/java/modules/ext/eclipselink/eclipselink.jar
setenv JDBC_JAR /u01/app/oracle/product/11.2.0/xe/jdbc/lib/ojdbc6.jar

setenv JAVA_HOME /home/sdo/VMs/jdk1.8.0-b123

set path = ( $JAVA_HOME/bin $path )
