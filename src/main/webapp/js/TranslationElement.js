class TranslationElement extends Element {
    constructor(translate, components, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._translate = translate;
        this._components = components;
    }

    get components() {
        return this._components;
    }

    get translate() {
        return this._translate;
    }

    set translate(translate) {
        this._translate = translate;
    }

    set components(components) {
        this._components = components;
    }

    /*
        Converts the keybind element to a JSON representation for Minecraft
    */
    asJSON() {
        let root = super.asJSON();
        let object = {"translate": this._translate, ...root};

        // TODO: Implement component into JSON output

        return object;
    }
}