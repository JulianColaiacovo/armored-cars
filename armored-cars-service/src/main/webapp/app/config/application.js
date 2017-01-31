//begin app.config/application.js

window.App = angular
    .module('ArmoredCars',
        [
            'ngMessages',
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