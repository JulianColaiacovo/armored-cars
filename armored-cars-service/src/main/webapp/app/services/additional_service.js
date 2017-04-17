//begin additional_service.js
App.factory('Additional', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/additionals";

    var additionalOp = {};

    additionalOp.get = function (additionalId, callback) {
        $resource(urlBase + '/:additional_id/', {
            additional_id: additionalId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    additionalOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    additionalOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    additionalOp.delete = function (additionalId, body, callback, onError) {
        $resource(urlBase + '/' + additionalId, {}, {}).delete({}, body, callback, onError);
    };

    return additionalOp;
}
]);

//end additional_service.js