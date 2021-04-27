class TextElement extends Element {
    constructor(text, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._text = text;
    }

    get text() {
        return this._text;
    }

    set text(text) {
        this._text = text;
    }

    /*
        Converts the text element to a JSON representation for Minecraft
    */
    asJSON() {
        let root = super.asJSON();
        let object = {"text": this._text, ...root};
        return object;
    }
}