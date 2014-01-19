<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="activities.list.title" pagename="activities" currentPage="activities">
    <s:layout-component name="body">

        <h2><f:message key="activities.list.title"/></h2>

        <table class="basic">
            <tr>
                <th rowspan="2"><f:message key="activity.name"/></th>
                <th colspan="4"><f:message key="activity.calories"/></th>
            </tr>
            <tr>
                <th><f:message key="activity.weightCat_130_"/></th>
                <th><f:message key="activity.weightCat_155_"/></th>
                <th><f:message key="activity.weightCat_180_"/></th>
                <th><f:message key="activity.weightCat_205_"/></th>
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
        <c:if test="${sessionScope.user!=null}">
            <p>
                <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.ActivitiesActionBean" event="update"><f:message key="activities.update"/></s:link>
                </p>
        </c:if>
    </s:layout-component>
</s:layout-render>