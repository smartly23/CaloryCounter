
package cz.fi.muni.pa165.calorycounter.frontend;

import static cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean.log;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UrlBinding("/myprofile/{$event}/{user.username}")
public class ProfileActionBean extends BaseActionBean {
    
    @ValidateNestedProperties(value = {
        @Validate(on = "save", field = "name", required = true),
        @Validate(on = "save", field = "age", required = true),
        @Validate(on = "save", field = "sex", required = true),
        @Validate(on = "save", field = "weightCatNum", required = true)
    })
    private AuthUserDto user;
    private String username;
    @SpringBean //Spring can inject even to private and protected fields
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);

    @DefaultHandler
    public Resolution show() {
        log.debug("show()");
        user = userService.getByUsername(username);
        return new ForwardResolution("/profile/show.jsp");
    }
    
    public Resolution edit() {
        log.debug("edit() user with id {}", user.getUserId());
        return new ForwardResolution("/profile/edit.jsp");
    }
    
    public Resolution save() {
        log.debug("save() user with id {}", user.getUserId());
        userService.update(user);
        return new RedirectResolution(this.getClass(), "show");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "show");
    }
    
    public AuthUserDto getUser() {
        return user;
    }
    
}
