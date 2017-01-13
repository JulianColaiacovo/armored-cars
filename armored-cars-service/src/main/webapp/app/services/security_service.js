//begin security_service.js
App.factory('Security', ['$resource','$rootScope', function($resource, $rootScope) {
  return {query:function(appContext) {
      return $resource("/"+appContext+"/security/", {},
          {
              login : { method : 'PUT' },
              logout : { method : 'DELETE' }
          }
      )
  }}
}
]);

//begin end_service.js