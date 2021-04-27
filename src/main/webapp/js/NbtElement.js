class NbtElement extends Element {
    static types = ["block", "entity", "storage"];

    constructor(type, nbt, selector, tags, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._type = type;
        this._nbt = nbt;
        this._selector = selector;
        this._tags = Element.cleanSelectorTags(tags);
    }

    // GETTERS

    get type() {
        return this._type;
    }

    get nbt() {
        return this._nbt;
    }

    get selector() {
        return this._selector;
    }

    get tags() {
        return this._tags;
    }

    // SETTERS

    set type(type) {
        this._type = type;
    }

    set nbt(nbt) {
        this._nbt = nbt;
    }

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

        let initialObject = {};

        // Set selector based on type of nbt data
        initialObject[this._type] = this._selector
        if (this._type == "entity") {
            initialObject[this._type] = this._selector + this._tags;
        }

        initialObject["nbt"] = this._nbt;

        let object = {...initialObject, ...root};

        return object;
    }
}