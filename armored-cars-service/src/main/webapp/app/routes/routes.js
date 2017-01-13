//begin app.routes.js

BASE_PATH = "/";
LOGIN_PATH = '/users/login';
NOTFOUND_PATH = "/errors/404";
PENDINGSNC_PATH="/pendingsNC";

App.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when(BASE_PATH, {
        redirectTo: LOGIN_PATH,
    }).
    when(LOGIN_PATH, {
        templateUrl: 'assets/login.html',
        controller: 'LoginController'
    }).
    otherwise({
        templateUrl: 'assets/not_found.html'
    });
}]);

//end app.routes.js