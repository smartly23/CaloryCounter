package cz.fi.muni.pa165.calorycounter.frontend.restserver;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Context
    private UriInfo context;

//    with no path after /profile given
    @GET
    @Produces("text/plain")
    public String getText() {
        return "Please specify your action.";
    }
    
    @GET
    @Path("/jsonget")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto getUserJsonByQuery(@DefaultValue("John") @QueryParam("uname") String username,
                                          @DefaultValue("1") @QueryParam("id") int id) {
        return userService.getByUsername(username);
    }
    
    @GET
    @Path("/jsonget/{uname}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AuthUserDto getUserJsonByPath(@DefaultValue("John") @PathParam("uname") String username) {
        return userService.getByUsername(username);
    }
    
}
