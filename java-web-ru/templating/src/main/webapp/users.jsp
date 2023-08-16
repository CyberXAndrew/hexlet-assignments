<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
         integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>id</td>
                    <td>users name</td>
                    <td>email</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.get("id")}</td>
                        <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}
                        </a></td>
                        <td>${user.get("email")}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
<!-- END -->
