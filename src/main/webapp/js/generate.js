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
    Attempts to save the currently generated command to the user's account.
*/
const saveCommand = () => {
    generateCommand();
    let object = {};
    let command = document.querySelector("#commandOutput").value;
    object.command = command;

    let initObject = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(object)
    };

    let promise = fetch("http://localhost:8080/jsongenerator/api/commands", initObject);
    promise.then(response => response.text()).then(data => console.log(data)).catch(error => console.log("Unable to save command"));
}

/*
    Initializes elements on the webpage to enable Minecraft command generation
    features.
*/
const generateInit = () => {
    updateCommandType();
    let commandTypeInput = document.querySelector("#command");
    commandTypeInput.addEventListener("change", updateCommandType);

    let addTextButton = document.querySelector("#addText");
    addTextButton.addEventListener("click", addTextElement);

    let addEntitySelectorButton = document.querySelector("#addSelector");
    addEntitySelectorButton.addEventListener("click", addSelectorElement);

    let addScoreButton = document.querySelector("#addScore");
    addScoreButton.addEventListener("click", addScoreElement);

    let addNbtButton = document.querySelector("#addNbt");
    addNbtButton.addEventListener("click", addNbtElement);

    let addKeybindButton = document.querySelector("#addKeybind");
    addKeybindButton.addEventListener("click", addKeybindElement);

    let addTranslationButton = document.querySelector("#addTranslation");
    addTranslationButton.addEventListener("click", addTranslateElement);

    let generateButton = document.querySelector("#generate");
    generateButton.addEventListener("click", generateCommand);

    let saveButton = document.querySelector("#save");
    saveButton.addEventListener("click", saveCommand);
}

window.addEventListener("load", generateInit);