// Configure interceptors.
App.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('requestInterceptor');
}]);