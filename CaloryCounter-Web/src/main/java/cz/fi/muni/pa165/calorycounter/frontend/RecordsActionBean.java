package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserActivityRecordsDto;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserActivityRecordsService;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * Stripes ActionBean for handling book operations.
 *
 * @author Lastuvka
 */
@UrlBinding("/records/{$event}")
public class RecordsActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordsActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected UserActivityRecordsService userActivityRecordsService;

    @SpringBean //Spring can inject even to private and protected fields
    protected UserService userService;
    private UserActivityRecordsDto uards;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        uards = userActivityRecordsService.getAllActivityRecords(getSessionUser());
        return new ForwardResolution("/records/list.jsp");

    }

    public UserActivityRecordsDto getUards() {
        return uards;
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        uards = userActivityRecordsService.getAllActivityRecords(getSessionUser());
        //return null to let the event handling continue
        return null;
    }

}
