//begin additionalcollection_list_controller.js
App.controller('AdditionalCollectionListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'AdditionalCollection',
    function ($rootScope, $scope, $location, $routeParams, $http, AdditionalCollection) {

        $scope.initialize = function () {
            $rootScope.section = "ADDITIONALCOLLECTION-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadAdditionalCollections();
        };

        $scope.loadAdditionalCollections = function () {
            AdditionalCollection.getAll(function (response) {
                $scope.additionalcollections = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("additional-collections/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("additional-collections/edit/" + id);
        };

        $scope.delete = function (additionalcollection) {
            if (confirm("Esta seguro que desea eliminar el cobro del adicional numero " + additionalcollection.id + "?")) {
                AdditionalCollection.delete(additionalcollection.id, onDeleteOk, onDeleteError);
            }
        };

        var onDeleteOk = function () {
            $location.path('/additional-collections/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar el cobro del adicional';
            }
        };

    }]);
//end additionalcollection_list_controller.js