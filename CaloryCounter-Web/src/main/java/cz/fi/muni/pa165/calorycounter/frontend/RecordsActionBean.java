package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserActivityRecordsDto;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserActivityRecordsService;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;

import java.util.List;

/**
 * Stripes ActionBean for handling book operations.
 *
 * @author Lastuvka
 */
@UrlBinding("/records/{$event}/{user.id}")
public class RecordsActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordsActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected UserActivityRecordsService userActivityRecordsService;

    @SpringBean //Spring can inject even to private and protected fields
    protected UserService userService;

    //--- part for showing a list of records ----
    private UserActivityRecordsDto uards;
    private AuthUserDto authUserDto;

    // some login user 
    @DefaultHandler
    public Resolution list() {
        AuthUserDto user = new AuthUserDto();
        user.setName("x");
        user.setUsername("x");
        user.setUserId(1L);
        authUserDto = user;

        log.debug("list()");
        uards = userActivityRecordsService.getAllActivityRecords(authUserDto);
        return new ForwardResolution("/records/list.jsp");

    }

    public UserActivityRecordsDto getUards() {
        return uards;
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        uards = userActivityRecordsService.getAllActivityRecords(authUserDto);
        //return null to let the event handling continue
        return null;
    }

}
