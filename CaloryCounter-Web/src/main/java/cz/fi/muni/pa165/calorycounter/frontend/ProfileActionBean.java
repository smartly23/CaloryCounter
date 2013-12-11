package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stripes ActionBean for handling user profile operations.
 *
 * @author Martin Pasko (smartly23)
 */
@UrlBinding("/myprofile/{$event}/{user.username}")
public class ProfileActionBean extends BaseActionBean {

    @ValidateNestedProperties(value = {
        @Validate(on = "save", field = "name", required = true),
        @Validate(on = "save", field = "age", required = true),
        @Validate(on = "save", field = "sex", required = true),
        @Validate(on = "save", field = "weightCategory", required = true)
    })
    private AuthUserDto user;
    private static String username;
    private Gender[] genders = cz.fi.muni.pa165.calorycounter.frontend.Gender.values();
    @SpringBean //Spring can inject even to private and protected fields
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(ProfileActionBean.class);

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
        user = userService.getByUsername(username);
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
