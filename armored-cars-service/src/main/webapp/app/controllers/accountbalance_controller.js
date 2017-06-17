//begin additional_controller.js
App.controller('AccountBalanceController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored', 'Client', 'AccountBalance',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored, Client, AccountBalance) {

        $scope.initialize = function () {
            $rootScope.section = "ACCOUNTBALANCE-VIEW";
            $scope.account_balance = {};
            initModals();
        };

        $scope.selectArmored = function (armored) {
            $scope.modals.armored.selected = armored;
            $scope.hideArmoredModal();
            if (armored) {
                AccountBalance.getBalanceOfArmored(armored.id, function (response) {
                    $scope.account_balance = response;
                });
            } else {
                $scope.account_balance = [];
            }
        };

        $scope.armoredModalSearch = function () {
            var armored = $scope.modals.armored || {};
            Armored.search(armored.code, armored.brand, function (response) {
                $scope.modals.armored.items = response;
            });
        };

        $scope.showArmoredModal = function () {
            $scope.modals.armored.visible = true;
        };

        $scope.hideArmoredModal = function () {
            $scope.modals.armored.visible = false;
        };

        var initModals = function () {
            $scope.modals = {
                "client": {
                    "visible": false,
                    "selected": null
                },
                "armored": {
                    "visible": false,
                    "selected": null
                }
            };
        };

        $scope.initialize();

    }]);
//end additional_controller.js