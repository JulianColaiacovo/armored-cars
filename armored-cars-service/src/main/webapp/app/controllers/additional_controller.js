//begin additional_controller.js
App.controller('AdditionalController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Additional', 'Currency', 'Armored',
    function ($rootScope, $scope, $location, $routeParams, $http, Additional, Currency, Armored) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
            loadCurrencies();
            loadData();
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
                Additional.save($scope.additional, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
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