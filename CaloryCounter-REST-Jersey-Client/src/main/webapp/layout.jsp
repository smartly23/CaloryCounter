<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <s:layout-component name="header"/>
        </head>

        <body>
            <div id="main_container">
                <div id="header">
                    <div id="logo"><s:link href="/index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo" title="${topTitle}"/></s:link></div>
                    <h1 id="topTitle"><s:link href="/index.jsp"><f:message key="topTitle"/></s:link></h1>

                        <ul id="menu">
                            <li id="first"><s:link href="/index.jsp"><f:message key="navigation.home"/></s:link></li>
                        <li><s:link href="/users.jsp"><f:message key="navigation.users"/></s:link></li>
                        <li><s:link href="/activities.jsp"><f:message key="navigation.activities"/></s:link></li>
                        </ul>

                    </div>

                    <div class="green_box">
                    <s:messages/>
                </div>

                <div id="main_content">
                    <s:layout-component name="body"/>

                    <div style=" clear:both;"></div>
                </div><!--end of main content-->


                <div id="footer">
                    <div class="copyright">
                        (c) MUFI
                    </div>
                </div>
            </div> <!--end of main container-->
        </body>
    </html>
</s:layout-definition>
