//begin client_controller.js
App.controller('ClientController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Client', 'DocumentType',
    function ($rootScope, $scope, $location, $routeParams, $http, Client, DocumentType) {

        var dniPattern = new RegExp("^\\d{2}\\.?\\d{3}\\.?\\d{3}$");
        var cuilCuitPattern = new RegExp("^\\d{2}\\-?\\d{2}\\.?\\d{3}\\.?\\d{3}\\-?\\d$");

        $scope.initialize = function () {
            $scope.isSaving = false;
            setSection();
            loadDocumentTypes();
            loadClientData();
        };

        $scope.isNewClient = function () {
            return $rootScope.section === "CLIENT-ADD";
        };

        $scope.canEditData = function () {
            return $rootScope.section === "CLIENT-ADD" || $rootScope.section === "CLIENT-EDIT";
        };

        $scope.documentTypeChanged = function () {
            $scope.form.document.$setDirty();
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                $scope.client.document = $scope.client.document.replace(/\./g, '').replace(/-/g, '');
                Client.save($scope.client, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.getDocumentNumberPattern = function () {
            if ($scope.client != null && $scope.client.document_type == "DNI") {
                return dniPattern;
            }
            return cuilCuitPattern;
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "CLIENT-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "CLIENT-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "CLIENT-EDIT"
            }
        };

        var loadClientData = function () {
            if ($scope.isNewClient()) {
                $scope.client = {"document_type": "DNI"};
            } else {
                Client.get($routeParams.client_id, function (response) {
                    $scope.client = response;
                });
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/clients/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            $rootScope.globalError = 'Error saving client';
        };

        var loadDocumentTypes = function () {
            DocumentType.getAll(function (response) {
                $scope.document_types = response;
            });
        };

        $scope.initialize();

    }]);
//end client_controller.js