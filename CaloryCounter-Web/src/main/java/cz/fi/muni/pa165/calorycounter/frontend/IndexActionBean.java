/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.frontend;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Bryndza
 */
@DoesNotRequireLogin
@UrlBinding("/")
public class IndexActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(IndexActionBean.class);

    @DefaultHandler
    public Resolution index() {
        log.debug("index()");
        return new ForwardResolution("/index.jsp");
    }

}
