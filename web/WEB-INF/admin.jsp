<%-- 
    Document   : admin
    Created on : Nov 21, 2017, 11:47:01 AM
    Author     : 578291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsf/header.jspf" %>
<h1>Manage Users</h1>
<br>
<h3>Add User</h3>
<br>
<div>
    <form action="admin?action=add" method="POST">
        Username: <input type="text" name="newusername">
        Password: <input type="text" name="newpassword">
        Email: <input type="text" name="newemail">
        First name: <input type="text" name="newfirstname">
        Last name: <input type="text" name="newlastname">
        <input type="submit" name="submit">
    </form>
</div>
<p>${message}</p>
<br>
<div>
    <c:if test="${view != null}">
        <h3>Edit User</h3>
        <form action="admin?action=edit" method="POST">
            Username: <input type="text" name="editusername" value="${editusername}">
            Password: <input tpye="text" name="editpassword" value="${editpassword}">
            Email: <input type="text" name="editemail" value="${editemail}">
            First name: <input type="text" name="editfirstname" value="${editfirstname}">
            Last name: <input type="text" name="editlastname" value="${editfirstname}">
            <input type="submit" name="submit">
        </form>
    </c:if>
</div>
<br>
<div>
    <h3>All Users</h3>
    <br>
    <table>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        <c:if test="${users.size() > 0}">
            <c:forEach var="user" items="${users}">
                <c:if test="${!username.equals(user.getUsername())}">
                <tr>
                    <th>${user.getUsername()}</th>
                    <th>${user.getEmail()}</th>
                    <th>
                        <form method="POST" action="admin?action=delete">
                            <input type="hidden" name="selectedname" value="${user.getUsername()}">
                            <input type="submit" name="delete" value="Delete">
                        </form>
                    </th>
                    <th>
                        <form method="GET" action="admin">
                            <input type="hidden" name="selectedname" value="${user.getUsername()}">
                            <input type="hidden" name="action" value="editview">
                            <input type="submit" name="edit" value="Edit">
                        </form>
                    </th>
                </tr>
                </c:if>
            </c:forEach>
        </c:if>
    </table>
</div>
<c:import url="/includes/footer.html"/>
