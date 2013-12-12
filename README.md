CaloryCounter
=============

System for recording of sport activities and burnt calories counter.



To test REST-Client API module, proceed with following steps in order:

0. connect to database
1. clean & install whole project (from the parent module, run mvn clean install (or mvn install if no change in code))
2. don't forget to set default tomcat port to 8080
3. in the parent module, run "mvn tomcat7:run", wait until server is started
4. in different console (shell), run "mvn exec:java -pl CaloryCounter-REST-Jersey-Client" in the parent module


For devoted Netbeans users:

0. connect to database
1. click on parent project and click Clean and Build
2. set port on your default Tomcat server to 8080
3. run the Rest-Client module
4. run the main class in Rest-Client module

<<Netbeans process is not guaranteed to work>>
