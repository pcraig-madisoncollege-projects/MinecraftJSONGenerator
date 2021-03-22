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
        </main>

        <c:import url="includes/footer.jsp"></c:import>
    </div>
</body>

<c:import url="includes/scripts.jsp"></c:import>

</html>
