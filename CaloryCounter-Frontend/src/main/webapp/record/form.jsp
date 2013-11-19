<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="record.activity"/></th>
        <td><s:text id="b1" name="record.activity"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="record.duration"/></th>
        <td><s:text id="b2" name="record.duration.hour" size="1"/>h <s:text id="b3" name="record.duration.minute" size="1"/>m</td>
    </tr>
    <tr>
        <th><s:label for="b4" name="record.date"/></th>
        <td><s:text id="b4" name="record.date"/></td>
    </tr>
</table>