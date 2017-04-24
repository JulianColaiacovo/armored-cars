//begin security_service.js
App.factory('Security', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/armored-cars/security/";

    var securityOp = {};

    securityOp.login = function (body, callback, onError) {
        $resource(urlBase, {}, { 'put': { method: 'PUT' } }).put({}, body, callback, onError);
    };

    securityOp.logout = function (params, callback, onError) {
        $resource(urlBase, params, {}).delete({}, {}, callback, onError);
    };

    return securityOp;
}
]);

//begin end_service.js