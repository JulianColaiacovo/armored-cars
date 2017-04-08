//begin stock_status_service.js
App.factory('StockStatus', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/stock-statuses/";

    var stockStatusOp = {};

    stockStatusOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    return stockStatusOp;
    
}
]);

//end stock_status_service.js