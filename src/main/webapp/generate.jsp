<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="Generate a Command" scope="request" />
<c:set var="js" value="${['Element.js', 'TextElement.js', 'generate.js']}" scope="request" />
<c:import url="includes/head.jsp" />

<body>
<div class="container-fluid px-0">
    <c:import url="includes/header.jsp" />

    <main class="p-3">
        <h2>Generate a Command</h2>
        <p>To generate a command, select the type of command you want to create from the dropdown menu below. Once you
            have done that, you can enter any additional data about the command or the information you want to display.</p>
        <form action="#" method="post">
            <div class="form-group">
                <label for="command">Command</label>
                <select name="command" id="command" class="form-control">
                    <option value="" selected>None</option>
                    <option value="tellraw">Tellraw</option>
                </select>
            </div>
            <div id="hiddenCommandData" hidden>
                <div class="form-group">
                    <label for="targetSelector">Target Selector</label>
                    <select name="targetSelector" id="targetSelector" class="form-control">
                        <option value="@a">All Players (@a)</option>
                        <option value="@p">Nearest Player (@p)</option>
                        <option value="@r">Random Player (@r)</option>
                        <option value="@e">Entity(s) (@e)</option>
                        <option value="@s">Self (@s)</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="targetSelectorTags">Tags</label>
                    <input type="text" id="targetSelectorTags" class="form-control">
                </div>
                <div id="elementButtons">
                    <button id="addText" class="btn btn-primary">Add Text</button>
                    <button id="addSelector" class="btn btn-primary">Add Entity Selector</button>
                    <button id="addScore" class="btn btn-primary">Add Score Value</button>
                    <button id="addNbt" class="btn btn-primary">Add NBT Value</button>
                    <button id="addKeybind" class="btn btn-primary">Add Keybind</button>
                    <button id="addTranslation" class="btn btn-primary">Add Translation</button>
                </div>
                <ul id="elements" class="list-unstyled">
                    <li>JSON Elements Go Here</li>
                </ul>

                <input type="submit" value="Generate Command" class="btn btn-primary">
                <input type="button" value="Save Command" class="btn btn-primary">
                <input type="button" value="Load Command" class="btn btn-primary">

                <div class="form-group">
                    <label for="commandOutput">Command</label>
                    <textarea id="commandOutput" cols="30" rows="10" readonly class="form-control">Generated Command Goes Here</textarea>
                </div>
            </div>

        </form>

    </main>

    <c:import url="includes/footer.jsp" />
</div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
