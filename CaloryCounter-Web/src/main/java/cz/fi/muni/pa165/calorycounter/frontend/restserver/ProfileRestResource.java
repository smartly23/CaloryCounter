package cz.fi.muni.pa165.calorycounter.frontend.restserver;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;

/**
 * REST resource for operations on AuthUser entities.
 *
 * @author smartly23 Martin Pasko
 */
@Path("/profile")
public class ProfileRestResource {

    private AuthUserDto user;
//    Spring beans can't be injected directly into JAX-RS classes by using just Spring XML configuration,
//    instead Jersey jersey-spring3 dependency needs to be added
    @Autowired
    private UserService userService;
//    @Context
//    private UriInfo context;

//    with no path after /profile given:
    @GET
    @Produces("text/plain")
    public String getText() {
        return "Please specify your action.";   // upravit: vsetky message cez resource bundle
    }

    @GET
    @Path("/getuserByQuery")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto getUserByQuery(@QueryParam("uname") String username) {
        if (username == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            returnedUser = userService.getByUsername(username);
        } catch (RecoverableDataAccessException ex) {
            /*
             * Thanks to the "slightly weird" design of UserServiceImpl (in case of entity not found 
             * returns null), we can be sure, that if exception is thrown, than its internal server error
             */
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

//    e.g. curl -i http://localhost:8080/CaloryCounter-Web/resources/profile/jsonget/Anna
//    for XML use 'curl -i -H "Accept: application/xml" -H "Content-Type: application/xml" http://localhost:8080/path'
    @GET
    @Path("/getuser/{uname}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto getUserByPath(@PathParam("uname") String username) {
        if (username == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            returnedUser = userService.getByUsername(username);
        } catch (RecoverableDataAccessException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

    /*
     * Registers a new user, returns that user dto object, having its id (userId) assigned.
     */
    @POST
    @Path("/createuser")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto registerUser(AuthUserDto newUser) {
        if (newUser == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            userService.register(newUser, newUser.getPassword());
            returnedUser = userService.getByUsername(newUser.getUsername());
        } catch (RecoverableDataAccessException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

    /*
     * If the DTO sent to server has invalid (non-existent) username, 
     */
    @PUT
    @Path("/updateuser")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto updateUser(AuthUserDto userToUpdate) {
        if (userToUpdate == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            userService.update(userToUpdate);
            returnedUser = userService.getByUsername(userToUpdate.getUsername());
        } catch (RecoverableDataAccessException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

    @DELETE
    @Path("/removeuser/{uname}")
    public void unregisterUser(@PathParam("uname") String username) {
        if (username == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            userService.remove(userService.getByUsername(username));
        } catch (RecoverableDataAccessException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
