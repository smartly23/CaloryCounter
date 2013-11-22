<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="record.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean" var="actionBean"/>

        <s:form beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean">
            <s:hidden name="record.activityRecordId"/>
            <fieldset>
                <legend>
                    <f:message key="record.edit.edit"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="save">
                    <f:message key="record.edit.save"/>
                </s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>