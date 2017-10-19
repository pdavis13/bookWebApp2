<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
        <h1>Author List</h1>
        <h2>${errMessage}</h2>
        <form id="add" name="add" method="POST" action="authorController?action=form">
            <button name="id" type="submit" value="add">Add</button>
        </form>
        <br />
        <table border="1">
            <c:forEach var="a" items="${authorList}">
                <tr>
                    <td>${a.authorId}</td>
                    <td>${a.authorName}</td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                    <td>
                        <form id="edit${a.authorId}" name="edit${a.authorId}" method="POST" action="authorController?action=form">
                            <button name="id" type="submit" value="${a.authorId}">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form id="delete${a.authorId}" name="delete${a.authorId}" method="POST" action="authorController?action=delete">
                            <button name="delete" type="submit" value="${a.authorId}">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br />
        <form id="add" name="add" method="POST" action="authorController?action=form">
            <button name="id" type="submit" value="add">Add</button>
        </form>
    </body>
</html>
