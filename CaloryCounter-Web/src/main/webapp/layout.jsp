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
                    <div id="logo"><s:link href="/index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="" title="" border="0" width="95" height="84"></s:link></div>
                    <div id="profile"><p><s:link href="/profile/show.jsp"><f:message key="navigation.editprofile"/></s:link></p></div>
                        <div id="menu">
                            <ul>
                                <li><s:link class="current" href="/index.jsp"><f:message key="navigation.home"/></s:link></li>
                            <li><s:link href="/record/create.jsp"><f:message key="navigation.new_record"/></s:link></li>
                            <li><s:link href="/records/list.jsp"><f:message key="navigation.my_records"/></s:link></li>
                            <li><s:link href="/global_ladder.jsp"><f:message key="navigation.global_ladder"/></s:link></li>
                            <li><s:link href="/activities.jsp"><f:message key="navigation.activities"/></s:link></li>
                            </ul>
                        </div>
                    </div>

                    <div class="green_box">
                    <s:messages/>
                </div>

                <div id="main_content">

                    <s:messages/>
                    <s:layout-component name="body"/>

                    <div style=" clear:both;"></div>
                </div><!--end of main content-->


                <div id="footer">
                    <div class="copyright">
                        <a href="home.html"><img src="/images/footer_logo.gif" alt="" title="" border="0"></a>
                    </div>
                    <div class="footer_links">
                        <a href="#">About us</a>
                        <a href="privacy.html">Privacy policy</a>
                        <a href="contact.html">Contact us </a>
                    </div>
                </div>
            </div> <!--end of main container-->
        </body>
    </html>
</s:layout-definition>