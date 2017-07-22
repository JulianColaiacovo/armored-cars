//begin bill_list_controller.js
App.controller('BillListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Bill',
    function ($rootScope, $scope, $location, $routeParams, $http, Bill) {

        $scope.initialize = function () {
            $rootScope.section = "BILL-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadBills();
        };

        $scope.loadBills = function () {
            Bill.getAll(function (response) {
                $scope.bills = response;
            });
        };

        $scope.initialize();

        $scope.view = function (bill) {
            $location.path("bills/view/" + bill.id);
        };

        $scope.edit = function (bill) {
            $location.path("bills/edit/" + bill.id);
        };

        $scope.delete = function (bill) {
            if (confirm("Esta seguro que desea eliminar la factura numero " + bill.id + "?")) {
                Bill.delete(bill.id, onDeleteOk, onDeleteError);
            }
        };

        var onDeleteOk = function () {
            $location.path('/bills/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar la factura';
            }
        };

    }]);
//end bill_list_controller.js