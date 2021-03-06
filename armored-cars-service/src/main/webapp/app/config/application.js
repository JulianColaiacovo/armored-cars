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
            'http-auth-interceptor',
            'datetimepicker',
            'angular-md5',
            'ui.mask',
            'ui.validate',
            'ngFileUpload'
        ]
    );

//end app.config/application.js