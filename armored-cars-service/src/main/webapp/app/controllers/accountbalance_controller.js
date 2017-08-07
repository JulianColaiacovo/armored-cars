//begin additional_controller.js
App.controller('AccountBalanceController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Armored', 'Client', 'AccountBalance',
    function ($rootScope, $scope, $location, $routeParams, $http, Armored, Client, AccountBalance) {

        var loadData = function () {
            var armoredId = $location.search().armored_id;
            var clientId = $location.search().client_id;
            if (armoredId) {
                Armored.get(armoredId, function (armored) {
                    $scope.selectArmored(armored);
                });
            } else if (clientId) {
                Client.get(clientId, function (client) {
                    $scope.selectClient(client);
                });
            }
        };

        $scope.initialize = function () {
            $rootScope.section = "LISTS-ACCOUNTBALANCE-VIEW";
            initModals();
            $scope.armoredModalSearch();
            $scope.clientModalSearch();
            loadData();
        };

        $scope.selectArmored = function (armored) {
            $scope.modals.armored.selected = armored;
            $scope.hideArmoredModal();
            if (armored) {
                $location.search('armored_id', armored.id);
                AccountBalance.getBalanceOfArmored(armored.id, function (response) {
                    $scope.account_balance = response;
                });
            } else {
                $location.search('armored_id', null);
                $scope.account_balance = null;
            }
        };

        $scope.armoredModalSearch = function () {
            var armored = $scope.modals.armored || {};
            Armored.search(armored.code, armored.brand, armored.client_name, function (response) {
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
                $location.search('client_id', client.id);
                AccountBalance.getBalanceOfClient(client.id, function (response) {
                    $scope.account_balance = response;
                });
            } else {
                $location.search('client_id', null);
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