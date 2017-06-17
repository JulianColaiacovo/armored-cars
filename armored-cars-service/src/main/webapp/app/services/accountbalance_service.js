//begin accountbalance_service.js
App.factory('AccountBalance', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/account-balances";

    var accountBalanceOp = {};

    accountBalanceOp.getBalanceOfClient = function (clientId, callback) {
        $resource(urlBase + "/client/" + clientId, {}, {}).get(function (response) {
            callback(response);
        });
    };

    accountBalanceOp.getBalanceOfArmored = function (armoredId, callback) {
        $resource(urlBase + "/armored/" + armoredId, {}, {}).get(function (response) {
            callback(response);
        });
    };

    return accountBalanceOp;
}
]);

//end accountbalance_service.js