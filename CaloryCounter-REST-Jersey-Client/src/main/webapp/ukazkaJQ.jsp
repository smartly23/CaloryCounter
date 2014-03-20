<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="users.titlekey">
    <s:layout-component name="body">

        <div id="profile">
            <p><div id="welcome"></div><a id="login" class="link_" href="#"><f:message key="navigation.login"/></a></p>
    </div>
    <h2><f:message key="sample.title"/></h2>

    <form id="searchForm" action="/ukazkaJQ.jsp" method="post">
        <label for="searchUsername"><f:message key="users.findbyusername"/>:</label>
        <input id="searchUsername" name="searchUsername" onkeyup="suggestJQResistant(this.value);" autocomplete="off"/>
        <input type="submit" name="search" value="<f:message key="users.findBtn"/>"/>
    </form>
    <div id="suggestions"></div>

    <br/>
    <div id="userInfo" style="display: none">
        <form id="userForm" action="/ukazkaJQ.jsp" method="post">
            <div>
                <label for="username"><f:message key="users.username"/>:</label>
                <input id="username" name="username" value=""/>
            </div>
            <div>
                <label for="name"><f:message key="users.name"/>:</label>
                <input id="name" name="name" value=""/>
            </div>
            <div>
                <label for="age"><f:message key="users.age"/>:</label>
                <input id="age" name="age" value=""/>
            </div>
            <div>
                <label for="sex"><f:message key="users.sex"/>:</label>
                <select id="sex" name="sex">
                    <option value="Male"><f:message key="users.sex.male"/></option>
                    <option value="Female"><f:message key="users.sex.female"/></option>
                    <option value="Other"><f:message key="users.sex.other"/></option>
                </select>
            </div>
            <div>
                <label for="weightCategory"><f:message key="users.weightCategory"/>:</label>
                <select id="weightCategory" name="weightCategory">
                    <option value="_130_"><f:message key="users.weightCat._130_"/></option>
                    <option value="_155_"><f:message key="users.weightCat._155_"/></option>
                    <option value="_180_"><f:message key="users.weightCat._180_"/></option>
                    <option value="_205_"><f:message key="users.weightCat._205_"/></option>
                </select>
            </div>
            <div id="buttons">
                <input type="hidden" id="userId" name="userId" value=""/>
                <input type="hidden" id="usernameKey" name="usernameKey" value=""/>
                <input type="submit" name="edit" value="<f:message key="users.editBtn"/>"/>
                <input type="submit" name="delete" value="<f:message key="users.deleteBtn"/>"/>
            </div>
        </form>
    </div>

    <div id="loginForm"> 
        <form name="login" action="" method="post">
            <center>Username:</center>
            <center><input name="username" size="14" /></center>
            <center>Password:</center>
            <center><input name="password" type="password" size="14" /></center>
            <center><input type="submit" name="submit" value="Login" /></center>
            <div id="loginStatus"></div>
        </form>
        <br />
        <center><a id="closeLoginForm" href="">close</a></center> 
    </div> 

    <script type="text/javascript">

            function suggestJQ(str) {
                if (str.length === 0) {
                    $("#suggestions").html("");
                    return;
                }
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        printSuggestionJQ(xhr);
                        $("#suggestions div").mouseover(function() {
                            $(this).addClass("link_mouseover").removeClass("link");
                        });
                        $("#suggestions div").mouseout(function() {
                            $(this).addClass("link").removeClass("link_mouseover");
                        });
                        $("#suggestions div").click(function() {
                            $("#searchUsername").val($(this).text());
                            $("#suggestions").hide();
                            $("#suggestions").html("");
                        });
                    }
                };
                xhr.open("GET", "http://localhost:8080/CaloryCounter-Web/resources/profile/getusers/" + escape(str), true);
                xhr.send();
            }

            function printSuggestionJQ(xhrequest) {
                $("#suggestions").html("");
                $("#suggestions").show();
                var namesArray = xhrequest.responseText.split(", ");
                var row;
                for (i = 0; i < namesArray.length; i++) {
                    row = $("<div></div>").attr("class", "link").text(namesArray[i]);
                    $("#suggestions").append(row);
                }
            }

            function suggestJQResistant(str) {
                if (str.length === 0) {
                    $("#suggestions").html("");
                    return;
                }
                var request = $.ajax({
                    url: "http://localhost:8080/CaloryCounter-Web/resources/profile/getusers_resistant/" + escape(str),
                    type: "GET",
                    dataType: "text",
                    success: printSuggestionJQResistant
                });
                request.fail(function(jqXHR, textStatus) {
                    console.log("Error when requesting suggestions. Status: " + textStatus);
                });
            }
            function printSuggestionJQResistant(dataFromServer) {   // these data will be injected automatically
                $("#suggestions").html("");
                $("#suggestions").show();
                var namesArray = dataFromServer.split(", ");
                var row;
                for (i = 0; i < namesArray.length; i++) {
                    row = $("<div></div>").attr("class", "link").text(namesArray[i]);
                    $("#suggestions").append(row);
                }
                $("#suggestions div").mouseover(function() {
                    $(this).addClass("link_mouseover").removeClass("link");
                });
                $("#suggestions div").mouseout(function() {
                    $(this).addClass("link").removeClass("link_mouseover");
                });
                $("#suggestions div").click(function() {
                    $("#searchUsername").val($(this).text());
                    $("#suggestions").hide();
                    $("#suggestions").html("");
                });
            }authUser

            $('#login').hover(
                    function() {
                        $(this).removeClass("link_");
                        $(this).addClass("link_over");
                    },
                    function() {
                        $(this).removeClass("link_over");
                        $(this).addClass("link_");
                    });
//            $("#login").mouseover(function() {
//                $("#login").addClass("link_over");
//                $("#login").removeClass("link_");
//            }
//            );
//            $("#login").mouseout(function() {
//                $("#login").removeClass("link_over");
//                $("#login").addClass("link_");
//            });

            $("#login").click(function() {
                $("#loginForm").css("visibility", "visible");
            });

            $("#closeLoginForm").click(closeLoginForm());

            $("#loginForm").submit(processForm());

            function closeLoginForm() {
                $("#loginForm").css("visibility", "hidden");
            }

            function failLogin(status) {
                $("#loginStatus").text("Login failed: " + status);
            }

            function successLogin() {
                closeLoginForm();
                $("#welcome").text("Welcome, ");
            }

            function processForm() {
                var data = '{"uname" : "' + $("#loginForm input[name=username]").val() + '", ' +
                        '"pwd" : "' + $("#loginForm input[type=password]").val() + '" }';
                var request = $.ajax({
                    url: "http://localhost:8080/CaloryCounter-Web/resources/profile/authUser",
                    type: "POST",
                    dataType: "json",
                    crossDomain: true,
                    contentType: "application/json; charset=utf-8",
                    data: data,
                    success: successLogin
                });
                request.fail(function(jqXHR, textStatus) {
                    failLogin(textStatus);
                });
            }

            $("#searchForm").submit(function(event) {
                if ($("#searchForm input[type=submit][clicked=true]").attr("name") == "search") {
                    var request = $.ajax({
                        url: "http://localhost:8080/CaloryCounter-Web/resources/profile/getuser/" + $("#searchUsername").val(),
                        type: "GET",
                        dataType: "json",
                        crossDomain: true,
                        success: getUserSuccess
                    });
                    request.fail(function(jqXHR, textStatus) {
                        $("#userInfo").hide();
                    });
                }
                event.preventDefault();
            }
            );
            function getUserSuccess(data) {
                $("#userForm input[name='edit']").show();
                $("#userForm input[name='delete']").show();
                $("#userInfo").show();
                $("#username").val(data.username);
                $("#username").prop('disabled', true);
                $("#password").val(data.password);
                $("#password").prop('disabled', true);
                $("#name").val(data.name);
                $("#age").val(data.age);
                $("#sex").val(data.sex);
                $("#weightCategory").val(data.weightCategory);
                $("#usernameKey").val(data.username);
                $("#userId").val(data.userId);
            }


            $("#userForm").submit(function(event) {
                if ($("#username").val() == "") {
                } else if ($("#userForm input[type=submit][clicked=true]").attr("name") == "edit") {
                    var data = '{ "username" : "' + $("#username").val() + '", ' +
                            '"userId" : "' + $("#userId").val() + '", ' +
                            '"password" : "' + $("#password").val() + '", ' +
                            '"name" : "' + $("#name").val() + '", ' +
                            '"sex" : "' + $("#sex").val() + '", ' +
                            '"weightCategory" : "' + $("#weightCategory").val() + '", ' +
                            '"age" : "' + $("#age").val() + '" }';
                    var request = $.ajax({
                        url: "http://localhost:8080/CaloryCounter-Web/resources/profile/updateuser",
                        type: "PUT",
                        dataType: "json",
                        crossDomain: true,
                        contentType: "application/json; charset=utf-8",
                        data: data,
                        success: editUserSuccess
                    });
                    request.fail(function(jqXHR, textStatus) {
                        $("#userInfo").hide();
                    });
                } else if ($("#userForm input[type=submit][clicked=true]").attr("name") == "delete") {
                    var request = $.ajax({
                        url: "http://localhost:8080/CaloryCounter-Web/resources/profile/removeuser/" + $("#usernameKey").val(),
                        type: "DELETE",
                        contentType: "application/json; charset=utf-8",
                        crossDomain: true,
                        success: deleteUserSuccess
                    });
                    request.fail(function(jqXHR, textStatus) {
                        $("#userInfo").hide();
                    });
                }
                event.preventDefault();
            });
            function editUserSuccess(data) {
                $("#userInfo").hide();
            }
            function deleteUserSuccess() {
                $("#userInfo").hide();
                $("#searchUsername").val("");
            }

            $("#userForm input[type=submit]").click(function() {
                $("#userForm input[type=submit]").removeAttr("clicked");
                $(this).attr("clicked", "true");
            });
            $("#searchForm input[type=submit]").click(function() {
                $("#searchForm input[type=submit]").removeAttr("clicked");
                $(this).attr("clicked", "true");
            });

    </script>

</s:layout-component>
</s:layout-render>
