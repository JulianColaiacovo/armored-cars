//begin app.routes.js

BASE_PATH = "/";
LOGIN_PATH = '/users/login';
ACCOUNT_CHANGEPASSWORD_PATH = '/accounts/change-password';
ADDITIONAL_LIST_PATH = '/additionals';
ADDITIONAL_ACTION_PATH = '/additionals/:action/:additional_id?';
ADDITIONALCOLLECTION_LIST_PATH = '/additional-collections';
ADDITIONALCOLLECTION_ACTION_PATH = '/additional-collections/:action/:additionalcollection_id?';
ARMORED_LIST_PATH = '/armoreds';
ARMORED_ACTION_PATH = '/armoreds/:action/:armored_id?';
BILL_LIST_PATH = '/bills';
BILL_ACTION_PATH = '/bills/:action/:bill_id?';
CLIENT_LIST_PATH = '/clients';
CLIENT_ACTION_PATH = '/clients/:action/:client_id?';
COLLECTION_LIST_PATH = '/collections';
COLLECTION_ACTION_PATH = '/collections/:action/:collection_id?';
USER_LIST_PATH = '/users';
USER_ACTION_PATH = '/users/:action/:user_id?';
NOTFOUND_PATH = "/errors/404";
FORBIDDEN_PATH = "/forbidden/403";

App.config(['$routeProvider', function ($routeProvider) {

    var financialPermission = function ($q, $location, $rootScope) {
        var deferred = $q.defer();
        deferred.resolve();
        if (!$rootScope.isFinancial()) {
            $location.path(FORBIDDEN_PATH);
        }
        return deferred.promise;
    };

    var accountingPermission = function ($q, $location, $rootScope) {
        var deferred = $q.defer();
        deferred.resolve();
        if (!$rootScope.isFinancialOrAccounting()) {
            $location.path(FORBIDDEN_PATH);
        }
        return deferred.promise;
    };

    $routeProvider.when(BASE_PATH, {
        redirectTo: CLIENT_LIST_PATH
    }).when(LOGIN_PATH, {
        templateUrl: 'assets/login.html',
        controller: 'LoginController'
    }).when(ACCOUNT_CHANGEPASSWORD_PATH, {
        templateUrl: 'assets/account/changepassword.html',
        controller: 'ChangePasswordController'
    }).when(ADDITIONAL_LIST_PATH, {
        templateUrl: 'assets/additional/list.html',
        controller: 'AdditionalListController',
        resolve: { app: accountingPermission }
    }).when(ADDITIONAL_ACTION_PATH, {
        templateUrl: 'assets/additional/actions.html',
        controller: 'AdditionalController',
        resolve: { app: accountingPermission }
    }).when(ADDITIONALCOLLECTION_LIST_PATH, {
        templateUrl: 'assets/additionalcollection/list.html',
        controller: 'AdditionalCollectionListController',
        resolve: { app: accountingPermission }
    }).when(ADDITIONALCOLLECTION_ACTION_PATH, {
        templateUrl: 'assets/additionalcollection/actions.html',
        controller: 'AdditionalCollectionController',
        resolve: { app: accountingPermission }
    }).when(ARMORED_LIST_PATH, {
        templateUrl: 'assets/armored/list.html',
        controller: 'ArmoredListController'
    }).when(ARMORED_ACTION_PATH, {
        templateUrl: 'assets/armored/actions.html',
        controller: 'ArmoredController'
    }).when(BILL_LIST_PATH, {
        templateUrl: 'assets/bill/list.html',
        controller: 'BillListController',
        resolve: { app: accountingPermission }
    }).when(BILL_ACTION_PATH, {
        templateUrl: 'assets/bill/actions.html',
        controller: 'BillController',
        resolve: { app: accountingPermission }
    }).when(CLIENT_LIST_PATH, {
        templateUrl: 'assets/clients/list.html',
        controller: 'ClientListController',
    }).when(CLIENT_ACTION_PATH, {
        templateUrl: 'assets/clients/actions.html',
        controller: 'ClientController',
    }).when(COLLECTION_LIST_PATH, {
        templateUrl: 'assets/collection/list.html',
        controller: 'CollectionListController',
        resolve: { app: accountingPermission }
    }).when(COLLECTION_ACTION_PATH, {
        templateUrl: 'assets/collection/actions.html',
        controller: 'CollectionController',
        resolve: { app: accountingPermission }
    }).when(USER_LIST_PATH, {
        templateUrl: 'assets/user/list.html',
        controller: 'UserListController',
        resolve: { app: financialPermission }
    }).when(USER_ACTION_PATH, {
        templateUrl: 'assets/user/actions.html',
        controller: 'UserController',
        resolve: { app: financialPermission }
    }).when(FORBIDDEN_PATH, {
        templateUrl: 'assets/forbidden.html'
    }).otherwise({
        templateUrl: 'assets/not_found.html'
    });
}]);

//end app.routes.js