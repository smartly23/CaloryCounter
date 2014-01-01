<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="stats.ladder.title">
    <s:layout-component name="header">
        <script type='text/javascript' src='https://www.google.com/jsapi'></script>
    </s:layout-component>
    <s:layout-component name="body">

        <h2><f:message key="stats.ladder.title"/></h2>

        <div id="globalLadder"></div>
        <script type='text/javascript'>
            google.load('visualization', '1', {packages: ['table']});
            google.setOnLoadCallback(drawTable);
            function drawTable() {
                var data = new google.visualization.DataTable();
                data.addColumn('string', '<f:message key="stats.username"/>');
                data.addColumn('number', '<f:message key="stats.caloriessum"/>');
                data.addColumn('number', '<f:message key="stats.durationsum"/>');
                data.addRows([
            <c:forEach items="${actionBean.usersStats}" var="userStats" varStatus="loopCounter" >
                ['<c:out value="${userStats.nameOfUser}"/>',
                <c:out value="${userStats.sumBurntCalories}"/>,
                <c:out value="${userStats.sumDuration}"/>]<c:if test="${!loop.last}">,</c:if>
                <c:if test="${userStats.userId == actionBean.user.userId}"><c:set var="rowIndex" value="${loopCounter.count-1}"/></c:if>
            </c:forEach>
                ]);
                        data.setProperty(<c:out value="${rowIndex}"/>, 0, 'style', 'font-weight: bold; background-color: #9CD16A');
                data.setProperty(<c:out value="${rowIndex}"/>, 1, 'style', 'font-weight: bold; background-color: #9CD16A');
                data.setProperty(<c:out value="${rowIndex}"/>, 2, 'style', 'font-weight: bold; background-color: #9CD16A');


                var table = new google.visualization.Table(document.getElementById('globalLadder'));
                table.draw(data, {showRowNumber: true, allowHtml: true});
            }
        </script>

    </s:layout-component>
</s:layout-render>