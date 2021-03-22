<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="JSON Generator" scope="request" />
<c:import url="includes/head.jsp"></c:import>

<body>
    <div class="container-fluid px-0">
        <c:import url="includes/header.jsp"></c:import>

        <main class="p-3">
            <h2>${user.nickname}'s Saved Commands</h2>
            <c:choose>
                <c:when test="${user.commands.size() > 0}">
                    <table class="table datatable">
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
                                    <td>${command.name}</td>
                                    <td>Empty</td>
                                    <td>Empty</td>
                                    <td>${command.shared}</td>
                                    <td>Edit</td>
                                    <td>Delete</td>
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

        <c:import url="includes/footer.jsp"></c:import>
    </div>
</body>

<c:import url="includes/scripts.jsp"></c:import>

</html>
