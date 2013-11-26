<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="record.create.title">
    <s:layout-component name="header">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    </s:layout-component>
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean" var="actionBean"/>
        <s:form beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean" action="createRecord" focus="" >
            <fieldset>
                <legend>
                    <f:message key="record.create.legend"/>
                </legend>
                <%@include file="form.jsp"%>
                <s:submit name="createRecord">
                    <f:message key="record.create"/>
                </s:submit>
            </fieldset>
        </s:form>
        <s:messages/>
    </s:layout-component>
</s:layout-render>