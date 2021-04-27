class TextElement extends Element {
    constructor() {
        super();
        this._text = "";
    }

    get text() {
        return this._text;
    }

    set text(text) {
        this._text = text;
    }

    // Converts the text element to a JSON representation for Minecraft
    asJSON() {
        let object = {
            "text": this._text,
            "color": this._color
        };

        return object;
    }
}