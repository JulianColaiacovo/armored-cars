//begin aliquot_service.js
App.factory('Aliquot', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/aliquots/";

    var aliquotOp = {};

    aliquotOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    return aliquotOp;
    
}
]);

//end aliquot_service.js