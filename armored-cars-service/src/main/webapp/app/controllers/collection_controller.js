//begin collection_controller.js
App.controller('CollectionController', ['$rootScope', '$scope', '$location', '$routeParams', '$http', 'Bill', 'Currency', 'Collection', 'Client', 'BillTypeCode',
    function ($rootScope, $scope, $location, $routeParams, $http, Bill, Currency, Collection, Client, BillTypeCode) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            initModals();
            setSection();
            loadData();
            loadModalBillTypeCodes();
        };

        $scope.isNew = function () {
            return $rootScope.section === "COLLECTION-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "COLLECTION-EDIT";
        };

        $scope.updateTotal = function () {
            $scope.collection.total_amount = $scope.collection.base_amount + $scope.collection.gain_amount +
                $scope.collection.vat_amount + $scope.collection.suss_amount + $scope.collection.iibb_amount;
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                $scope.collection.bill_id = $scope.modals.bill.selected.id;
                $scope.collection.client_id = $scope.modals.client.selected.id;
                Collection.save($scope.collection, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
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
        };

        $scope.showBillModal = function () {
            $scope.modals.bill.visible = true;
        };

        $scope.hideBillModal = function () {
            $scope.modals.bill.visible = false;
        };

        $scope.billModalSearch = function () {
            Bill.search($scope.modals.bill.bill_type_code, function (response) {
                $scope.modals.bill.items = response;
            });
        };

        $scope.selectBill = function (bill) {
            $scope.modals.bill.selected = bill;
            $scope.hideBillModal();
        };

        var loadModalBillTypeCodes = function () {
            BillTypeCode.getBillTypeCodesCollection(function (response) {
                $scope.modals.bill.bill_type_codes = response;
                if (response.length > 0) {
                    $scope.modals.bill.bill_type_code = response[0]['id'];
                }
            });
        };

        var initModals = function () {
            $scope.modals = {
                "client": {
                    "visible": false,
                    "selected": null
                },
                "bill": {
                    "visible": false,
                    "selected": null
                }
            };
        };

        var loadData = function () {
            if ($scope.isNew()) {
                $scope.collection = {
                    "base_amount": 0,
                    "gain_amount": 0,
                    "vat_amount": 0,
                    "suss_amount": 0,
                    "iibb_amount": 0,
                    "total_amount": 0
                };
            } else {
                Collection.get($routeParams.collection_id, function (collectionResponse) {
                    collectionResponse.date = new Date(collectionResponse.date);
                    $scope.collection = collectionResponse;
                    if (collectionResponse.bill_id) {
                        Bill.get(collectionResponse.bill_id, function (billResponse) {
                            billResponse.date = new Date(billResponse.date);
                            $scope.modals.bill.selected = billResponse;
                        });
                    }
                    Client.get(collectionResponse.client_id, function (clientResponse) {
                        $scope.modals.client.selected = clientResponse;
                    });
                });
            }
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "COLLECTION-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "COLLECTION-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "COLLECTION-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/collections/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar la cobranza';
            }
        };

        $scope.initialize();

    }]);
//end collection_controller.js