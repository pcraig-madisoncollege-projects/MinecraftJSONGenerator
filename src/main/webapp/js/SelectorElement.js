class SelectorElement extends Element {
    constructor(selector, tags, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._selector = selector;
        this._tags = tags;
    }

    // GETTERS

    get selector() {
        return this._selector;
    }

    get tags() {
        return this._tags;
    }

    // SETTERS

    set selector(selector) {
        this._selector = selector;
    }

    set tags(tags) {
        this._tags = tags;
    }

    /*
        Converts the selector element to a JSON representation for Minecraft
    */
    asJSON() {
        let root = super.asJSON();
        let object = {
            "selector": this._selector + this._tags, ...root
        };
        return object;
    }
}