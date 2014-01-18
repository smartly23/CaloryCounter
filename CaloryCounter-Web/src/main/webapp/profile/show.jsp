<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="profile.titlekey">
    <s:layout-component name="body">
        <%--<s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" var="actionBean"/>--%>
        <!--vzhladom na najnovsi layout.jsp (link je na actionbeanu (spravne!), nie JSPcko), uz toto netreba -->
        <h2><f:message key="navigation.editprofile"/></h2>

        <div id="profileAttr">
            <f:message key="profile.username"/><strong><c:out value=" ${actionBean.user.username}"/></strong><br>
            <f:message key="profile.name"/><strong><c:out value=" ${actionBean.user.name}"/></strong><br>
            <f:message key="profile.age"/><strong><c:out value=" ${actionBean.user.age}"/></strong><br>
            <f:message key="profile.sex"/><strong><c:out value=" ${actionBean.user.sex}"/></strong><br>
            <f:message key="profile.weight"/><strong><c:out value=" ${actionBean.user.weightCategory.showedCategory}"/></strong>
            <br>
            <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" event="edit">
                <s:param name="user.username" value="${actionBean.user.username}"/><f:message key="profile.edit"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>