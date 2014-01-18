package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stripes ActionBean for handling book operations.
 *
 * @author Jan Kučera
 */
@DoesNotRequireLogin
@UrlBinding("/activities")
public class ActivitiesActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(ActivitiesActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected ActivityService activityService;

    //--- part for showing a list of records ----
    private List<ActivityDto> activities;

    // some login user
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        activities = activityService.getAll();
        return new ForwardResolution("/activities/list.jsp");
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

}
