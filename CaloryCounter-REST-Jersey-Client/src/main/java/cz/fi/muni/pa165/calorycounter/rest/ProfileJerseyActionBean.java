package cz.fi.muni.pa165.calorycounter.rest;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Stripes ActionBean for handling user profile operations by means of Jersey REST Client.
 *
 * @author Martin Pasko (smartly23)
 */
@UrlBinding("/myprofile/{$event}/{user.username}")
public class ProfileJerseyActionBean extends BaseJerseyActionBean {

    @ValidateNestedProperties(value = {
        @Validate(on = "save", field = "name", required = true),
        @Validate(on = "save", field = "age", required = true),
        @Validate(on = "save", field = "sex", required = true),
        @Validate(on = "save", field = "weightCatNum", required = true)
    })
    private AuthUserDto user;
    private static String username;
    private Gender[] genders = cz.fi.muni.pa165.calorycounter.rest.Gender.values();
    
    // tu nebudeme pouzivat Spring, ale Jersey Client API
    
    //@SpringBean //Spring can inject even to private and protected fields
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(ProfileJerseyActionBean.class);

    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/pa165/restserver");

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadUser() {
        String login = getContext().getRequest().getParameter("user.username");
        if (login == null) {
            return;
        }
        user = userService.getByUsername(login);
        /* vytiahne sa objekt z DB a automaticky sa nan namapuju zeditovane polozky, teda nam toto vrati
         * objekt, ktory po prislusnej akcii (napr. submit button z formulara) automaticky bude mat 
         * namapovane nove hodnoty = nemusime kopirovat vsetky parametre cez url
         */
    }

    @DefaultHandler
    public Resolution show() {
        log.debug("show()");
        if (username == null) {
            username = "John"; // pre-persisted user until we have real user management
        }
        //user = userService.getByUsername(username);
        
        WebTarget resourceWebTarget = webTarget.path("user/json/1");
        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");
        Response response = invocationBuilder.get();
        
        user = response.readEntity(AuthUserDto.class);
        
        return new ForwardResolution("/profile/show.jsp");
    }

    public Resolution edit() {
        log.debug("edit() user {}", user);
        return new ForwardResolution("/profile/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() user {}", user);
        userService.update(user);
        return new RedirectResolution(this.getClass(), "show");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "show");
    }

    public AuthUserDto getUser() {
        return user;
    }

    public Gender[] getGenders() {
        return genders;
    }

}
