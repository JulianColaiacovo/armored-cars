//begin additional_service.js
App.factory('AdditionalCollection', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/additional-collections";

    var additionalOp = {};

    additionalOp.get = function (additionalCollectionId, callback) {
        $resource(urlBase + '/:additionalcollection_id/', {
            additionalcollection_id: additionalCollectionId
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

    additionalOp.delete = function (additionalCollectionId, body, callback, onError) {
        $resource(urlBase + '/' + additionalCollectionId, {}, {}).delete({}, body, callback, onError);
    };

    return additionalOp;
}
]);

//end additional_service.js