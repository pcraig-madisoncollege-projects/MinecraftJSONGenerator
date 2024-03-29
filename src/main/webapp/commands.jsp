<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="JSON Generator" scope="request" />
<c:import url="includes/head.jsp" />

<body>
    <div class="container-fluid px-0">
        <c:import url="includes/header.jsp" />

        <main class="p-3">
            <h2>${user.nickname}'s Saved Commands</h2>
            <c:choose>
                <c:when test="${user.commands.size() > 0}">
                    <table id="dataTable" class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Group</th>
                                <th>Last Modified</th>
                                <th>Public</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="command" items="${user.commands}">
                                <tr>
                                    <td><a href="command?id=${command.id}">${command.name}</a></td>
                                    <td>${command.group}</td>
                                    <td>${command.dateModified}</td>
                                    <td>${command.shared}</td>
                                    <td><a href="edit?id=${command.id}">Edit</a></td>
                                    <td><a href="delete?id=${command.id}">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>

                <c:otherwise>
                    <p>You have not saved any commands yet.</p>
                </c:otherwise>
            </c:choose>
        </main>

        <c:import url="includes/footer.jsp" />
    </div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
