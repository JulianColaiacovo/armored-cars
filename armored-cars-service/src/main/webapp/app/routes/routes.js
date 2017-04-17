//begin app.routes.js

BASE_PATH = "/";
LOGIN_PATH = '/users/login';
ARMORED_LIST_PATH = '/armoreds';
ARMORED_ACTION_PATH = '/armoreds/:action/:armored_id?';
BILL_LIST_PATH = '/bills';
BILL_ACTION_PATH = '/bills/:action/:bill_id?';
CLIENT_LIST_PATH = '/clients';
CLIENT_ACTION_PATH = '/clients/:action/:client_id?';
COLLECTION_LIST_PATH = '/collections';
COLLECTION_ACTION_PATH = '/collections/:action/:collection_id?';
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
    }).when(BILL_LIST_PATH, {
        templateUrl: 'assets/bill/list.html',
        controller: 'BillListController'
    }).when(BILL_ACTION_PATH, {
        templateUrl: 'assets/bill/actions.html',
        controller: 'BillController'
    }).when(CLIENT_LIST_PATH, {
        templateUrl: 'assets/clients/list.html',
        controller: 'ClientListController'
    }).when(CLIENT_ACTION_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController'
    }).when(COLLECTION_LIST_PATH, {
        templateUrl: 'assets/collection/list.html',
        controller: 'CollectionListController'
    }).when(COLLECTION_ACTION_PATH, {
        templateUrl: 'assets/collection/actions.html',
        controller: 'CollectionController'
    }).otherwise({
        templateUrl: 'assets/not_found.html'
    });
}]);

//end app.routes.js