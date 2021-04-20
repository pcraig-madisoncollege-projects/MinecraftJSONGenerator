
/*
    Changes the visibility of certain input fields based on the current command type.
*/
const updateCommandType = () => {
    let commandType = document.querySelector("#command").value;

    let commandData = document.querySelector("#hiddenCommandData");

    switch (commandType) {
        case "tellraw":
            commandData.hidden = false;
            break;
        default:
            commandData.hidden = true;
            break;
    }
}

/*
    Initializes elements on the webpage to enable Minecraft command generation features.
*/
const generateInit = () => {
    updateCommandType();
    let commandTypeInput = document.querySelector("#command");
    commandTypeInput.addEventListener("change", updateCommandType);
}

window.addEventListener("load", generateInit);