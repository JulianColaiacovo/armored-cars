App.factory('requestInterceptor', ['$q', '$rootScope', '$location', function ($q, $rootScope, $location) {
    return {
        response: function(response
        ) {
            var path = $location.absUrl();
            var pathArray = path.split('/');
            var appContext = pathArray[3];
            $rootScope.appContext = appContext;

            if (appContext.indexOf("armored-cars") > -1){
                $rootScope.env = "PROD"
            }

            return response || $q.when(response);
        }
    }
}]);