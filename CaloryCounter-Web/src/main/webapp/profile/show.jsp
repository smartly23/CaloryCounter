<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="profile.titlekey">
    <s:layout-component name="body">
        <%--<s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" var="actionBean"/>--%>
        <!--vzhladom na najnovsi layout.jsp (link je na actionbeanu (spravne!), nie JSPcko), uz toto netreba -->
        <p><f:message key="navigation.editprofile"/></p>

        <div>
            <br><f:message key="profile.username"/><c:out value=" ${actionBean.user.username}"/><br>
            <f:message key="profile.name"/><c:out value=" ${actionBean.user.name}"/><br>
            <f:message key="profile.age"/><c:out value=" ${actionBean.user.age}"/><br>
            <f:message key="profile.sex"/><c:out value=" ${actionBean.user.sex}"/><br>
            <f:message key="profile.weight"/><c:out value=" ${actionBean.user.weightCatNum.showedCategory}"/>
            <br>
            <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" event="edit">
                <s:param name="user.username" value="${actionBean.user.username}"/><f:message key="profile.edit"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>