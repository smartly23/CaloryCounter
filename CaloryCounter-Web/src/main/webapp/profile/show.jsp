<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" var="profileActionBean"/>
        <!-- treba, lebo pri prvom prideni na tuto stranku z layoutu sme este neforwardovali z ProfileBeanu,
        teda neni pribaleny konkretny ActionBean -->
        <p><f:message key="navigation.editprofile"/></p>

        <div>
            <br><f:message key="profile.username"/><c:out value=" ${profileActionBean.user.username}"/><br>
            <f:message key="profile.name"/><c:out value=" ${profileActionBean.user.name}"/><br>
            <f:message key="profile.age"/><c:out value=" ${profileActionBean.user.age}"/><br>
            <f:message key="profile.sex"/><c:out value=" ${profileActionBean.user.sex}"/><br>
            <f:message key="profile.weight"/><c:out value=" ${profileActionBean.user.weightCatNum.showedCategory}"/>
            <br>
            <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" event="edit">
                <s:param name="user.id" value="${profileActionBean.user.userId}"/><f:message key="profile.edit"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>