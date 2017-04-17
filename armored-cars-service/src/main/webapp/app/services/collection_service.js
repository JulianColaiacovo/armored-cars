//begin collection_service.js
App.factory('Collection', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/collections";

    var collectionOp = {};

    collectionOp.get = function (collectionId, callback) {
        $resource(urlBase + '/:collection_id/', {
            collection_id: collectionId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    collectionOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    collectionOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    collectionOp.delete = function (collectionId, body, callback, onError) {
        $resource(urlBase + '/' + collectionId, {}, {}).delete({}, body, callback, onError);
    };

    return collectionOp;
}
]);

//end collection_service.js