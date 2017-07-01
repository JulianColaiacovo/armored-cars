//begin armored_list_controller.js
App.controller('ArmoredListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored) {

        $scope.initialize = function () {
            $rootScope.section = "ARMORED-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.uploadFileTexts = {
                title: "Cargar excel de blindados"
            };
            $scope.loadArmoreds();
        };

        $scope.loadArmoreds = function () {
            Armored.getAll(function (response) {
                $scope.armoreds = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("armoreds/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("armoreds/edit/" + id);
        };

        $scope.delete = function (armored) {
            if (confirm("Esta seguro que desea eliminar el blindaje numero " + armored.code + "?")) {
                Armored.delete(armored.id, onDeleteOk, onDeleteError);
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
            $http.post("/" + $rootScope.appContext + "/armoreds/excel", fd, {
                withCredentials: true,
                headers: {'Content-Type': undefined},
                transformRequest: angular.identity
            }).success(function (data, status, headers, config) {
                $scope.isUploadingFile = false;
                $location.path("armoreds/")
            }).error(function (response) {
                $scope.isUploadingFile = false;
                $rootScope.globalError = 'Error al cargar el excel de blindajes';
            });

        };

        var onDeleteOk = function () {
            $location.path('/armoreds/');
        };

        var onDeleteError = function (response) {
            $rootScope.globalError = 'Error al borrar el blindaje';
        };

    }]);
//end armored_list_controller.js