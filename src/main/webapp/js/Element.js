class Element {
    // TODO: implement custom color selection
    static colors = [
        "white", "black", "dark blue", "dark green", "dark aqua", "dark red",
        "dark purple", "gold", "gray", "dark gray", "blue", "green", "aqua",
        "red", "light purple", "yellow", "reset"
    ];

    static selectors = [
        "@a", "@p", "@r", "@s"
    ];

    // TODO: consider options for supporting hover and click events due to large variability per option
    static hoverEvents = [
        "none", "show text", "show item", "show entity"
    ];
    static clickEvents = [
        "none", "open url", "open file", "run command", "suggest command", "change page", "copy to clipboard"
    ];

    constructor(color, bold, italic, underlined, strikethrough, obfuscated) {
        this._color = color;
        this._bold = bold;
        this._italic = italic;
        this._underlined = underlined;
        this._strikethrough = strikethrough;
        this._obfuscated = obfuscated;
    }

    // GETTERS

    get color() {
        return this._color;
    }

    get bold() {
        return this._bold;
    }

    get italic() {
        return this._italic;
    }

    get underlined() {
        return this._underlined;
    }

    get strikethrough() {
        return this._strikethrough;
    }

    get obfuscated() {
        return this._obfuscated;
    }

    // SETTERS

    set color(color) {
        this._color = color;
    }

    set bold(bold) {
        this._bold = bold;
    }

    set italic(italic) {
        this._italic = italic;
    }

    set underlined(underlined) {
        this._underlined = underlined;
    }

    set strikethrough(strikethrough) {
        this._strikethrough = strikethrough;
    }

    set obfuscated(obfuscated) {
        this.obfuscated = obfuscated;
    }

    /*
        Generates square brackets around target selector tags if they are missing.
    */
    static cleanSelectorTags(tags) {
        tags = tags.trim();
        if (tags != "") {
            if (!tags.startsWith("[")) {
                tags = "[" + tags;
            }
            if (!tags.endsWith("]")) {
                tags += "]";
            }
        }

        return tags;
    }

    /*
        Converts the generic element to a JSON representation for Minecraft
    */
    asJSON() {
        let object = {
            "color": this._color,
            "bold": this._bold,
            "italic": this._italic,
            "underlined": this._underlined,
            "strikethrough": this._strikethrough,
            "obfuscated": this._obfuscated
        };
        return object;
    }
}