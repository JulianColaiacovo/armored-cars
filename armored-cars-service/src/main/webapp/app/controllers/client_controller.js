//begin client_controller.js
App.controller('ClientController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Client', 'DocumentType',
    function ($rootScope, $scope, $location, $routeParams, $http, Client, DocumentType) {

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

        $scope.save = function () {
            if ($scope.clientForm.$valid) {
                $scope.isSaving = true;
                Client.save($scope.client, onSaveOk, onSaveError);
                $scope.isSaving = false;
            }
        };

        var setSection = function () {
            if ($location.url().toLowerCase().indexOf("view") != -1) {
                $rootScope.section = "CLIENT-VIEW"
            } else if ($location.url().toLowerCase().indexOf("add") != -1) {
                $rootScope.section = "CLIENT-ADD"
            } else if ($location.url().toLowerCase().indexOf("edit") != -1) {
                $rootScope.section = "CLIENT-EDIT"
            }
        };

        var loadClientData = function () {
            if ($scope.isNewClient()) {
                $scope.client = {};
            } else {
                Client.get($routeParams.client_id, function (response) {
                    $scope.client = response;
                });
            }
        };

        var onSaveOk = function (response) {
            $location.path('/client/view/' + response.id);
        };

        var onSaveError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error saving client';
            }
        };

        var loadDocumentTypes = function () {
            DocumentType.getAll(function (response) {
                $scope.document_types = response;
            });
        };

        $scope.initialize();

    }]);
//end client_controller.js