/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.ActivityService;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.ActivityDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserRole;
import java.io.IOException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin
 */
@RequireLogin(role = UserRole.ADMIN)
@UrlBinding("/admin")
public class AdministratorActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(AdministratorActionBean.class);

    @SpringBean //Spring can inject even to private and protected fields
    protected ActivityService activityService;

    private String returnBean;
    private boolean removeDeprecated;
    private ActivityDto activity;
    private boolean edit;

    public boolean isEdit() {
        return edit;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public boolean isRemoveDeprecated() {
        return removeDeprecated;
    }

    public void setRemoveDeprecated(boolean removeDeprecated) {
        this.removeDeprecated = removeDeprecated;
    }

    public String getReturnBean() {
        return returnBean;
    }

    @DefaultHandler
    public Resolution def() {
        log.debug("def()");
        return new ForwardResolution("/index.jsp");
    }

    public Resolution updateActivitiesFromPage() {
        log.debug("updateActivitiesFromPage");
        return new ForwardResolution("/administrator/updateActivities.jsp");
    }

    public Resolution updateActivities() {
        log.debug("update()");
        try {
            activityService.updateFromPage(removeDeprecated);
            this.getContext().getMessages().add(new LocalizableMessage("activities.update.success"));
        } catch (IOException e) {
            this.getContext().getValidationErrors().addGlobalError(new LocalizableError("activities.update.IOError"));
        }
        returnBean = "cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean";
        return new ForwardResolution("/administrator/message.jsp");
    }

    public Resolution cancelOperationActivity() {
        log.debug("cancel()");
        edit = false;
        return new RedirectResolution(ActivitiesActionBean.class);
    }

    public Resolution createActivity() {
        log.debug("createActivity(): " + activity);
        return new ForwardResolution("/administrator/createActivity.jsp");
    }

    public Resolution confirmCreateActivity() {
        log.debug("confirmCreateActivity(): " + activity);
        activityService.create(activity);
        this.getContext().getMessages().add(new LocalizableMessage("activity.create.success", escapeHTML(activity.getActivityName().toString())));
        returnBean = "cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean";
        return new ForwardResolution("/administrator/message.jsp");
    }

    public Resolution editActivity() {
        log.debug("editActivity(): " + activity);
        edit = true;
        return new ForwardResolution("/administrator/editActivity.jsp");
    }

    public Resolution confirmEditActivity() {
        log.debug("confirmEditActivity(): " + activity);
        edit = false;
        activityService.update(activity);
        this.getContext().getMessages().add(new LocalizableMessage("activity.edit.success", escapeHTML(activity.getActivityName().toString())));
        returnBean = "cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean";
        return new ForwardResolution("/administrator/message.jsp");
    }

    public Resolution deleteActivity() {
        log.debug("deleteActivity(): " + activity);
        return new ForwardResolution("/administrator/deleteActivity.jsp");
    }

    public Resolution confirmDeleteActivity() {
        log.debug("confirmDeleteActivity(): " + activity);
        activityService.remove(activity.getActivityId());
        this.getContext().getMessages().add(new LocalizableMessage("activity.delete.success", escapeHTML(activity.getActivityName().toString())));
        returnBean = "cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean";
        return new ForwardResolution("/administrator/message.jsp");
    }
}
