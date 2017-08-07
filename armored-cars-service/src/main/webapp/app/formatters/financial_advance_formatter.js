App.filter("FinancialAdvanceFormatter", function ($filter) {
    return function (boolean, billTypeCode) {
        if (billTypeCode.startsWith('BILL_')) {
            return $filter('BooleanFormatter')(boolean);
        } else {
            return '-';
        }
    }
});