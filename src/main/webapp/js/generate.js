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
    Generates a command based on the type of command and the JSON elements provided.
*/
const generateCommand = () => {
    let commandOutput = document.querySelector("#commandOutput");
    let commandType = document.querySelector("#command").value;
    let targetSelector = document.querySelector("#targetSelector").value;
    let targetSelectorTags = document.querySelector("#targetSelectorTags").value.trim();
    let elements = [...document.querySelector("#elements").childNodes];

    targetSelectorTags = Element.cleanSelectorTags(targetSelectorTags);

    let command = `/${commandType} ${targetSelector}${targetSelectorTags} `;

    console.log(`Generating ${commandType} command using elements:`);
    console.log(elements);
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
        if (type == "text") {
            let text = document.querySelector(`#${"elementText" + id}`).value;
            component = new TextElement(text, color, bold, italic, underlined, strikethrough, obfuscated);
        } else if (type == "selector") {
            let selector = document.querySelector(`#${"elementSelector" + id}`).value;
            let selectorTags = document.querySelector(`#${"elementSelectorTags" + id}`).value;
            component = new SelectorElement(selector, selectorTags, color, bold, italic, underlined, strikethrough, obfuscated);
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

    let generateButton = document.querySelector("#generate");
    generateButton.addEventListener("click", generateCommand);
}

window.addEventListener("load", generateInit);