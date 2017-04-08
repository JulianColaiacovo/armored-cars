//begin client_service.js
App.factory('Client', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/clients";

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

    clientOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    clientOp.delete = function (clientId, body, callback, onError) {
        $resource(urlBase + '/' + clientId, {}, {}).delete({}, body, callback, onError);
    };

    return clientOp;
}
]);

//end client_service.js