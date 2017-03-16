//begin boot.js
App.run(['$rootScope', '$templateCache', '$cookies', '$location', '$modal', '$timeout', '$route', 'Security',
    function ($rootScope, $templateCache, $cookies, $location, $modal, $timeout, $route, Security) {

        $rootScope.initialize = function () {
            if ($cookies.get("token")) $rootScope.token = $cookies.get("token");
            if ($cookies.get("logged_user")) $rootScope.logged_user = $cookies.get("logged_user");
        };

        $rootScope.login = function (token, userName) {
            $rootScope.token = token;
            $rootScope.logged_user = userName;

            $cookies.put("token", $rootScope.token);
            $cookies.put("logged_user", $rootScope.logged_user);

            $location.path(BASE_PATH);
        };

        $rootScope.logout = function () {
            Security.query($rootScope.appContext).logout({token: $rootScope.token}, function () {
                $rootScope.token = null;
                $rootScope.logged_user = null;

                $cookies.remove("token");
                $cookies.remove("logged_user");

                $location.path(BASE_PATH);
            });
        };

        $rootScope.clearNotifications = function () {
            $rootScope.globalError = null;
            $rootScope.globalWarning = null;
            $rootScope.globalSuccess = null;
        };

        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            $timeout($rootScope.clearNotifications, 5000);

            if (!$rootScope.token) {
                $location.path(LOGIN_PATH);
            }
        });

        $rootScope.initialize();
    }]);

//end boot.js