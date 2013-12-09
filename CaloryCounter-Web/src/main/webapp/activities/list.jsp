<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="activities.list.title">
    <s:layout-component name="body">
        
        <h2><f:message key="activities.list.title"/></h2>

        <table class="basic">
            <tr>
                <th rowspan="2"><f:message key="activity.name"/></th>
                <th colspan="4"><f:message key="activity.calories"/></th>
            </tr>
            <tr>
                <th><f:message key="activity.weightCat1"/></th>
                <th><f:message key="activity.weightCat2"/></th>
                <th><f:message key="activity.weightCat3"/></th>
                <th><f:message key="activity.weightCat4"/></th>
            </tr>
            <c:forEach items="${actionBean.activities}" var="activity">
                <tr>
                    <td class="col1"><c:out value="${activity.activityName}"/></td>
                    <c:forEach items="${activity.weightCalories}" var="weightCalories">
                        <td><c:out value="${weightCalories.value}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>

    </s:layout-component>
</s:layout-render>