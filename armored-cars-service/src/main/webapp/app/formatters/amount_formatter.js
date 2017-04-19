App.filter("AmountFormatter", function ($filter) {
    return function (amount, currency) {
         return $filter('CurrencyFormatter')(currency) + ' ' + amount.toFixed(3).replace(/[.,]0*$/, "");
    }
});