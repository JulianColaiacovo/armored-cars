App.filter("AmountFormatter", function ($filter) {
    return function (amount, currency) {
        amount = amount || 0;
        var currencyStr = '';
        if (currency) {
            currencyStr = $filter('CurrencyFormatter')(currency) + ' ';
        }
        var amountStr = amount.toLocaleString('es-AR', { minimumFractionDigits: 2 }).replace(/[.,]0*$/, "");
        return currencyStr + amountStr;
    }
});