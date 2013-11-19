<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="record.create.title">
    <s:layout-component name="body">
        <s:form beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean">
            <fieldset><legend><f:message key="record.create.legend"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="record.create.save"/></s:submit>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>