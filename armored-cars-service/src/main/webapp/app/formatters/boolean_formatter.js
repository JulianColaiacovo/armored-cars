App.filter("BooleanFormatter", function () {
    return function (boolean) {
        if (boolean) {
            return "Si";
        } else {
            return "No";
        }
    }
});