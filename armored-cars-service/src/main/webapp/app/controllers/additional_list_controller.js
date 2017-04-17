//begin additional_list_controller.js
App.controller('AdditionalListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Additional',
    function ($rootScope, $scope, $location, $routeParams, $http, Additional) {

        $scope.initialize = function () {
            $rootScope.section = "ADDITIONAL-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadAdditionals();
        };

        $scope.loadAdditionals = function () {
            Additional.getAll(function (response) {
                $scope.additionals = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("additionals/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("additionals/edit/" + id);
        };

        $scope.delete = function (additional) {
            if (confirm("Esta seguro que desea eliminar el adicional numero " + additional.id + "?")) {
                Additional.delete(additional.id, onDeleteOk, onDeleteError);
            }
        };

        var onDeleteOk = function () {
            $location.path('/additionals/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar el adicional';
            }
        };

    }]);
//end additional_list_controller.js