//begin bill_type_code_service.js
App.factory('BillTypeCode', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/bill-type-codes";

    var billTypeCodeOp = {};

    billTypeCodeOp.getAllEnabled = function (callback) {
        $resource(urlBase, {
            enabled: true
        }, {}).query(function (response) {
            callback(response);
        });
    };

    billTypeCodeOp.getPossibleApplies = function (billTypeCode, callback) {
        $resource(urlBase + "/possible-applies", {
            billTypeCode: billTypeCode
        }, {}).query(function (response) {
            callback(response);
        });
    };

    return billTypeCodeOp;
}
]);

//end bill_type_code_service.js