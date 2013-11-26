<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:errors/>
<s:hidden name="record.userId"/>
<s:hidden name="record.weightCatNum"/>
<s:hidden name="record.caloriesBurnt"/>
<table>
    <tr>
        <th><s:label for="activityName" name="record.activity"/></th>
        <td><s:text id="activityName" name="record.activityName"/></td>
    </tr>
    <tr>
        <th><s:label for="duration" name="record.duration"/></th>
        <td><s:text id="duration" name="record.duration" size="4"/> <f:message key="minutes"/></td>
    </tr>
    <tr>
        <th><s:label for="date" name="record.date"/></th>
        <td><s:text id="date" name="record.activityDate"/></td>
    </tr>
</table>
<script>
    $(function() {
        $("#date").datepicker();
    });
</script>
