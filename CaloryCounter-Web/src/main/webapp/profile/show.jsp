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
            <c:out value="Username: ${user.username}"/>
            <c:out value="Name: ${user.name}"/>
            <c:out value="Age: ${user.age}"/>
            <c:out value="Sex: ${user.sex}"/>
            <c:out value="Weight category: ${user.weightCatNum.showedCategory}"/>
            <br>
            <s:link beanclass="cz.fi.muni.pa165.calorycounter.frontend.ProfileActionBean" event="edit">
                <s:param name="user.id" value="${user.userId}"/><f:message key="profile.edit"/>
            </s:link>
        </div>
    </s:layout-component>
</s:layout-render>