class KeybindElement extends Element {
    constructor(keybind, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._keybind = keybind;
    }

    get keybind() {
        return this._keybind;
    }

    set keybind(keybind) {
        this._keybind = keybind;
    }

    /*
        Converts the keybind element to a JSON representation for Minecraft
    */
    asJSON() {
        let root = super.asJSON();
        let object = {"keybind": this._keybind, ...root};
        return object;
    }
}