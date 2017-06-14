//begin security_service.js
App.factory('User', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/armored-cars/users";

    var userOp = {};

    userOp.get = function (userId, callback) {
        $resource(urlBase + '/:user_id/', {
            user_id: userId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    userOp.getByName = function (name, callback) {
        return $resource(urlBase + '/name/:name/', {
            name: name
        }, {}).get(function (response) {
            callback(response);
        });
    };

    userOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    userOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    userOp.delete = function (userId, body, callback, onError) {
        $resource(urlBase + '/' + userId, {}, {}).delete({}, body, callback, onError);
    };

    userOp.change_password = function (body, callback, onError) {
        $resource(urlBase + "/change-password", {}, {}).save({}, body, callback, onError);
    };

    return userOp;
}
]);

//begin end_service.js