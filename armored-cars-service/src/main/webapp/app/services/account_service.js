//begin account_service.js
App.factory('Account', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/accounts/";

    var accountOp = {};

    accountOp.change_password = function (body, callback, onError) {
        $resource(urlBase + "change-password/", {}, {}).save({}, body, callback, onError);
    };

    return accountOp;
}
]);
//end account_service.js