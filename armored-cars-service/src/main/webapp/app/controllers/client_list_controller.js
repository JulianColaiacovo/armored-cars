//begin client_list_controller.js
App.controller('ClientListController', ['$rootScope', '$scope', '$location', '$http', 'Client',
    function ($rootScope, $scope, $location, $http, Client) {

        $scope.initialize = function () {
            $rootScope.section = "CLIENT-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.changeSortCriteria("-id");
            $scope.uploadFileTexts = {
                title: "Cargar excel de clientes"
            };
            $scope.loadClients();
        };

        $scope.loadClients = function () {
            Client.getAll(function (response) {
                $scope.clients = response;
            });
        };

        $scope.changeSortCriteria = function (criteria) {
            var regex = new RegExp('(-){0,1}' + criteria);
            if (regex.test($scope.client_sort_criteria)) {
                if ($scope.client_sort_criteria.startsWith('-')) {
                    $scope.client_sort_criteria = criteria;
                } else {
                    $scope.client_sort_criteria = '-' + criteria;
                }
            } else {
                $scope.client_sort_criteria = criteria;
            }
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("clients/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("clients/edit/" + id);
        };

        $scope.delete = function (client) {
            if (confirm("Esta seguro que desea eliminar el cliente " + client.name + "?")) {
                Client.delete(client.id, onDeleteOk, onDeleteError);
            }
        };

        $scope.selectFile = function (files) {
            $scope.selectedFile = files[0];
        };

        $scope.deselectFile = function () {
            $scope.selectedFile = null;
            angular.element(document.querySelector('#file')).val('');
        };

        $scope.uploadFile = function () {
            var fd = new FormData();
            //Take the first selected file
            fd.append("file", $scope.selectedFile);

            $scope.isUploadingFile = true;

            $http.post("/" + $rootScope.appContext + "/clients/excel", fd, {
                withCredentials: true,
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
            }).success(function (data, status, headers, config) {
                $location.path("clients/")
                $scope.isUploadingFile = false;
            }).error(function (response) {
                $scope.isUploadingFile = false;
                $rootScope.globalError = 'Error al cargar el excel de clientes';
            });

        };

        var onDeleteOk = function () {
            $location.path('/clients/');
        };

        var onDeleteError = function (response) {
            $rootScope.globalError = 'Error deleting client';
        };

    }]);
//end client_list_controller.js