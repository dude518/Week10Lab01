<%-- 
    Document   : login
    Created on : Nov 21, 2017, 11:46:28 AM
    Author     : 578291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html"/>
<h1>NotesKeepr</h1>
<br>
<div>
    <form action="login?action=login" method="POST">
        Username: <input type="text" name="username"><br/>
        Password: <input type="password" name="password"><br/>
        <input type="submit" name="submit" value="Log in">
    </form>
    <p>${message}</p>
</div>
<c:import url="/includes/footer.html"/>