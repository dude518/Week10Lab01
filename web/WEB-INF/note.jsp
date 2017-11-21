<%-- 
    Document   : note
    Created on : Nov 21, 2017, 11:47:15 AM
    Author     : 578291
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsf/header.jspf" %>
<form method="GET" action="account">
    <input type="submit" value="Edit Account">
</form>
<h1>Notes Page</h1>
<br>
<h3>Add Note</h3>
<br>
<div>
    <form action="notes?action=add" method="POST">
        Title: <input type="text" name="newtitle">
        Note: <input type="text" name="newcontent">
        <input type="submit" name="submit" value="Add">
    </form>
</div>
<p>${message}</p>
<br>
<div>
<c:if test="${view != null}">
    <h3>Edit Note</h3>
    <form action="notes?action=edit" method="POST">
        Title: <input type="text" name="edittitle" value="${edittitle}">
        Note: <input type="text" name="editcontent" value="${editcontent}">
        <input type="hidden" name="editid" value="${editid}">
        <input type="submit" name="submit" value="Edit">
    </form>
</c:if>
</div>
<br>
<h3>Notes</h3>
<div>
    <table>
        <tr>
            <th>Title</th>
        </tr>
        <c:if test="${notes.size() > 0}">
            <c:forEach var="note" items="${notes}">
                <tr>
                    <th>${note.getTitle()}</th>
                    <th>
                        <form method="GET" action="notes">
                            <input type="hidden" name="selectednote" value="${note.getNoteID()}">
                            <input type="hidden" name="action" value="editview">
                            <input type="submit" name="submit" value="Edit">
                        </form>
                    </th>
                    <th>
                        <form method="POST" action="notes?action=delete">
                            <input type="hidden" name="selectednote" value="${note.getNoteID()}">
                            <input type="submit" name="submit" value="Delete">
                        </form>
                    </th>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>

<c:import url="/includes/footer.html"/>