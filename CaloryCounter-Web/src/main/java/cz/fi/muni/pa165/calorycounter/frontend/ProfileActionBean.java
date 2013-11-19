
package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

@UrlBinding("/myprofile/{$event}/{user.username}")
public class ProfileActionBean extends BaseActionBean {
    
    /*
     * TO DO: dorobit UserService tak, aby extendoval Service
     * dorobit konverziu tak, aby konvertovala aj username podla novo upraveneho dto
     * nastavit spring context tak, aby tam injektoval implementaciu toho userservice
     * kto a kde si bude pamatat username, pomocou ktoreho getneme userdto?
     */
    private AuthUserDto user;
    @SpringBean //Spring can inject even to private and protected fields
    private UserService userService;

    @DefaultHandler
    public Resolution show() {
        // log
        user = userService.getByUsername((String)getContext().getServletContext().getAttribute("username"));   // username si pamatat v kontexte
        return new ForwardResolution("/profile/show.jsp");
    }
    
    public Resolution edit() {
        // log
        return new ForwardResolution("/profile/edit.jsp");
    }
    
    public AuthUserDto getUser() {
        return user;
    }

    public UserService getUserService() {
        return userService;
    }
    
}
