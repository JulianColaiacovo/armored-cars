//begin armored_controller.js
App.controller('ArmoredController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored', 'StockStatus', 'Client',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored, StockStatus, Client) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.armored = {};
            setSection();
            loadStockStatuses();
            loadData();
        };

        $scope.isNew = function () {
            return $rootScope.section === "ARMORED-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "ARMORED-EDIT";
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                Armored.save($scope.armored, onSaveOk, onSaveError);
                $scope.isSaving = false;
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        var loadData = function () {
            if (!$scope.isNew()) {
                Armored.get($routeParams.armored_id, function (response) {
                    response.entry_date = new Date(response.entry_date);
                    response.departure_date = new Date(response.departure_date);
                    response.delivery_date = new Date(response.delivery_date);
                    $scope.armored = response;
                });
            }
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "ARMORED-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "ARMORED-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "ARMORED-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $location.path('/armoreds/view/' + response.id);
        };

        var onSaveError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar el blindaje';
            }
        };

        var loadStockStatuses = function () {
            StockStatus.getAll(function (response) {
                $scope.stock_statuses = response;
                $scope.armored.stock_status = $scope.armored.stock_status || 'WITHOUT_STOCK';
            });
        };

        $scope.initialize();

    }]);
//end armored_controller.js