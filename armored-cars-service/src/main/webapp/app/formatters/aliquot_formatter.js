App.filter("AliquotFormatter", function () {
    return function (aliquot) {
        return aliquot + "%";
    }
});