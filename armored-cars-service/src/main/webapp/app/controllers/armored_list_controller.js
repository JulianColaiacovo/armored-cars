//begin armored_list_controller.js
App.controller('ArmoredListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored) {

        $scope.initialize = function () {
            $rootScope.section = "ARMORED-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
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

        var onDeleteOk = function () {
            $location.path('/armoreds/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar el blindaje';
            }
        };

    }]);
//end armored_list_controller.js