//begin bill_service.js
App.factory('Bill', ['$resource', '$rootScope', '$http', function ($resource, $rootScope, $http) {

    var urlBase = "/" + $rootScope.appContext + "/bills";

    var billOp = {};

    billOp.get = function (billId, callback) {
        $resource(urlBase + '/:bill_id/', {
            bill_id: billId
        }, {}).get(function (response) {
            callback(response);
        });
    };

    billOp.getNextNumber = function (billTypeCode, callback) {
        $http.get(urlBase + '/next-number', {
            params: { billTypeCode: billTypeCode }
        }).then(function(response) {
            callback(response.data)
        }, {});
    };

    billOp.getUncollectedBills = function (callback) {
        $resource(urlBase + "/uncollected", {}, {}).query(callback);
    };

    billOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    billOp.save = function (body, callback, onError) {
        $resource(urlBase, {}, {}).save({}, body, callback, onError);
    };

    billOp.delete = function (clientId, body, callback, onError) {
        $resource(urlBase + '/' + clientId, {}, {}).delete({}, body, callback, onError);
    };

    billOp.search = function (billTypeCode, clientName, callback) {
        $http.get(urlBase + '/search', {
            params: {
                billTypeCode: billTypeCode,
                clientName: clientName
            }
        }).then(function(response) {
            callback(response.data)
        }, {});
    };

    return billOp;
}
]);

//end bill_service.js