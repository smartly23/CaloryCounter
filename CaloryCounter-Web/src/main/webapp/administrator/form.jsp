<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-component name="header">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</s:layout-component>
<s:errors/>
<s:hidden name="record.userId" value="${actionBean.user.userId}"/>
<s:hidden name="record.weightCategory" value="${actionBean.user.weightCategory}"/>
<table>
    <tr>
        <th><s:label for="activityName" name="activity.name"/></th>
        <td><s:text id="activityName" name="activity.activityName" disabled="${actionBean.edit}"/></td>
    </tr>
    <tr>
        <th><s:label for="130" name="activity.weightCat_130_"/></th>
        <td><s:text id="130" name="activity.weightCalories" size="4"/> <f:message key="weight.unit"/></td>
    </tr>
    <tr>
        <th><s:label for="155" name="activity.weightCat_155_"/></th>
        <td><s:text id="155" name="activity.weightCalories" size="4"/> <f:message key="weight.unit"/></td>
    </tr>
    <tr>
        <th><s:label for="130" name="activity.weightCat_130_"/></th>
        <td><s:text id="130" name="activity.weightCalories" size="4"/> <f:message key="weight.unit"/></td>
    </tr>
</table>
<script>
    $(function() {
        $("#activityDate").datepicker();
    });
</script>
