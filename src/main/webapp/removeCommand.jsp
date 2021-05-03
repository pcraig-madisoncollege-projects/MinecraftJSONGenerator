<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="JSON Generator" scope="request" />
<c:import url="includes/head.jsp"></c:import>

<body>
<div class="container-fluid px-0">
    <c:import url="includes/header.jsp"></c:import>

    <main class="p-3">
        <c:choose>
            <c:when test="${command != null}">
                <h2>Are you sure you want to delete ${command.name}?</h2>

                <p>Group: ${command.group}</p>

                <div class="form-group">
                    <textarea class="form-control" readonly>${command.value}</textarea>
                </div>

                <form action="delete" method="post">
                    <input type="submit" class="btn btn-primary" value="Delete Command">
                    <a href="commands" class="btn btn-primary">Cancel</a>
                    <input type="hidden" name="id" value="${command.id}">
                </form>

            </c:when>

            <c:otherwise>
                <h2>Unable to View Command</h2>
                <p>This command cannot be shown. If this is incorrect, you can
                    report a problem <a href="report">here</a>.</p>
            </c:otherwise>
        </c:choose>
    </main>

    <c:import url="includes/footer.jsp"></c:import>
</div>
</body>

<c:import url="includes/scripts.jsp"></c:import>

</html>
