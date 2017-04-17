//begin additionalcollection_controller.js
App.controller('AdditionalCollectionController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'AdditionalCollection',
    function ($rootScope, $scope, $location, $routeParams, $http, AdditionalCollection) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
            loadData();
        };

        $scope.isNew = function () {
            return $rootScope.section === "ADDITIONALCOLLECTION-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "ADDITIONALCOLLECTION-EDIT";
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                AdditionalCollection.save($scope.additionalcollection, onSaveOk, onSaveError);
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
                $scope.additionalcollection = {
                    "date": new Date(),
                    "amount": 0
                };
            } else {
                AdditionalCollection.get($routeParams.additionalcollection_id, function (response) {
                    response.date = new Date(response.date);
                    $scope.additionalcollection = response;
                });
            }
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "ADDITIONALCOLLECTION-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "ADDITIONALCOLLECTION-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "ADDITIONALCOLLECTION-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/additional-collections/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar el cobro del adicional';
            }
        };

        $scope.initialize();

    }]);
//end additionalcollection_controller.js