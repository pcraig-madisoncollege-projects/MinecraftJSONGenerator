class ScoreElement extends Element {
    constructor(selector, tags, objective, color, bold, italic, underlined, strikethrough, obfuscated) {
        super(color, bold, italic, underlined, strikethrough, obfuscated);
        this._selector = selector;
        this._tags = Element.cleanSelectorTags(tags);
        this._objective = objective;
    }

    // GETTERS

    get selector() {
        return this._selector;
    }

    get tags() {
        return this._tags;
    }

    get objective() {
        return this._objective;
    }

    // SETTERS

    set selector(selector) {
        this._selector = selector;
    }

    set tags(tags) {
        this._tags = tags;
    }

    set objective(objective) {
        this._objective = objective;
    }

    /*
        Converts the scoreboard element to a JSON representation for Minecraft
    */
    asJSON() {
        let root = super.asJSON();
        let scoreObject = {
            "name": this._selector + this._tags,
            "objective": this._objective
        };

        let object = {"score": scoreObject, ...root};
        return object;
    }
}