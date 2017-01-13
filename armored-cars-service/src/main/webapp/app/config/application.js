//begin app.config/application.js

window.App = angular
    .module('ArmoredCars',
        [
            'ngResource',
            'ngRoute',
            'ngCookies',
            'ui.bootstrap',
            'ngSanitize',
            'chieffancypants.loadingBar',
            'http-auth-interceptor'
        ]
    );

//end app.config/application.js