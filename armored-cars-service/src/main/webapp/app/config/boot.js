//begin boot.js
App.run(['$rootScope', '$templateCache', '$cookies', '$location', '$modal', '$timeout', '$route', '$q', 'Security', 'User',
    function ($rootScope, $templateCache, $cookies, $location, $modal, $timeout, $route, $q, Security, User) {

        $rootScope.initialize = function () {
            if ($cookies.get("token")) $rootScope.token = $cookies.get("token");
            if ($cookies.get("logged_user")) $rootScope.logged_user = $cookies.get("logged_user");
            refreshPermissions();
        };

        $rootScope.login = function (token, user) {
            $rootScope.token = token;
            $rootScope.logged_user = user;

            $cookies.put("token", $rootScope.token);
            $cookies.put("logged_user", $rootScope.logged_user);

            refreshPermissions();

            $location.path(BASE_PATH);
        };

        $rootScope.logout = function () {
            Security.logout({token: $rootScope.token}, function () {
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

        $rootScope.isFinancialOrAccounting = function () {
            return $rootScope.isFinancial() || $rootScope.isAccounting();
        };

        $rootScope.isFinancial = function () {
            return $rootScope.user_data.user_level == "FINANCIAL";
        };

        $rootScope.isAccounting = function () {
            return $rootScope.user_data.user_level == "ACCOUNTING";
        };

        $rootScope.isWorkshop = function () {
            return $rootScope.user_data.user_level == "WORKSHOP";
        }; 

        var refreshPermissions = function () {
            if ($rootScope.user_data == null) {
                $rootScope.user_data = { user_level: 'WORKSHOP' };
            }
            User.getByName($rootScope.logged_user, function (response) {
                $rootScope.user_data = response;
            });
        };

        $rootScope.initialize();
    }]);

//end boot.js