package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserStatsDto;
import java.util.List;

/**
 * Stripes ActionBean for handling book operations.
 *
 * @author Kucera
 */
@UrlBinding("/stats/{$event}/{user.id}")
public class StatsActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(StatsActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected UserService userService;

    //--- part for showing a list of records ----
    private List<UserStatsDto> usersStats;
    private AuthUserDto authUserDto;

    // some login user 
    @DefaultHandler
    public Resolution list() {
        // nastaveni prihlaseneho uzivatele
        authUserDto = userService.getByUsername("John");

        log.debug("list()");
        usersStats = userService.getAllUserStats();
        return new ForwardResolution("/stats/list.jsp");
    }

    public List<UserStatsDto> getUsersStats() {
        return usersStats;
    }

    public AuthUserDto getAuthUserDto() {
        return authUserDto;
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        //fill up the data for the table if validation errors occured
        usersStats = userService.getAllUserStats();
        //return null to let the event handling continue
        return null;
    }

}
