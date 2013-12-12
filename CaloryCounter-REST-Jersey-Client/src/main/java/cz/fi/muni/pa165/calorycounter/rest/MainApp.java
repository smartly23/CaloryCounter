package cz.fi.muni.pa165.calorycounter.rest;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a temporary class, we need to create a web GUI around rest cleint and
 * then this class must be deleted. The methods in this class are pretty
 * complete to be integrated into GUI.
 *
 * To run entire project from command line, go to parent project directory and
 * run "mvn exec:java -pl CaloryCounter-REST-Jersey-Client" First, of course,
 * you need to have the project already deployed on Tomcat or other server. To
 * do this in command line, run in different shell or in background: "mvn
 * tomcat:run" in the parent project directory.
 *
 * @author xpasko
 */
public class MainApp {

    private static AuthUserDto user = null;
//    creating client is expensive operation, we need only one so far
    private static final Client client = ClientBuilder.newClient();
    private static final int nastav_si_cestu = 3;
    final static Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        String username;
        AuthUserDto returnedUser;
        AuthUserDto wantedUser = new AuthUserDto();
        
        username = "John";
        returnedUser = findUserByQuery(username);
        log.debug("finding user by query with username " + username + ", found: " + returnedUser);

        username = "Nonexistent";
        returnedUser = findUserByQuery(username);
        log.debug("finding user by query with username " + username + ", found: " + returnedUser + "\n");
        
        username = "Anna";
        returnedUser = findUserByPath(username);
        log.debug("finding user by path with username " + username + ", found: " + returnedUser);
        
        username = "Nonexistent";
        returnedUser = findUserByPath(username);
        log.debug("finding user by path with username " + username + ", found: " + returnedUser + "\n");
        
        wantedUser.setAge(18);
        wantedUser.setName("Fero Mrkvicka");
        wantedUser.setSex("Male");
        wantedUser.setWeightCategory(WeightCategory._130_);
        wantedUser.setUsername("Fero");
        wantedUser.setPassword("ferino je boss");
        returnedUser = registerUser(wantedUser);
        log.debug("registering user " + wantedUser + ", returned: " + returnedUser);
        
        // already registered user:
        wantedUser.setAge(25);
        wantedUser.setName("Ferdo Mravenec");
        wantedUser.setPassword("ferino ma rad muchy");
        returnedUser = registerUser(wantedUser);
        log.debug("registering already registered user " + wantedUser + ", returned: " + returnedUser + "\n");
        
        // correct is to use update instead of register:
        returnedUser = updateUser(wantedUser);
        log.debug("updating user " + wantedUser + ", returned: " + returnedUser);
        
        username = "Anna";
        int status = unregisterUser(username);
        log.debug("Removing user " + username + " returned status: " + status);
    }

    /*
     * @return dto object as it was found in the database, or null if Response status was not 200.
     */
    private static AuthUserDto findUserByQuery(String username) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/getuserbyquery");
        actionTarget = actionTarget.queryParam("uname", username);
        // building a request with media type expected in RESPONSE:
        Invocation.Builder builder = actionTarget.request(MediaType.APPLICATION_JSON);
        log.debug("Client: findUser: " + actionTarget.getUri().toString());
//        toto uz netreba, ked sme to specifikovali v requeste:
//        builder.header("accept", MediaType.APPLICATION_JSON);

        Response response = builder.get();
        log.debug("Client: findUserByQuery: status: " + response.getStatus());

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            try {
                return response.readEntity(AuthUserDto.class); // readEntity automatically closes Response object
            } catch (ProcessingException pex) {
//                returned response cannot be mapped to AuthUserDto: GUI will handle
                throw new RestException(pex);
            } catch (IllegalStateException isex) {
//                GUI will politely handle :)
                throw new RestException(isex);
            }
        } else {
            return null;
        }
    }
    
    /*
     * @return dto object as it was found in the database, or null if Response status was not 200.
     */
    private static AuthUserDto findUserByPath(String username) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/getuser/"+username);
        Invocation.Builder builder = actionTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        log.debug("Client: findUser: " + actionTarget.getUri().toString());

        Response response = builder.get();
        log.debug("Client: findUserByPath: status: " + response.getStatus());

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            try {
                return response.readEntity(AuthUserDto.class);
            } catch (ProcessingException pex) {
//                returned response cannot be mapped to AuthUserDto: GUI will handle
                throw new RestException(pex);
            } catch (IllegalStateException isex) {
//                GUI will politely handle :)
                throw new RestException(isex);
            }
        } else {
            return null;
        }
    }
    
    /*
     * @return dto object as it was registered in the database, or null if Response status was not 200.
     */
    private static AuthUserDto registerUser(AuthUserDto userToRegister) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/createuser");
        Invocation.Builder builder = actionTarget.request(MediaType.APPLICATION_JSON);
        log.debug("Client: registerUser: " + actionTarget.getUri().toString());

        Response response = builder.post(Entity.entity(userToRegister, MediaType.APPLICATION_JSON));
        log.debug("Client: registerUser: status: " + response.getStatus());

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            try {
                return response.readEntity(AuthUserDto.class);
            } catch (ProcessingException pex) {
//                returned response cannot be mapped to AuthUserDto: GUI will handle
                throw new RestException(pex);
            } catch (IllegalStateException isex) {
//                GUI will politely handle :)
                throw new RestException(isex);
            }
        } else {
            return null;
        }
    }
    
    /*
     * @return dto object as it was updated in the database, or null if Response status was not 200.
     */
    private static AuthUserDto updateUser(AuthUserDto userToUpdate) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/updateuser");
        Invocation.Builder builder = actionTarget.request(MediaType.APPLICATION_JSON);
        log.debug("Client: updateUser: " + actionTarget.getUri().toString());

        Response response = builder.put(Entity.entity(userToUpdate, MediaType.APPLICATION_JSON));
        log.debug("Client: updateUser: status: " + response.getStatus());

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            try {
            return response.readEntity(AuthUserDto.class);
            } catch (ProcessingException pex) {
//                returned response cannot be mapped to AuthUserDto: GUI will handle
                throw new RestException(pex);
            } catch (IllegalStateException isex) {
//                GUI will politely handle :)
                throw new RestException(isex);
            }
        } else {
            return null;
        }
    }

    private static int unregisterUser(String username) {
        WebTarget actionTarget = getTarget(nastav_si_cestu).path("profile/removeuser/" + username);
        Invocation.Builder builder = actionTarget.request();
        log.debug("Client: unregisterUser: " + actionTarget.getUri().toString());

//        batch processing on http invocations:
//        Invocation invocation = builder.buildDelete();
//        Response response = invocation.invoke();

//        single http request, no batch processing:
        Response response = builder.delete();
        int status = response.getStatus();
        log.debug("Client: unregisterUser: status: " + status);
        response.close();
        return status;  // GUI will handle this
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
