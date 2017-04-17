//begin changepassword_controller.js
App.controller('ChangePasswordController', ['$rootScope', '$scope', '$cookies', '$location', 'Account', 'md5',
    function ($rootScope, $scope, $cookies, $location, Account, md5) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.password = {}
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                var data = {
                    "user_name": $rootScope.logged_user,
                    "old_password": md5.createHash($scope.password.old || ''),
                    "new_password": md5.createHash($scope.password.new || ''),
                    "repeat_new_password": md5.createHash($scope.password.repeat_new || '')
                };
                Account.change_password(data, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.canEditData = function () {
            return true;
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/clients');
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al cambiar la contrase\u00f1a';
            }
        };

        $scope.initialize();

    }]);

//begin changepassword_controller.js