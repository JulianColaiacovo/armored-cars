//begin client_service.js
App.factory('Client', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/client";

    var clientOp = {};

    clientOp.get = function (clientId, callback) {
        $resource(urlBase + '/:client_id/', {
            client_id: clientId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    clientOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    return clientOp;
}
]);

//end client_service.js