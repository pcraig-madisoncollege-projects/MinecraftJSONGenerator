<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="nav flex-column flex-lg-row align-items-start align-items-lg-center bg-dark px-3 py-2">
        <a href="index.jsp" class="text-white text-decoration-none mr-3"><h1 class="mb-0">Minecraft JSON Generator</h1></a>
        <a href="generate.jsp" class="text-white text-decoration-none mx-lg-3 my-3 my-lg-0">Generate a Command</a>
        <a href="index.jsp#about" class="text-white text-decoration-none mx-lg-3">About</a>
        <c:choose>
            <c:when test="${pageContext.request.isUserInRole('user') || pageContext.request.isUserInRole('admin')}">
                <a href="#" class="btn btn-primary ml-lg-auto my-3 my-lg-0">My Commands</a>
                <a href="logout" class="btn btn-primary ml-lg-3">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="register" class="btn btn-primary ml-lg-auto my-3 my-lg-0">Sign Up</a>
                <a href="login" class="btn btn-primary ml-lg-3">Login</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
