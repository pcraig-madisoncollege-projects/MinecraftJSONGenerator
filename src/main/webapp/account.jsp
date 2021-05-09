<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="${user.nickname}'s Account" scope="request" />
<c:import url="includes/head.jsp" />

<body>
    <div class="container-fluid px-0">
        <c:import url="includes/header.jsp" />

        <main class="p-3">
            <h2>${user.nickname}'s Account</h2>

            <c:if test="${user.message != null}">
                <p class="text-danger">${user.message}</p>
            </c:if>

            <form action="account" method="post">
                <div class="form-group">
                    <label for="nickname">Update Nickname</label>
                    <input type="text" id="nickname" class="form-control" name="nickname" value="${user.nickname}">
                </div>

                <div class="form-group">
                    <label for="minecraftProfile">Link Minecraft Profile</label>
                    <input type="text" id="minecraftProfile" class="form-control" name="minecraftProfile" value="${user.minecraftProfile}">
                </div>

                <p>Change Password</p>

                <div class="form-group">
                    <label for="oldPassword">Old Password</label>
                    <input type="password" id="oldPassword" class="form-control" name="oldPassword">
                </div>

                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" id="newPassword" class="form-control" name="newPassword">
                </div>

                <input type="submit" class="btn btn-primary" value="Update Account">

                <c:if test="${feedback != null && success != null}">
                    <c:choose>
                        <c:when test="${success}">
                            <p class="text-success">${feedback}</p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-danger">${feedback}</p>
                        </c:otherwise>

                    </c:choose>
                </c:if>

            </form>

        </main>

        <c:import url="includes/footer.jsp" />
    </div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
