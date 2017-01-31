//begin bill_type_code_service.js
App.factory('BillTypeCode', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/bill-type-code";

    var billTypeCodeOp = {};

    billTypeCodeOp.get = function (enabled, callback) {
        $resource(urlBase + '/:bill_id/', {
            enabled: enabled
        }, {}).get(function (response) {
            callback(response);
        });
    };

    return billTypeCodeOp;
}
]);

//end bill_type_code_service.js