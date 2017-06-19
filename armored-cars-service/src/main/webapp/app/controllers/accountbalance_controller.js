//begin additional_controller.js
App.controller('AccountBalanceController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored', 'Client', 'AccountBalance',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored, Client, AccountBalance) {

        $scope.initialize = function () {
            $rootScope.section = "LISTS-ACCOUNTBALANCE-VIEW";
            initModals();
            $scope.armoredModalSearch();
            $scope.clientModalSearch();
        };

        $scope.selectArmored = function (armored) {
            $scope.modals.armored.selected = armored;
            $scope.hideArmoredModal();
            if (armored) {
                AccountBalance.getBalanceOfArmored(armored.id, function (response) {
                    $scope.account_balance = response;
                });
            } else {
                $scope.account_balance = null;
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


        $scope.showClientModal = function () {
            $scope.modals.client.visible = true;
        };

        $scope.hideClientModal = function () {
            $scope.modals.client.visible = false;
        };

        $scope.clientModalSearch = function () {
            var client = $scope.modals.client;
            Client.search(client.name, client.document, function (response) {
                $scope.modals.client.items = response;
            });
        };

        $scope.selectClient = function (client) {
            $scope.modals.client.selected = client;
            $scope.hideClientModal();
            if (client) {
                AccountBalance.getBalanceOfClient(client.id, function (response) {
                    $scope.account_balance = response;
                });
            } else {
                $scope.account_balance = null;
            }
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