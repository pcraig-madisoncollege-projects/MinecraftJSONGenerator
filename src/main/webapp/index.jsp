<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="JSON Generator" scope="request" />
<c:import url="includes/head.jsp" />

<body>
    <div class="container-fluid px-0">
        <c:import url="includes/header.jsp" />

        <main class="p-3">
            <c:if test="${user != null}">
                <p class="text-success">You are signed in as ${user.nickname}.</p>
            </c:if>
            <section id="about">
                <h2>About the JSON Generator</h2>
                <p>This service makes creating JSON components for Minecraft commands easy! With a versatile and
                    easy-to-learn interface, you no longer need to memorize the JSON components to write complex commands.</p>
                <p>Another great feature about the JSON generator is the ability for users to save their commands online.
                    No longer are you required to save the command locally and risk losing your work.</p>
                <p>On top of that, the command generator also supports loading commands from an already generated command.
                    Gone are the days of re-generating a command as a result of losing a save code.</p>
            </section>

            <section id="donate">
                <h2>Donate to the Project</h2>
                <p>By supporting the project, you are helping to continue the development and stability for all users.
                    As commands and JSON components get to be more and more complex in Minecraft, the goal for this
                    project is to adapt and develop.</p>
                <p>Any donations are much appreciated!</p>
            </section>

            <section id="contact">
                <h2>Contact and Support</h2>
                <p>Do you need help with something? Feel free to send us an email at support@example.com.</p>
                <p>Have something to report? You can report it <a href="#">here</a>.</p>
            </section>
        </main>

        <c:import url="includes/footer.jsp" />
    </div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
