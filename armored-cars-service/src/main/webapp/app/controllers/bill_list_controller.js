//begin armored_list_controller.js
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

        $scope.view = function (id) {
            $location.path("bills/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("bills/edit/" + id);
        };

        $scope.delete = function (armored) {
            if (confirm("Esta seguro que desea eliminar la factura numero " + armored.code + "?")) {
                Armored.delete(armored.id, onDeleteOk, onDeleteError);
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
//end armored_list_controller.js