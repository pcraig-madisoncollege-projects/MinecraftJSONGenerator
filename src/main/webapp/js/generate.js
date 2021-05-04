let uniqueElementId = 0;

/*
    Changes the visibility of certain input fields based on the current command
    type.
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
    Creates a text input element with the provided id prefix and an
    auto-generated label with the provided label text. The element that gets
    returned is a span element containing the label and input.
*/
const createTextInput = (labelText, prefix) => {
    let span = document.createElement("span");

    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("id", prefix + uniqueElementId);

    let label = document.createElement("label");
    label.setAttribute("for", prefix + uniqueElementId);
    label.textContent = labelText;

    span.appendChild(label);
    span.appendChild(input);
    return span;
}

/*
    Creates a select input element containing the provided string options array
    as well as the provided id prefix. The label is generated with the provided
    label text. The element that gets returned is a span element containing the
    label and input.
*/
const createSelectInput = (options, labelText, prefix) => {
    let span = document.createElement("span");

    let input = document.createElement("select");
    input.setAttribute("id", prefix + uniqueElementId);

    options.forEach(option => {
        let choice = document.createElement("option");
        choice.setAttribute("value", option.replaceAll(" ", "_"));
        choice.textContent = option;
        input.appendChild(choice);
    });

    let label = document.createElement("label");
    label.setAttribute("for", prefix + uniqueElementId);
    label.textContent = labelText;

    span.appendChild(label);
    span.appendChild(input);
    return span;
}

/*
    Creates a checkbox input element with the provided id prefix. The label is
    generated with the provided label text. The element that gets returned is
    a span element containing the label and input.
*/
const createCheckbox = (labelText, prefix) => {
    let span = document.createElement("span");

    let input = document.createElement("input");
    input.setAttribute("type", "checkbox");
    input.setAttribute("id", prefix + uniqueElementId);

    let label = document.createElement("label");
    label.setAttribute("for", prefix + uniqueElementId);
    label.textContent = labelText;

    span.appendChild(input);
    span.appendChild(label);
    return span;
}

/*
    Appends the provided HTML list item element to the JSON tellraw elements.
*/
const appendElement = element => {
    let elementsList = document.querySelector("#elements");
    elementsList.appendChild(element);
    uniqueElementId++;
}

/*
    Deletes the parent element of the event target.
*/
const deleteElement = event => {
    let elementsList = document.querySelector("#elements");
    let parent = event.target.parentElement;

    elementsList.removeChild(parent);
}

/*
    Generates the generic styling inputs for every JSON element type. These
    inputs consist of color, bold, italics, underline, strikethrough, and
    obfuscation. The input elements are all contained within a span element.
*/
const createStylingInputs = () => {
    let span = document.createElement("span");

    let colorInput = createSelectInput(Element.colors, "Color:", "elementColor");
    span.appendChild(colorInput);

    let lineBreak = document.createElement("br");
    span.appendChild(lineBreak);

    let boldCheckbox = createCheckbox("Bold", "elementBold");
    span.appendChild(boldCheckbox);

    let italicCheckbox = createCheckbox("Italic", "elementItalic");
    span.appendChild(italicCheckbox);

    let underlinedCheckbox = createCheckbox("Underlined", "elementUnderlined");
    span.appendChild(underlinedCheckbox);

    let strikethroughCheckbox = createCheckbox("Strikethrough", "elementStrikethrough");
    span.appendChild(strikethroughCheckbox);

    let obfuscatedCheckbox = createCheckbox("Obfuscated", "elementObfuscated");
    span.appendChild(obfuscatedCheckbox);

    return span;
}

/*
    Creates a unique delete button for the current unique element id.
*/
const createDeleteButton = () => {
    let button = document.createElement("button");

    button.setAttribute("type", "button");
    button.setAttribute("data-delete", uniqueElementId);
    button.textContent = "Delete";
    button.addEventListener("click", deleteElement);

    return button;
}

/*
    Adds a JSON text element to the list of elements to generate.
*/
const addTextElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "text");

    let textInput = createTextInput("Text:", "elementText");
    listItem.appendChild(textInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Adds a JSON entity selector element to the list of elements to generate.
*/
const addSelectorElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "selector");

    let selectorInput = createSelectInput(Element.selectors, "Entity Selector:", "elementSelector");
    listItem.appendChild(selectorInput);

    let selectorTagsInput = createTextInput("Selector Tags:", "elementSelectorTags");
    listItem.appendChild(selectorTagsInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Adds a JSON score element to the list of elements to generate.
*/
const addScoreElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "score");

    let selectorInput = createSelectInput(Element.selectors, "Entity Selector:", "elementSelector");
    listItem.appendChild(selectorInput);

    let selectorTagsInput = createTextInput("Selector Tags:", "elementSelectorTags");
    listItem.appendChild(selectorTagsInput);

    let objectiveInput = createTextInput("Scoreboard Objective:", "elementObjective");
    listItem.appendChild(objectiveInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Adds a JSON score element to the list of elements to generate.
*/
const addNbtElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "nbt");

    let nbtTypeInput = createSelectInput(NbtElement.types, "NBT Type:", "elementNbtType");
    listItem.appendChild(nbtTypeInput);

    let selectorInput = createTextInput("Target:", "elementSelector");
    listItem.appendChild(selectorInput);

    let selectorTagsInput = createTextInput("Target Tags:", "elementSelectorTags");
    listItem.appendChild(selectorTagsInput);

    let nbtInput = createTextInput("NBT:", "elementNbt");
    listItem.appendChild(nbtInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Adds a JSON keybind element to the list of elements to generate.
*/
const addKeybindElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "keybind");

    let keybindInput = createTextInput("Keybind:", "elementKeybind");
    listItem.appendChild(keybindInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Adds a JSON translate element to the list of elements to generate.
*/
const addTranslateElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);
    listItem.setAttribute("data-type", "translate");

    let translateInput = createTextInput("Translation Key:", "elementTranslate");
    listItem.appendChild(translateInput);

    let styleInputs = createStylingInputs();
    listItem.appendChild(styleInputs);

    let deleteButton = createDeleteButton();
    listItem.appendChild(deleteButton);

    appendElement(listItem);

    return listItem;
}

/*
    Generates a command based on the type of command and the JSON elements provided.
*/
const generateCommand = () => {
    let commandOutput = document.querySelector("#commandOutput");
    let commandType = document.querySelector("#command").value;
    let targetSelector = document.querySelector("#targetSelector").value;
    let targetSelectorTags = document.querySelector("#targetSelectorTags").value;
    let elements = [...document.querySelector("#elements").childNodes];

    targetSelectorTags = Element.cleanSelectorTags(targetSelectorTags);

    let command = `/${commandType} ${targetSelector}${targetSelectorTags} `;

    let components = [];

    // Retrieve each JSON element from the document and format using Minecraft JSON format
    elements.forEach(element => {
        let component;
        let id = element.getAttribute("data-id");
        let type = element.getAttribute("data-type");

        // Retrieve generic data for a JSON element
        let color = document.querySelector(`#${"elementColor" + id}`).value;
        let bold = document.querySelector(`#${"elementBold" + id}`).checked;
        let italic = document.querySelector(`#${"elementItalic" + id}`).checked;
        let underlined = document.querySelector(`#${"elementUnderlined" + id}`).checked;
        let strikethrough = document.querySelector(`#${"elementStrikethrough" + id}`).checked;
        let obfuscated = document.querySelector(`#${"elementObfuscated" + id}`).checked;

        // Retrieve text element data
        switch (type) {
            case "text":
                let text = document.querySelector(`#${"elementText" + id}`).value;
                component = new TextElement(text, color, bold, italic, underlined, strikethrough, obfuscated);
                break;
            case "selector":
                let entitySelector = document.querySelector(`#${"elementSelector" + id}`).value;
                let entitySelectorTags = document.querySelector(`#${"elementSelectorTags" + id}`).value;

                component = new SelectorElement(entitySelector, entitySelectorTags, color,
                    bold, italic, underlined, strikethrough, obfuscated);
                break;
            case "score":
                let scoreSelector = document.querySelector(`#${"elementSelector" + id}`).value;
                let scoreSelectorTags = document.querySelector(`#${"elementSelectorTags" + id}`).value;
                let objective = document.querySelector(`#${"elementObjective" + id}`).value;

                component = new ScoreElement(scoreSelector, scoreSelectorTags,
                    objective, color, bold, italic, underlined, strikethrough,
                    obfuscated);
                break;
            case "nbt":
                let nbtType = document.querySelector(`#${"elementNbtType" + id}`).value;
                let nbt = document.querySelector(`#${"elementNbt" + id}`).value;
                let nbtTarget = document.querySelector(`#${"elementSelector" + id}`).value;
                let nbtTargetTags = document.querySelector(`#${"elementSelectorTags" + id}`).value;

                component = new NbtElement(nbtType, nbt, nbtTarget,
                    nbtTargetTags, color, bold, italic, underlined,
                    strikethrough, obfuscated);
                break;
            case "keybind":
                let keybind = document.querySelector(`#${"elementKeybind" + id}`).value;

                component = new KeybindElement(keybind, color, bold, italic,
                    underlined, strikethrough, obfuscated);
                break;
            case "translate":
                let translate = document.querySelector(`#${"elementTranslate" + id}`).value;

                // TODO: Add support for "with" used in translate JSON element
                component = new TranslationElement(translate, undefined, color, bold, italic,
                    underlined, strikethrough, obfuscated);
                break;
            default:
                break;
        }

        if (component) {
            components.push(component.asJSON());
        }
    });

    let data = JSON.stringify(components);

    // Append data to command based on type of command being generated
    switch (commandType) {
        case "tellraw":
        default:
            command += data;
            break;
    }

    commandOutput.textContent = command;
}

/*
    Opens the save confirmation form dialog.
*/
const openSaveForm = () => {
    generateCommand();
    let saveForm = document.querySelector("#saveForm");
    let saveCommandInput = document.querySelector("#saveCommand");
    let feedback = document.querySelector("#saveFeedback");
    let command = document.querySelector("#commandOutput").value;

    saveCommandInput.textContent = command;
    feedback.textContent = "";

    saveForm.style.display = "block";
}

/*
    Closes the save confirmation form dialog.
*/
const closeSaveForm = () => {
    let saveForm = document.querySelector("#saveForm");
    saveForm.style.display = "none";
}

/*
    Attempts to save the generated command to the user's account.
*/
const confirmSave = () => {
    let url = window.location.origin + "/jsongenerator/api/commands";
    let form = document.querySelector("#saveForm");
    let feedback = document.querySelector("#saveFeedback");
    let name = form.name.value;
    let command = form.command.value;
    let group = form.group.value;
    let shared = form.shared.checked;

    let object = {
        "name": name,
        "command": command,
        "group": group,
        "shared": shared
    };

    // Update saved command using command id
    if (form.commandId) object.id = form.commandId.value;

    let initObject = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(object)
    };

    let promise = fetch(url, initObject);
    feedback.textContent = "Saving command. Please wait...";
    feedback.classList.add("text-warning");

    promise.then(response => response.text()).then(data => {
        if (data != "{}") {
            feedback.textContent = "Successfully saved the command!";
            feedback.classList.replace("text-warning", "text-success");
        } else {
            feedback.textContent = "Failed to save the command!";
            feedback.classList.replace("text-warning", "text-danger");
        }
    }).catch(error => {
        feedback.textContent = "Failed to save the command!";
        feedback.classList.replace("text-warning", "text-danger");
    });
}

/*
    Opens the load confirmation form dialog.
*/
const openLoadForm = () => {
    let loadForm = document.querySelector("#loadForm");
    let feedback = document.querySelector("#loadFeedback");

    feedback.textContent = "";
    loadForm.style.display = "block";
}

/*
    Closes the load confirmation form dialog.
*/
const closeLoadForm = () => {
    let loadForm = document.querySelector("#loadForm");
    loadForm.style.display = "none";
}

/*
    Splits the provided selector data into its target and tag components.
*/
const splitSelector = selector => {
    let parts = [];

    let target = selector;
    let tags = "";
    let tagIndex = selector.indexOf("[");
    // Verify that selector tags exist
    if (tagIndex > 0) {
        let selectorParts = selector.split("[");
        target = selectorParts[0];

        // Retrieve remaining selector parts
        for (let index = 1; index < selectorParts.length; index++) {
            let part = selectorParts[index];
            tags += "[" + part;
        }
    }

    parts.push(target);
    parts.push(tags);

    return parts;
}

/*
    Attempts to load the provided command as a JSON-containing command and creates the proper JSON elements on the page.
*/
const loadCommand = initialCommand => {
    let success = false;

    // Empty the elements list
    let elementsList = document.querySelector("#elements");
    elementsList.textContent = "";

    // Replace the starting forward slash if necessary
    let command = initialCommand.replaceAll(/^\//g, "");

    // Break command into managable pieces
    let parts = command.split(" ");

    if (parts.length >= 3) {
        let commandType = parts[0];

        // Load selector data
        let selector = "";
        let lastIndex = 1;
        for (let index = 1; index < parts.length; index++) {
            let part = parts[index];
            selector += " " + part;
            lastIndex++;

            // Exit when closing bracket is provided
            if (part.endsWith("]") || (index === 1 && !part.endsWith("]"))) {
                break;
            }
        }
        selector = selector.trim();
        let selectorParts = splitSelector(selector);
        let target = selectorParts[0];
        let tags = selectorParts[1];

        // Verify that the selector tags could be loaded correctly
        if (lastIndex < parts.length) {

            // Load JSON data
            let data = "";
            for (let index = lastIndex; index < parts.length; index++) {
                data += " " + parts[index];
            }
            data = data.trim();

            // Attempt to load JSON elements from data
            let elements = [];
            try {
                elements = JSON.parse(data);
            } catch {
                return false;
            }

            // Fill generator page with data
            let commandInput = document.querySelector("#command");
            let targetInput = document.querySelector("#targetSelector");
            let targetTagsInput = document.querySelector("#targetSelectorTags");

            commandInput.value = commandType;
            updateCommandType();
            targetInput.value = target;
            targetTagsInput.value = tags;
            elements.forEach(element => {
                let node;
                let nodeId = 0;

                // Load specific data based on type of JSON data
                if (element.text) {
                    node = addTextElement();
                    nodeId = node.getAttribute("data-id");
                    document.querySelector(`#elementText${nodeId}`).value = element.text;
                } else if (element.selector) {
                    node = addSelectorElement();
                    nodeId = node.getAttribute("data-id");
                    let selectorParts = splitSelector(element.selector);
                    let target = selectorParts[0];
                    let tags = selectorParts[1];
                    document.querySelector(`#elementSelector${nodeId}`).value = target;
                    document.querySelector(`#elementSelectorTags${nodeId}`).value = tags;
                } else if (element.score) {
                    node = addScoreElement();
                    nodeId = node.getAttribute("data-id");
                    let selectorParts = splitSelector(element.score.name);
                    let target = selectorParts[0];
                    let tags = selectorParts[1];
                    let objective = element.score.objective;
                    document.querySelector(`#elementSelector${nodeId}`).value = target;
                    document.querySelector(`#elementSelectorTags${nodeId}`).value = tags;
                    document.querySelector(`#elementObjective${nodeId}`).value = objective;
                } else if (element.entity || element.block || element.container) {
                    node = addNbtElement();
                    nodeId = node.getAttribute("data-id");
                    let type;
                    let target = "";
                    let tags = "";
                    let nbt = element.nbt;

                    // Load specific data
                    if (element.entity) {
                        type = "entity";
                        let selectorParts = splitSelector(element.entity);
                        target = selectorParts[0];
                        tags = selectorParts[1];
                    } else if (element.container) {
                        type = "container";
                        target = element.container;
                    } else {
                        type = "block";
                        target = element.block;
                    }

                    document.querySelector(`#elementNbtType${nodeId}`).value = type;
                    document.querySelector(`#elementSelector${nodeId}`).value = target;
                    document.querySelector(`#elementSelectorTags${nodeId}`).value = tags;
                    document.querySelector(`#elementNbt${nodeId}`).value = nbt;
                } else if (element.keybind) {
                    node = addKeybindElement();
                    nodeId = node.getAttribute("data-id");
                    let keybind = element.keybind;
                    document.querySelector(`#elementKeybind${nodeId}`).value = keybind;
                } else if (element.translate) {
                    node = addTranslateElement();
                    nodeId = node.getAttribute("data-id");
                    let translate = element.translate;
                    document.querySelector(`#elementTranslate${nodeId}`).value = translate;
                } else {
                    node = addTextElement();
                    nodeId = node.getAttribute("data-id");
                    document.querySelector(`#elementText${nodeId}`).value = element;
                }

                // Apply generic styling values if applicable
                if (typeof(element) === "object") {
                    if (element.color) {
                        document.querySelector(`#elementColor${nodeId}`).value = element.color;
                    }
                    if (element.bold) {
                        document.querySelector(`#elementBold${nodeId}`).checked = element.bold;
                    }
                    if (element.italic) {
                        document.querySelector(`#elementItalic${nodeId}`).checked = element.italic;
                    }
                    if (element.underlined) {
                        document.querySelector(`#elementUnderlined${nodeId}`).checked = element.underlined;
                    }
                    if (element.strikethrough) {
                        document.querySelector(`#elementStrikethrough${nodeId}`).checked = element.strikethrough;
                    }
                    if (element.obfuscated) {
                        document.querySelector(`#elementObfuscated${nodeId}`).checked = element.obfuscated;
                    }
                }
            });

            success = true;
        }
    }
    return success;
}

/*
    Attempts to load the entered command, if possible.
*/
const confirmLoad = () => {
    let loadForm = document.querySelector("#loadForm");
    let feedback = document.querySelector("#loadFeedback");
    feedback.classList.remove("text-danger");
    feedback.textContent = "";

    let command = loadForm.command.value.trim();

    if (command != "") {
        let success = loadCommand(command);
        if (!success) {
            feedback.classList.add("text-danger");
            feedback.textContent = "Failed to load the command. Be sure you are providing a valid command.";
        } else {
            closeLoadForm();
        }
    } else {
        feedback.classList.add("text-danger");
        feedback.textContent = "You must enter a valid command in order to load.";
    }
}

/*
    Initializes elements on the webpage to enable Minecraft command generation
    features.
*/
const generateInit = () => {
    updateCommandType();
    let commandTypeInput = document.querySelector("#command");
    if (commandTypeInput) commandTypeInput.addEventListener("change", updateCommandType);

    let addTextButton = document.querySelector("#addText");
    if (addTextButton) addTextButton.addEventListener("click", addTextElement);

    let addEntitySelectorButton = document.querySelector("#addSelector");
    if (addEntitySelectorButton) addEntitySelectorButton.addEventListener("click", addSelectorElement);

    let addScoreButton = document.querySelector("#addScore");
    if (addScoreButton) addScoreButton.addEventListener("click", addScoreElement);

    let addNbtButton = document.querySelector("#addNbt");
    if (addNbtButton) addNbtButton.addEventListener("click", addNbtElement);

    let addKeybindButton = document.querySelector("#addKeybind");
    if (addKeybindButton) addKeybindButton.addEventListener("click", addKeybindElement);

    let addTranslationButton = document.querySelector("#addTranslation");
    if (addTranslationButton) addTranslationButton.addEventListener("click", addTranslateElement);

    let generateButton = document.querySelector("#generate");
    if (generateButton) generateButton.addEventListener("click", generateCommand);

    // Save Dialog
    let openSaveButton = document.querySelector("#openSaveForm");
    if (openSaveButton) openSaveButton.addEventListener("click", openSaveForm);
    let cancelSaveButton = document.querySelector("#cancelSaveForm");
    if (cancelSaveButton) cancelSaveButton.addEventListener("click", closeSaveForm);
    let closeSaveButton = document.querySelector("#closeSaveForm");
    if (closeSaveButton) closeSaveButton.addEventListener("click", closeSaveForm);
    let saveButton = document.querySelector("#confirmSave");
    if (saveButton) saveButton.addEventListener("click", confirmSave);

    // Load Dialog
    let openLoadButton = document.querySelector("#openLoadForm");
    if (openLoadButton) openLoadButton.addEventListener("click", openLoadForm);
    let cancelLoadButton = document.querySelector("#cancelLoadForm");
    if (cancelLoadButton) cancelLoadButton.addEventListener("click", closeLoadForm);
    let closeLoadButton = document.querySelector("#closeLoadForm");
    if (closeLoadButton) closeLoadButton.addEventListener("click", closeLoadForm);
    let loadButton = document.querySelector("#confirmLoad");
    if (loadButton) loadButton.addEventListener("click", confirmLoad);

    // Auto-load command if generated command exists in text area
    let commandOutput = document.querySelector("#commandOutput");
    if (commandOutput && commandOutput.value !== "") loadCommand(commandOutput.value);
}

window.addEventListener("load", generateInit);