//begin client_controller.js
App.controller('ArmoredController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
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
            if ($scope.isNew()) {
                $scope.armored = {"stock_status": "WITHOUT_STOCK"};
            } else {
                Armored.get($routeParams.armored_id, function (response) {
                    $scope.armored = response;
                });
            }
        };

        var setSection = function () {
            if ($location.url().toLowerCase().indexOf("view") != -1) {
                $rootScope.section = "ARMORED-VIEW"
            } else if ($location.url().toLowerCase().indexOf("add") != -1) {
                $rootScope.section = "ARMORED-ADD"
            } else if ($location.url().toLowerCase().indexOf("edit") != -1) {
                $rootScope.section = "ARMORED-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $location.path('/armored/view/' + response.id);
        };

        var onSaveError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error saving armored';
            }
        };

        $scope.initialize();

    }]);
//end client_controller.js