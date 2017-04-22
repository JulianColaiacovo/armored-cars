//begin armored_service.js
App.factory('Armored', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/armoreds";

    var armoredOp = {};

    armoredOp.get = function (armoredId, callback) {
        $resource(urlBase + '/:armored_id/', {
            armored_id: armoredId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    armoredOp.getBillToClient = function (armoredId, callback) {
        $resource(urlBase + '/:armored_id/bill-to-client', {
            armored_id: armoredId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    armoredOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    armoredOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    armoredOp.delete = function (clientId, body, callback, onError) {
        $resource(urlBase + '/' + clientId, {}, {}).delete({}, body, callback, onError);
    };

    return armoredOp;
}
]);

//end armored_service.js