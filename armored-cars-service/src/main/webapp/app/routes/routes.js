//begin app.routes.js

BASE_PATH = "/";
LOGIN_PATH = '/users/login';
ARMORED_LIST_PATH = '/armoreds';
ARMORED_ACTION_PATH = '/armoreds/:action/:armored_id?';
CLIENT_LIST_PATH = '/clients';
CLIENT_ACTION_PATH = '/clients/:action/:client_id?';
NOTFOUND_PATH = "/errors/404";

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when(BASE_PATH, {
        redirectTo: CLIENT_LIST_PATH
    }).when(LOGIN_PATH, {
        templateUrl: 'assets/login.html',
        controller: 'LoginController'
    }).when(ARMORED_LIST_PATH, {
        templateUrl: 'assets/armored/list.html',
        controller: 'ArmoredListController'
    }).when(ARMORED_ACTION_PATH, {
        templateUrl: 'assets/armored/actions.html',
        controller: 'ArmoredController'
    }).when(CLIENT_LIST_PATH, {
        templateUrl: 'assets/clients/list.html',
        controller: 'ClientListController'
    }).when(CLIENT_ACTION_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController'
    }).otherwise({
        templateUrl: 'assets/not_found.html'
    });
}]);

//end app.routes.js