//begin login_controller.js
App.controller('LoginController', ['$rootScope', '$scope', '$cookies', '$location', 'Security',
    function ($rootScope, $scope, $cookies, $location, Security) {

        $scope.initialize = function () {
            $scope.user = {}
        };

        $scope.login = function () {

            Security.query($rootScope.appContext).login($scope.user,
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