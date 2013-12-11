package cz.fi.muni.pa165.calorycounter.frontend;

import static cz.fi.muni.pa165.calorycounter.frontend.BaseActionBean.escapeHTML;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityRecordDto;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityRecordService;
import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityService;
import cz.fi.muni.pa165.calorycounter.serviceapi.UserService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import java.util.List;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * Stripes ActionBean for handling record operations.
 *
 * @author Bryndza
 */
@UrlBinding("/record/{$event}/{record.activityRecordId}")
public class RecordActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean
    protected ActivityRecordService activityRecordService;
    @SpringBean
    protected ActivityService activityService;
    @SpringBean
    protected UserService userService;
    @ValidateNestedProperties(value = {
        @Validate(on = {"createRecord", "save"}, field = "activityName", required = true),
        @Validate(on = {"createRecord", "save"}, field = "duration", required = true, minvalue = 1),
        @Validate(on = {"createRecord", "save"}, field = "activityDate", required = true)
    })
    private ActivityRecordDto record;
    private List<ActivityDto> activities;
    private AuthUserDto user;

    public AuthUserDto getUser() {
        return user;
    }

    public void setUser(AuthUserDto user) {
        this.user = user;
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDto> activities) {
        this.activities = activities;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"def", "createRecord"})
    public void setUp() {
        //String login = getContext().getRequest().getParameter("user.username");
        String login = "John";
        if (login == null) {
            return;
        }
        user = userService.getByUsername(login);
        activities = activityService.getAll(user.getWeightCategory());
    }

    public ActivityRecordDto getRecord() {
        return record;
    }

    public void setRecord(ActivityRecordDto record) {
        this.record = record;
    }

    @DefaultHandler
    public Resolution def() {
        log.debug("def()");
        return new ForwardResolution("/record/create.jsp");
    }

    @HandlesEvent("createRecord")
    public Resolution createRecord() {
        log.debug("createRecord() record={}", record);
        ActivityDto activity = null;
        for (ActivityDto act : activities) {
            if (act.getActivityName().equals(record.getActivityName())) {
                activity = act;
                break;
            }
        }
        record.setCaloriesBurnt(record.getDuration() * (activity.getCaloriesAmount(record.getWeightCategory())));
        Long createdId = activityRecordService.create(record);
        getContext().getMessages().add(new LocalizableMessage("record.create.message", escapeHTML(record.getActivityName().toString()), escapeHTML(String.valueOf(record.getDuration())), escapeHTML(String.valueOf(record.getCaloriesBurnt()))));
        log.debug("Created activity record with id " + createdId + ". <a href=\"/records/edit.jsp/" + createdId + "\">edit");
        return new ForwardResolution("/record/create.jsp");
    }
//    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
//    public void loadRecordFromDatabase() {
//        String ids = getContext().getRequest().getParameter("record.activityRecordId");
//        if (ids == null) {
//            return;
//        }
//        record = activityRecordService.get(Long.parseLong(ids));
//    }
//
//    public Resolution edit() {
//        log.debug("edit() record={}", record);
//        return new ForwardResolution("/record/edit.jsp");
//    }
//
//    public Resolution save() {
//        log.debug("save() record={}", record);
//        activityRecordService.update(record);
//        return new RedirectResolution("records/list.jsp");
//    }
}
