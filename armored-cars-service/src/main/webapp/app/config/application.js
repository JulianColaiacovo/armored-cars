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
            'datetimepicker'
        ]
    ).config([
        'datetimepickerProvider',
        function (datetimepickerProvider) {
            datetimepickerProvider.setOptions({
                locale: 'es'
            });
        }
    ]);

//end app.config/application.js