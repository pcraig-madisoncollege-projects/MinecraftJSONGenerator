<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="Login" scope="request" />
<c:import url="includes/head.jsp" />

<body>
<div class="container-fluid px-0">
    <c:import url="includes/header.jsp" />

    <main class="p-3">
        <h2>Login to an Account</h2>
        <p><span class="text-danger">*</span> Indicates required field</p>
        <p>Enter your login information below.</p>
        <form action="j_security_check" method="post">
            <div class="form-group">
                <label for="email"><span class="text-danger">*</span>Email Address</label>
                <input type="email" id="email" name="j_username" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password"><span class="text-danger">*</span>Password</label>
                <input type="password" id="password" name="j_password" class="form-control" required>
            </div>
            <input type="submit" value="Login" class="btn btn-primary">
            <input type="reset" value="Clear" class="btn btn-primary">
            <br>
            <div class="mt-2"><a href="#">Forgot your password?</a></div>
        </form>
    </main>

    <c:import url="includes/footer.jsp" />
</div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
