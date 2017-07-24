//begin additional_controller.js
App.controller('AdditionalController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Additional', 'Currency', 'Armored',
    function ($rootScope, $scope, $location, $routeParams, $http, Additional, Currency, Armored) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
            loadCurrencies();
            loadData();
            initModals();
            $scope.armoredModalSearch();
        };

        $scope.isNew = function () {
            return $rootScope.section === "ADDITIONAL-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "ADDITIONAL-EDIT";
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                $scope.additional.armored_id = $scope.modals.armored.selected.id;
                Additional.save($scope.additional, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.selectArmored = function (armored) {
            $scope.modals.armored.selected = armored;
            $scope.hideArmoredModal();
        };

        $scope.armoredModalSearch = function () {
            var armored = $scope.modals.armored || {};
            Armored.search(armored.code, armored.brand, armored.client_name, function (response) {
                $scope.modals.armored.items = response;
            });
        };

        $scope.showArmoredModal = function () {
            $scope.modals.armored.visible = true;
        };

        $scope.hideArmoredModal = function () {
            $scope.modals.armored.visible = false;
        };

        $scope.currencyChanged = function () {
            if ($scope.additional.currency_code === 'USD') {
                $scope.additional.conversion = 1;
            }
        };

        var initModals = function () {
            $scope.modals = {
                "armored": {
                    "visible": false,
                    "selected": null
                }
            };
        };

        var loadData = function () {
            $scope.additional = {};
            if ($scope.isNew()) {
                $scope.additional = {
                    "date": new Date(),
                    "amount": 0
                };
            } else {
                Additional.get($routeParams.additional_id, function (response) {
                    response.date = new Date(response.date);
                    $scope.additional = response;
                    Armored.get(response.armored_id, function (armoredResponse) {
                        $scope.modals.armored.selected = armoredResponse;
                    });
                });
            }
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "ADDITIONAL-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "ADDITIONAL-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "ADDITIONAL-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/additionals/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar el adicional';
            }
        };

        var loadCurrencies = function () {
            Currency.getAllEnabled(function (response) {
                $scope.currencies = response;
                $scope.additional.currency_code = $scope.additional.currency_code || 'ARS';
            });
        };

        $scope.initialize();

    }]);
//end additional_controller.js