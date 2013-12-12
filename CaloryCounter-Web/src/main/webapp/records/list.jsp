<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="records.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordsActionBean" var="actionBean"/>

        <h2><f:message key="records.list.title"/></h2>

        <table class="basic">
            <tr>
                <th><f:message key="record.date"/></th>
                <th><f:message key="record.activity"/></th>
                <th><f:message key="record.duration"/></th>
                <th><f:message key="record.burnt_calories"/></th>
                <th><f:message key="record.weight"/></th>
            </tr>
            <c:forEach items="${actionBean.uards.activityRecords}" var="activityRecord">
                <tr>
                    <td>
                        <f:formatDate pattern="dd.MM. yyyy" value="${activityRecord.activityDate}" />
                    </td>
                    <td><c:out value="${activityRecord.activityName}"/></td>
                    <td><c:out value="${activityRecord.duration}"/></td>
                    <td><c:out value="${activityRecord.caloriesBurnt}"/></td>
                    <td><f:message key="activity.weightCat${activityRecord.weightCategory}"/></td>
                    <td>
                        <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean" event="edit"><s:param name="record.activityRecordId" value="${activityRecord.activityRecordId}" /><f:message key="records.list.edit"/></s:link>
                        <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.RecordActionBean" event="delete"><s:param name="record.activityRecordId" value="${activityRecord.activityRecordId}" /><f:message key="records.list.delete"/></s:link>
                        </td>
                    </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>