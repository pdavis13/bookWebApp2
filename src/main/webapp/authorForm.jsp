<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add/Edit Author</title>
    </head>
    <body>
    <c:choose>
        <c:when test="${authorAdd}">
            <form id="add" name="add" method="POST" action="authorController?action=add">
                <input type="text" name="authorName" placeholder="Enter author here">
                <input type="submit" name="add" value="Add">
            </form>
        </c:when>
        <c:otherwise>
            <form id="edit" name="edit" method="POST" action="authorController?action=edit">
                <input type="text" name="authorName" value="${authorName}">
                <button name="authorId" type="submit" value="${authorId}">Edit</button>
            </form>
        </c:otherwise>
    </c:choose>
        
    </body>
</html>
