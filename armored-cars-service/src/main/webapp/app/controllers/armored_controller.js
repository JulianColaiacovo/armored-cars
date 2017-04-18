//begin armored_controller.js
App.controller('ArmoredController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored', 'StockStatus', 'Client',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored, StockStatus, Client) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.armored = {};
            initModals();
            setSection();
            loadStockStatuses();
            loadData();
            $scope.clientModalSearch();
        };

        $scope.isNew = function () {
            return $rootScope.section === "ARMORED-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "ARMORED-EDIT";
        };

        $scope.save = function () {
            if ($scope.form.$valid && $scope.modals.client.selected != null) {
                $scope.isSaving = true;
                $scope.armored.billing_and_reference.bill_to_client_id = $scope.modals.client.selected.id;
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

        $scope.showClientModal = function () {
            $scope.modals.client.visible = true;
        };

        var hideClientModal = function () {
            $scope.modals.client.visible = false;
        };

        $scope.clientModalSearch = function () {
            var client = $scope.modals.client;
            Client.search(client.name, client.document, function (response) {
                $scope.modals.client.items = response;
            });
        };

        $scope.selectClient = function (client) {
            $scope.modals.client.selected = client;
            hideClientModal();
        };

        var initModals = function () {
            $scope.modals = {
                "client": {
                    "visible": false,
                    "selected": null
                }
            };
        };

        var loadData = function () {
            if ($scope.isNew()) {
                $scope.armored = {
                    "price": 0
                };
            } else {
                Armored.get($routeParams.armored_id, function (response) {
                    response.entry_date = new Date(response.entry_date);
                    response.departure_date = new Date(response.departure_date);
                    response.delivery_date = new Date(response.delivery_date);
                    Client.get(response.billing_and_reference.bill_to_client_id, function (client) {
                        $scope.modals.client.selected = client;
                    });
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