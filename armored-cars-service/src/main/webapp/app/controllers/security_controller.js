//begin login_controller.js
App.controller('LoginController', ['$rootScope', '$scope', '$cookies', '$location', 'Security', 'md5',
    function ($rootScope, $scope, $cookies, $location, Security, md5) {

        $scope.initialize = function () {
            $scope.user = {}
        };

        $scope.login = function () {
            var login = {
                "user_name": $scope.user.user_name,
                "password": md5.createHash($scope.user.password || '')
            };
            Security.query($rootScope.appContext).login(login,
                function (data) {
                    $rootScope.login(data.token, $scope.user.user_name)
                },
                function (data) {
                    $rootScope.globalError = "Error al loguearse. Por favor verifica tu usuario y contraseña"
                });
        };

        $scope.initialize();

    }]);

//begin login_controller.js