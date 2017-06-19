//begin additional_controller.js
App.controller('UncollectedBillController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Bill',
    function ($rootScope, $scope, $location, $routeParams, $http, Bill) {

        $scope.initialize = function () {
            $rootScope.section = "LISTS-UNCOLLECTEDBILLS-VIEW";
            searchUncollectedBills();
        };

        var searchUncollectedBills = function () {
            Bill.getUncollectedBills(function (response) {
                $scope.uncollected_bills = response;
            })
        };

        $scope.initialize();

    }]);
//end additional_controller.js