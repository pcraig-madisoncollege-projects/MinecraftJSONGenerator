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
    Adds a JSON text element to the list of elements to generate.
*/
const addTextElement = () => {
    let listItem = document.createElement("li");
    listItem.setAttribute("data-id", uniqueElementId);

    let textInput = createTextInput("Text:", "elementText");
    listItem.appendChild(textInput);

    let colorInput = createSelectInput(Element.colors, "Color:", "elementColor");
    listItem.appendChild(colorInput);

    let lineBreak = document.createElement("br");
    listItem.appendChild(lineBreak);

    let boldCheckbox = createCheckbox("Bold", "elementBold");
    listItem.appendChild(boldCheckbox);

    let italicCheckbox = createCheckbox("Italic", "elementItalic");
    listItem.appendChild(italicCheckbox);

    let underlinedCheckbox = createCheckbox("Underlined", "elementUnderlined");
    listItem.appendChild(underlinedCheckbox);

    let strikethroughCheckbox = createCheckbox("Strikethrough", "elementStrikethrough");
    listItem.appendChild(strikethroughCheckbox);

    let obfuscatedCheckbox = createCheckbox("Obfuscated", "elementObfuscated");
    listItem.appendChild(obfuscatedCheckbox);

    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("data-delete", uniqueElementId);
    deleteButton.textContent = "Delete";
    deleteButton.addEventListener("click", deleteElement);
    listItem.appendChild(deleteButton);

    appendElement(listItem);
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
}

window.addEventListener("load", generateInit);