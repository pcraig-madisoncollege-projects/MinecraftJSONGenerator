class TextElement extends Element {
    constructor(text, color, bold) {
        super(color);
        this._text = text;
        this._bold = bold;
    }

    get text() {
        return this._text;
    }

    get bold() {
        return this._bold;
    }

    set text(text) {
        this._text = text;
    }

    set bold(bold) {
        this._bold = bold;
    }

    // Converts the text element to a JSON representation for Minecraft
    asJSON() {
        let object = {
            "text": this._text,
            "color": this._color,
            "bold": this._bold
        };

        return object;
    }
}