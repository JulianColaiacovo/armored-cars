App.filter("BillFormatter", function ($filter) {
    return function (bill) {
        return $filter('BillTypeCodeFormatter')(bill.bill_type_code) + ' - ' + $filter('BillNumberFormatter')(bill.number);
    }
});