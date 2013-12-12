package cz.fi.muni.pa165.calorycounter.frontend.restserver;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    final static Logger log = LoggerFactory.getLogger(ProfileRestResource.class);

//    with no path after /profile given:
    @GET
    public String getText() {
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("/getuserbyquery")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto getUserByQuery(@QueryParam("uname") String username) {
        log.debug("Server: getUserByQuery() with username: " + username);
        if (username == null) {
            throw new WebApplicationException("Username is null", Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            returnedUser = userService.getByUsername(username);
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(NoResultException.class)) {
                throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
            }
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
        log.debug("Server: get user with username: " + username);
        if (username == null) {
            throw new WebApplicationException("Username is null", Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            returnedUser = userService.getByUsername(username);
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(NoResultException.class)) {
                throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
            }
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
        log.debug("Server: register user: " + newUser);
        if (newUser == null || newUser.getUsername() == null || newUser.getPassword() == null) {
            throw new WebApplicationException("User, username or passwd is null", Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            userService.register(newUser, newUser.getPassword());
        } catch (IllegalArgumentException ex) {
            throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
        }
        try {
            returnedUser = userService.getByUsername(newUser.getUsername());
        } catch (RecoverableDataAccessException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

    @PUT
    @Path("/updateuser")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto updateUser(AuthUserDto userToUpdate) {
        log.debug("Server: update with user: " + userToUpdate);
        if (userToUpdate == null || userToUpdate.getUserId() == null) {
            throw new WebApplicationException("User or user id is null", Response.Status.BAD_REQUEST);
        }
        AuthUserDto returnedUser;
        try {
            userService.update(userToUpdate);
            returnedUser = userService.getByUsername(userToUpdate.getUsername());
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(IllegalArgumentException.class)) {
                throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
            }
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return returnedUser;
    }

    @DELETE
    @Path("/removeuser/{uname}")
    public Response unregisterUser(@PathParam("uname") String username) {
        log.debug("Server: unregister user: " + userToUpdate);
        if (username == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            userService.remove(userService.getByUsername(username));
        } catch (RecoverableDataAccessException ex) {
            if (ex.getCause().getClass().equals(IllegalArgumentException.class)
                    || ex.getCause().getClass().equals(NoResultException.class)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return Response.status(Response.Status.OK).build();
    }
}
