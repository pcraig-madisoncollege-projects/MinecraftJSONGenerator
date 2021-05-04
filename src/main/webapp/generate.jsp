<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<c:set var="title" value="Generate a Command" scope="request" />
<c:set var="js" value="${['Element.js', 'TextElement.js', 'SelectorElement.js', 'ScoreElement.js', 'NbtElement.js', 'KeybindElement.js', 'TranslationElement.js', 'generate.js']}" scope="request" />
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
                    <button type="button" id="addText" class="btn btn-primary">Add Text</button>
                    <button type="button" id="addSelector" class="btn btn-primary">Add Entity Selector</button>
                    <button type="button" id="addScore" class="btn btn-primary">Add Score Value</button>
                    <button type="button" id="addNbt" class="btn btn-primary">Add NBT Value</button>
                    <button type="button" id="addKeybind" class="btn btn-primary">Add Keybind</button>
                    <button type="button" id="addTranslation" class="btn btn-primary">Add Translation</button>
                </div>
                <ul id="elements" class="list-unstyled"></ul>

                <input type="button" id="generate" value="Generate Command" class="btn btn-primary">
                <c:if test="${user != null}">
                    <input type="button" id="openSaveForm" value="Save Command" class="btn btn-primary">
                </c:if>
                <input type="button" id="openLoadForm" value="Load Command" class="btn btn-primary">

                <div class="form-group">
                    <label for="commandOutput">Generated Command</label>
                    <c:choose>
                        <c:when test="${command != null}">
                            <textarea id="commandOutput" readonly class="form-control">${command.value}</textarea>
                        </c:when>
                        <c:otherwise>
                            <textarea id="commandOutput" readonly class="form-control"></textarea>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </form>

        <form id="saveForm" class="popupDialog shadow" action="">
            <div class="d-flex bg-dark text-white p-3 rounded-top border-top border-left border-right">
                <h2>Save Command</h2>
                <button type="button" id="closeSaveForm" class="btn btn-danger ml-auto">X</button>
            </div>

            <div class="bg-light p-3 rounded-bottom border-left border-right border-bottom">
                <p>To save your command, enter the following information about your command below.</p>
                <p><span class="text-danger">*</span> Indicates required field</p>
                <div class="form-group">
                    <label for="saveName"><span class="text-danger">*</span>Command Name</label>
                    <input type="text" id="saveName" class="form-control" name="name" value="${command.name}" required>
                </div>

                <div class="form-group">
                    <label for="saveCommand">Command</label>
                    <textarea id="saveCommand" class="form-control" name="command" readonly></textarea>
                </div>

                <div class="form-group">
                    <label for="saveGroup">Command Group</label>
                    <select id="saveGroup" class="form-control" name="group">
                        <option value="None">None</option>
                        <option value="group.new" class="text-success">New Group</option>
                    </select>
                </div>

                <div class="form-group form-check">
                    <c:choose>
                        <c:when test="${command.shared}">
                            <input type="checkbox" id="savePrivate" class="form-check-input" name="shared" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" id="savePrivate" class="form-check-input" name="shared">
                        </c:otherwise>
                    </c:choose>
                    <label for="savePrivate" class="form-check-label">Make Command Public</label>
                </div>

                <button type="button" id="confirmSave" class="btn btn-primary">Save Command</button>
                <button type="button" id="cancelSaveForm" class="btn btn-danger">Cancel</button>
                <p id="saveFeedback" class="mt-3 mb-0"></p>
            </div>

        </form>

        <form id="loadForm" class="popupDialog shadow" action="">
            <div class="d-flex bg-dark text-white p-3 rounded-top border-top border-left border-right">
                <h2>Load Command</h2>
                <button type="button" id="closeLoadForm" class="btn btn-danger ml-auto">X</button>
            </div>

            <div class="bg-light p-3 rounded-bottom border-left border-right border-bottom">
                <p>To load a command, paste the command below then click the load button. <span class="text-danger">Note</span> that this will clear the command you are currently generating.</p>
                <p><span class="text-danger">*</span> Indicates required field</p>

                <div class="form-group">
                    <label for="loadCommand"><span class="text-danger">*</span>Paste Command Below</label>
                    <textarea id="loadCommand" class="form-control" name="command" required></textarea>
                </div>

                <button type="button" id="confirmLoad" class="btn btn-primary">Load Command</button>
                <button type="button" id="cancelLoadForm" class="btn btn-danger">Cancel</button>
                <p id="loadFeedback" class="mt-3 mb-0"></p>
            </div>

        </form>

    </main>

    <c:import url="includes/footer.jsp" />
</div>
</body>

<c:import url="includes/scripts.jsp" />

</html>
