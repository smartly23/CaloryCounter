<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="home.titlekey">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.IndexActionBean" var="indexActionBean"/>
        <h2><f:message key="home.title"/></h2>

        <p><f:message key="home.text.p1"/></p>

    </s:layout-component>
</s:layout-render>
