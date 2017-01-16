//begin app.routes.js

BASE_PATH = "/";
LOGIN_PATH = '/users/login';
CLIENT_LIST_PATH = '/client';
CLIENT_ADD_PATH = '/client/add';
CLIENT_EDIT_PATH = '/client/edit/:client_id';
CLIENT_VIEW_PATH = '/client/view/:client_id';
NOTFOUND_PATH = "/errors/404";

App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when(BASE_PATH, {
        redirectTo: CLIENT_LIST_PATH
    }).when(LOGIN_PATH, {
        templateUrl: 'assets/login.html',
        controller: 'LoginController'
    }).when(CLIENT_LIST_PATH, {
        templateUrl: 'assets/clients/list.html',
        controller: 'ClientListController'
    }).when(CLIENT_ADD_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController'
    }).when(CLIENT_EDIT_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController'
    }).when(CLIENT_VIEW_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController'
    }).otherwise({
        templateUrl: 'assets/not_found.html'
    });
}]);

//end app.routes.js