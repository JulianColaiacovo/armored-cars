App.filter("CurrencyFormatter", function () {
    return function (currency) {
        if (currency === "ARS") {
            return "$";
        } else if (currency === "USD") {
            return "US$";
        }
        return currency;
    }
});