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
@UrlBinding("/myprofile/{$event}")
public class ProfileActionBean extends BaseActionBean {

    @ValidateNestedProperties(value = {
        @Validate(on = "save", field = "name", required = true),
        @Validate(on = "save", field = "age", required = true),
        @Validate(on = "save", field = "sex", required = true),
        @Validate(on = "save", field = "weightCategory", required = true)
    })
    private AuthUserDto user;
    private final Gender[] genders = cz.fi.muni.pa165.calorycounter.frontend.Gender.values();
    @SpringBean //Spring can inject even to private and protected fields
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(ProfileActionBean.class);

    public void setUser(AuthUserDto user) {
        this.user = user;
    }

    public AuthUserDto getUser() {
        return user;
    }

    @Before(stages = LifecycleStage.BindingAndValidation)
    protected void loadUser() {
        user = getSessionUser();
    }

    @DefaultHandler
    public Resolution show() {
        log.debug("show()");
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

    public Gender[] getGenders() {
        return genders;
    }

}
