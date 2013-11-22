<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="records.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean" var="actionBean"/>

        <p><f:message key="record.list.allrecords"/></p>

        <table class="basic">
            <tr>
                <th rowspan="2"><f:message key="activity.name"/></th>
                <th><f:message key="activity.calories"/></th>
            </tr>
            <tr>
                <th><f:message key="activity.weightCat130"/></th>
                <th><f:message key="activity.weightCat155"/></th>
                <th><f:message key="activity.weightCat180"/></th>
                <th><f:message key="activity.weightCat205"/></th>
            </tr>
            <c:forEach items="${actionBean.activities}" var="activity">
                <tr>
                    <td><c:out value="${activity.name}"/></td>
                    <!-- to do -->
                    <td><c:out value="${activity.weightCat130}"/></td>
                    <td><c:out value="${activity.weightCat155}"/></td>
                    <td><c:out value="${activity.weightCat180}"/></td>
                    <td><c:out value="${activity.weightCat205}"/></td>
                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>