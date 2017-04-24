//begin additional_list_controller.js
App.controller('UserListController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'User',
    function ($rootScope, $scope, $location, $routeParams, $http, User) {

        $scope.initialize = function () {
            $rootScope.section = "USER-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadUsers();
        };

        $scope.loadUsers = function () {
            User.getAll(function (response) {
                $scope.users = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("users/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("users/edit/" + id);
        };

        $scope.delete = function (user) {
            if (confirm("Esta seguro que desea eliminar el usuario " + user.user_name + "?")) {
                User.delete(user.id, onDeleteOk, onDeleteError);
            }
        };

        var onDeleteOk = function () {
            $location.path('/users/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al borrar el usuario';
            }
        };

    }]);
//end additional_list_controller.js