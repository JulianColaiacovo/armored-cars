//begin collection_list_controller.js
App.controller('CollectionListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Collection',
    function ($rootScope, $scope, $location, $routeParams, $http, Collection) {

        $scope.initialize = function () {
            $rootScope.section = "COLLECTION-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadCollections();
        };

        $scope.loadCollections = function () {
            Collection.getAll(function (response) {
                $scope.collections = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("collections/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("collections/edit/" + id);
        };

        $scope.delete = function (collection) {
            if (confirm("Esta seguro que desea eliminar la cobranza numero " + collection.id + "?")) {
                Collection.delete(collection.id, onDeleteOk, onDeleteError);
            }
        };

        var onDeleteOk = function () {
            $location.path('/collections/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar la cobranza';
            }
        };

    }]);
//end collection_list_controller.js