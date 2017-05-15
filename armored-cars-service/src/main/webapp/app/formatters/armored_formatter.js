App.filter("ArmoredFormatter", function () {
    return function (armored) {
        if (armored) {
            return armored.code + ' ' + armored.car.brand;
        }
        return "";
    }
});