//begin bill_type_code_service.js
App.factory('Currency', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/currencies";

    var billTypeCodeOp = {};

    billTypeCodeOp.getAllEnabled = function (callback) {
        $resource(urlBase, {
            enabled: true
        }, {}).query(function (response) {
            callback(response);
        });
    };

    return billTypeCodeOp;
}
]);

//end bill_type_code_service.js