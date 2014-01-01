package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Bryndza
 */
@DoesNotRequireLogin
@UrlBinding("/auth/{$event}")
public class AuthenticationActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(AuthenticationActionBean.class);
    @SpringBean
    protected UserService userService;

    @ValidateNestedProperties(value = {
        @Validate(on = {"login"}, field = "username", required = true),
        @Validate(on = {"login"}, field = "password", required = true)
    })

    @DefaultHandler
    public Resolution showForm() {
        log.debug("showForm()");
        return new ForwardResolution("/authentication/login.jsp");
    }

    public Resolution login() {
        log.debug("login()");
        HttpSession session = getContext().getRequest().getSession();
        String path = (String) session.getAttribute("authPath");
        user = userService.login(user.getUsername(), user.getPassword());
        log.debug("Login user: " + user);

        if (user != null) {
            session.setAttribute("user", user);
        } else {
            this.getContext().getValidationErrors().addGlobalError(new SimpleError("login.failed"));
            return new ForwardResolution("/authentication/login.jsp");
        }

        if (path != null) {
            return new RedirectResolution(path.substring(0));
        } else {
            return new RedirectResolution("/index.jsp");
        }
    }

    public Resolution logout() {
        getContext().getRequest().getSession().setAttribute("user", null);
        return new RedirectResolution("/authentication/login.jsp");
    }
}
