package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityRecordDto;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityRecordService;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 * Stripes ActionBean for handling record operations.
 *
 * @author Bryndza
 */
@UrlBinding("/record/{$event}/{record.activityRecordId}")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean
    protected ActivityRecordService activityRecordService;

    @ValidateNestedProperties(value = {
        @Validate(on = {"createRecord", "save"}, field = "activityName", required = true),
        @Validate(on = {"createRecord", "save"}, field = "duration", required = true, minvalue = 1),
        @Validate(on = {"createRecord", "save"}, field = "activityDate", required = true)
    })
    private ActivityRecordDto record;

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
        record.setCaloriesBurnt(1); //TODO
        record.setUserId(1L); //TODO
        record.setWeightCatNum(1); //TODO
        Long createdId = activityRecordService.create(record);
        getContext().getMessages().add(new LocalizableMessage("record.create.message", escapeHTML(record.getActivityName()), escapeHTML(String.valueOf(record.getDuration())), escapeHTML(String.valueOf(record.getCaloriesBurnt()))));
        log.debug("Created activity record with id " + createdId);
        log.debug(activityRecordService.get(createdId).toString());
        log.debug(new LocalizableMessage("record.create.message", escapeHTML(record.getActivityName()), escapeHTML(String.valueOf(record.getDuration())), escapeHTML(String.valueOf(record.getCaloriesBurnt()))).toString());
        return new ForwardResolution("/record/list.jsp");
    }

    // <editor-fold desc="Editing a record">//GEN-BEGIN:edit
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadRecordFromDatabase() {
        String ids = getContext().getRequest().getParameter("record.activityRecordId");
        if (ids == null) {
            return;
        }
        record = activityRecordService.get(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() record={}", record);
        return new ForwardResolution("/record/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() record={}", record);
        activityRecordService.update(record);;
        return new RedirectResolution("records/list.jsp");
    }
    // </editor-fold>//GEN-END:edit

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        log.debug("Error check");
        return null;
    }
}
