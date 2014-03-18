<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="users.titlekey">
    <s:layout-component name="body">

        <h2><f:message key="sample.title"/></h2>

        <form id="searchForm" action="/ukazka.jsp" method="post">
            <label for="username"><f:message key="users.findbyusername"/>:</label>
            <input id="searchUsername" name="searchUsername" onkeyup="suggest(this.value);" autocomplete="off"/>
            <input type="submit" name="search" value="<f:message key="users.findBtn"/>"/>
            <div id="suggestions"></div>
        </form>

        <br/>
        <div id="userInfo" style="display: none">
            <form id="userForm" action="/ukazka.jsp" method="post">
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

        <script type="text/javascript">

                var xhr = new XMLHttpRequest();

                function suggest(str) {
                    if (str.length === 0) {
                        document.getElementById('suggestions').innerHtml = '';
                        return;
                    }
                        xhr.open("GET", "http://localhost:8080/CaloryCounter-Web/resources/profile/getusers/" + escape(str), true);
                        xhr.onreadystatechange = printSuggestion();
                        xhr.send();
                }

                function printSuggestion() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var outerDiv = document.getElementById('suggestions');
                        outerDiv.innerHtml = '';
                        var namesArray = xhr.responseText.split(", ");
                        for (i = 0; i < namesArray.length; i++) {
                            var resultBlocks = '<div onmouseover="javascript:suggestOver(this);" ';
                            resultBlocks += 'onmouseout="javascript:suggestOut(this);" ';
                            resultBlocks += 'onclick="javascript:setSearch(this.innerHTML);" ';
                            resultBlocks += 'class="link">' + namesArray[i] + '</div>';
                            outerDiv.innerHtml += resultBlocks;
                        }
                        console.log(outerDiv.innerHtml);
                    }
                }

                function suggestOver(element) {
                    element.className = "link_mouseover";
                }

                function suggestOut(element) {
                    element.className = "link";
                }

                function setSearch(element_value) {
                    document.getElementById('searchUsername').value = element_value;
                    document.getElementById('suggestions').innerHtml = '';
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
                });
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
