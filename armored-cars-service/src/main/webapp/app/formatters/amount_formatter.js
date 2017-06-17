App.filter("AmountFormatter", function ($filter) {
    return function (amount, currency) {
        amount = amount | 0;
        return $filter('CurrencyFormatter')(currency) + ' ' + amount.toFixed(3).replace(/[.,]0*$/, "");
    }
});