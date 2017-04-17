//begin collection_controller.js
App.controller('CollectionController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Bill', 'Currency', 'Collection',
    function ($rootScope, $scope, $location, $routeParams, $http, Bill, Currency, Collection) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
            loadData();
            loadCurrencies();
        };

        $scope.isNew = function () {
            return $rootScope.section === "COLLECTION-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "COLLECTION-EDIT";
        };

        $scope.updateTotal = function () {
            $scope.collection.total_amount = $scope.collection.base_amount + $scope.collection.gain_amount +
                $scope.collection.vat_amount + $scope.collection.suss_amount + $scope.collection.iibb_amount;
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                Collection.save($scope.collection, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        var loadData = function () {
            if ($scope.isNew()) {
                $scope.collection = {
                    "base_amount": 0,
                    "gain_amount": 0,
                    "vat_amount": 0,
                    "suss_amount": 0,
                    "iibb_amount": 0,
                    "total_amount": 0
                };
            } else {
                Collection.get($routeParams.collection_id, function (collectionResponse) {
                    collectionResponse.date = new Date(collectionResponse.date);
                    $scope.collection = collectionResponse;
                    Bill.get(collectionResponse.bill_id, function (billResponse) {
                        billResponse.date = new Date(billResponse.date);
                        $scope.bill = billResponse;
                    });
                });
            }
        };

        var setSection = function () {
            if ($location.url().toLowerCase().indexOf("view") != -1) {
                $rootScope.section = "COLLECTION-VIEW"
            } else if ($location.url().toLowerCase().indexOf("add") != -1) {
                $rootScope.section = "COLLECTION-ADD"
            } else if ($location.url().toLowerCase().indexOf("edit") != -1) {
                $rootScope.section = "COLLECTION-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/collections/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar la cobranza';
            }
        };

        var loadCurrencies = function () {
            Currency.getAllEnabled(function (response) {
                $scope.currencies = response;
                $scope.collection.currency_code = $scope.collection.currency_code || 'ARS';
            });
        };

        $scope.initialize();

    }]);
//end collection_controller.js