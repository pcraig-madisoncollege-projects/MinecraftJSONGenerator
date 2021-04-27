class Element {
    // TODO: implement custom color selection
    static colors = [
        "white", "black", "dark blue", "dark green", "dark aqua", "dark red",
        "dark purple", "gold", "gray", "dark gray", "blue", "green", "aqua",
        "red", "light purple", "yellow", "reset"
    ];

    // TODO: consider options for supporting hover and click events due to large variability per option
    static hoverEvents = [
        "none", "show text", "show item", "show entity"
    ];
    static clickEvents = [
        "none", "open url", "open file", "run command", "suggest command", "change page", "copy to clipboard"
    ];

    constructor(color) {
        this._color = color;
    }

    get color() {
        return this._color;
    }

    set color(color) {
        this._color = color;
    }

    // Converts the generic element to a JSON representation for Minecraft
    asJSON() {
        return {};
    }
}