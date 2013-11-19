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

/**
 * Stripes ActionBean for handling record operations.
 *
 * @author Bryndza
 */
@UrlBinding("/record/{$event}/{record.id}")
public class RecordActionBean extends BaseActionBean implements ValidationErrorHandler {

    final static Logger log = LoggerFactory.getLogger(RecordActionBean.class);
    @SpringBean //Spring can inject even to private and protected fields
    protected ActivityRecordService activityRecordService;
    private ActivityRecordDto record;

    @DefaultHandler
    public Resolution create() {
        log.debug("list()");
        return new ForwardResolution("/record/create.jsp");
    }

    //part for editing a record
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadRecordFromDatabase() {
        String ids = getContext().getRequest().getParameter("record.id");
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

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
