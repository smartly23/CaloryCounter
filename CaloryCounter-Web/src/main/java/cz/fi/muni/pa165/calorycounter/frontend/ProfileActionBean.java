package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.WeightCategory;
import java.util.ArrayList;
import java.util.List;
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
    private Gender[] genders = cz.fi.muni.pa165.calorycounter.frontend.Gender.values();
    @SpringBean //Spring can inject even to private and protected fields
    private UserService userService;
    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);

    @DefaultHandler
    public Resolution show() {
        log.debug("show()");
        user = userService.getByUsername("x");
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

    public Gender[] getGenders() {
        return genders;
    }

    /*
     * Temporary method, until we implement login and authentication features
     */
    private AuthUserDto createAuxUser() {
        AuthUserDto authUser = new AuthUserDto();
        authUser.setName("Ezest Mrkvicka");
        authUser.setAge(35);
        authUser.setSex(Gender.Other.toString());
        authUser.setWeightCatNum(WeightCategory._155_);
        authUser.setUsername("emrkvicka");
        authUser.setUserId(new Long(666));
        // toto odkomentovat az ked budeme mat funkcnu databazu, dovtedy to bude hadzat error
        //userService.register(user, "emrkvicka", "passwd");
        return authUser;
    }
}
