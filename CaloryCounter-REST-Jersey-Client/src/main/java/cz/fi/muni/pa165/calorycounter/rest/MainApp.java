package cz.fi.muni.pa165.calorycounter.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a temporary class, we need to create a web GUI around rest cleint and
 * then this class must be deleted. The methods in this class are pretty complete to be integrated into GUI.
 * 
 * To run entire project from command line, go to parent project directory and run 
 * "mvn exec:java -pl CaloryCounter-REST-Jersey-Client"
 * First, of course, you need to have the project already deployed on Tomcat or other server. To do this in
 * command line, run in different shell or in background: "mvn tomcat:run" in the parent project directory.
 * 
 * @author xpasko
 */
public class MainApp {

//    creating client is expensive operation, we need only one so far
    private static final Client client = ClientBuilder.newClient();
    private static final int nastav_si_cestu = 3;
    final static Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        String username = "Anna";
        log.debug("Removing user "+username);
        int status = unregisterUser(username);
        log.debug("Returned status: "+status);
    }
    
    private static int unregisterUser(String username) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/removeuser/"+username);
        Invocation.Builder builder = actionTarget.request(MediaType.APPLICATION_JSON); // media type expected in RESPONSE
        log.debug("Client: unregisterUser: " + actionTarget.getUri().toString());
//        toto uz netreba, ked sme to specifikovali v requeste:
//        builder.header("accept", MediaType.APPLICATION_JSON);
        
//        batch processing on http invocations:
//        Invocation invocation = builder.buildDelete();
//        Response response = invocation.invoke();
        
//        single http request, no batch processing:
        Response response = builder.delete();
        int status = response.getStatus();
        response.close();
        return status;  // GUI will handle this
        
//        Response.Status.OK.getStatusCode() = 200
//        Response.Status.BAD_REQUEST.getStatusCode() = 404
//        Response.Status.INTERNAL_SERVER_ERROR.getStatusCode() = 500
                
    }
    
    private static WebTarget getTarget(int nastav_si_cestu) {
        WebTarget selectedTarget;
        switch (nastav_si_cestu) {
            case 1:
                selectedTarget = client.target("http://localhost:8080/pa165/resources"); // 1
                break;
            case 2:
                selectedTarget = client.target("http://localhost:8084/pa165/resources"); // 2
                break;
            case 3:
                // Tomcat 6 does not yet read context path from context.xml:
                selectedTarget = client.target("http://localhost:8080/CaloryCounter-Web/resources"); // 3
                break;
            default:
                selectedTarget = client.target("http://localhost:8080/pa165/resources"); // 1
                break;
        }
        return selectedTarget;
    }
    
}
