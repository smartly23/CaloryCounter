package cz.fi.muni.pa165.calorycounter.frontend;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.taglibs.standard.functions.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base actionBean implementing the required methods for setting and getting
 * context.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public abstract class BaseActionBean implements ActionBean {

    private ActionBeanContext context;
    protected AuthUserDto user;
    final static Logger log = LoggerFactory.getLogger(BaseActionBean.class);

    public AuthUserDto getUser() {
        return user;
    }

    public void setUser(AuthUserDto user) {
        this.user = user;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadUser() {
        user = (AuthUserDto) getContext().getRequest().getSession().getAttribute("user");
        log.debug("User: " + user);
    }
}
