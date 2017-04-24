//begin user_level_service.js
App.factory('UserLevel', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/user-levels";

    var userLevelsOp = {};

    userLevelsOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    return userLevelsOp;
    
}
]);

//end user_level_service.js