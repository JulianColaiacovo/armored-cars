App.filter("BillTypeCodeFormatter", function () {
    return function (billTypeCode) {
        if (billTypeCode == 'BILL_A') {
            return "Factura A";
        } else if (billTypeCode == 'BILL_B') {
            return "Factura B";
        } else if (billTypeCode == 'DEBIT_NOTE_A') {
            return "Nota de debito A";
        } else if (billTypeCode == 'DEBIT_NOTE_B') {
            return "Nota de debito B";
        } else if (billTypeCode == 'CREDIT_NOTE_A') {
            return "Nota de credito A";
        } else if (billTypeCode == 'CREDIT_NOTE_B') {
            return "Nota de credito B";
        } else {
            return billTypeCode;
        }
    }
});