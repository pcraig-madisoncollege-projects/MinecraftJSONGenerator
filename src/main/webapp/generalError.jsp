<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="JSON Generator" scope="request" />
<c:import url="includes/head.jsp" />

<body>
<div class="container-fluid px-0">
    <c:import url="includes/header.jsp" />

    <main class="p-3">
        <h2>Error</h2>
        <p>Failed to load the requested page. If you feel this is an issue, please report it <a href="report">here</a>.</p>
    </main>

    <c:import url="includes/footer.jsp" />
</div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
