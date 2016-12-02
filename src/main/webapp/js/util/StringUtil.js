function isEmpty(input) {
    if (isUndefined(input)) {
        return true;
    }
    if (input == null) {
        return true;
    }
    if (input.toString() == "") {
        return true;
    }
    return false;
}

function isUndefined(input) {
    return typeof(input) == "undefined";
}