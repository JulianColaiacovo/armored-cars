App.filter("BillNumberFormatter", function () {
    return function (billNumber) {
        var firstFourNumbers = ~~(billNumber / 100000000);
        var lastEightNumbers = billNumber % 100000000;

        return ('0000' + firstFourNumbers).slice(-4) + '-' + ('00000000' + lastEightNumbers).slice(-8);
    }
});