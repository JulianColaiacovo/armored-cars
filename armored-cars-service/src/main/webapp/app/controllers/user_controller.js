//begin user_controller.js
App.controller('UserController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'User', 'UserLevel', 'md5',
    function ($rootScope, $scope, $location, $routeParams, $http, User, UserLevel, md5) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.user = {};
            setSection();
            loadUserLevels();
            loadData();
        };

        $scope.isNew = function () {
            return $rootScope.section === "USER-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "USER-EDIT";
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                var data = jQuery.extend(true, {}, $scope.user);
                data.password = md5.createHash(data.password || '');
                User.save(data, onSaveOk, onSaveError);
                $scope.isSaving = false;
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.validateRepeatPassword = function (repeatPassword) {
            return $scope.user.password == repeatPassword;
        };

        var loadData = function () {
            if (!$scope.isNew()) {
                User.get($routeParams.user_id, function (response) {
                    $scope.user = response;
                });
            }
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "USER-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "USER-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "USER-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $location.path('/users/view/' + response.id);
        };

        var onSaveError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar el usuario';
            }
        };

        var loadUserLevels = function () {
            UserLevel.getAll(function (response) {
                $scope.user_levels = response;
                $scope.user.user_level = $scope.user.user_level || 'WORKSHOP';
            });
        };

        $scope.initialize();

    }]);
//end user_controller.js