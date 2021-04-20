
/*
    Changes the visibility of certain input fields based on the current command type.
*/
const changeCommandType = event => {
    let commandType = event.target.value;

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
    let commandTypeInput = document.querySelector("#command");
    commandTypeInput.addEventListener("change", changeCommandType);
}

window.addEventListener("load", generateInit);