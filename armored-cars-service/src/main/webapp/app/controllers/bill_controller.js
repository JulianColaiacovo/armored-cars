//begin bill_controller.js
App.controller('BillController', ['$rootScope', '$scope', '$filter', '$location', '$routeParams', '$http', 'Aliquot', 'Bill', 'BillTypeCode', 'Currency', 'Armored', 'Client',
    function ($rootScope, $scope, $filter, $location, $routeParams, $http, Aliquot, Bill, BillTypeCode, Currency, Armored, Client) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.bill = {};
            setSection();
            loadAliquots();
            loadBillTypeCodes();
            loadCurrencies();
            loadData();
        };

        $scope.isNew = function () {
            return $rootScope.section === "BILL-ADD";
        };

        $scope.canEditData = function () {
            return $scope.isNew() || $rootScope.section === "BILL-EDIT";
        };

        $scope.updateVatAndTotal = function () {
            if (isBillTypeA()) {
                $scope.bill.vat_amount = $scope.bill.taxed_amount * $scope.bill.aliquot / 100;
                $scope.bill.total_amount = $scope.bill.vat_amount + $scope.bill.taxed_amount + $scope.bill.untaxed_amount;
            } else {
                $scope.bill.vat_amount = $scope.bill.taxed_amount - ($scope.bill.taxed_amount * 100) / ($scope.bill.aliquot + 100);
                $scope.bill.total_amount = $scope.bill.taxed_amount + $scope.bill.untaxed_amount;
            }
        };

        $scope.save = function () {
            if ($scope.form.$valid) {
                $scope.isSaving = true;
                Bill.save($scope.bill, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.searchArmoredClient = function () {
            var armoredId = $scope.bill.armored_id;
            if (armoredId) {
                Armored.getBillToClient(armoredId, function (response) {
                    $scope.client = response;
                });
            } else {
                $scope.client = null;
            }
        };

        $scope.updateBillNumber = function () {
            setDefaultBillNumber();
        };

        $scope.isCreditNote = function () {
            return $scope.bill.bill_type_code.startsWith('CREDIT_NOTE_');
        };

        var loadBillClient = function (clientId) {
            if (clientId) {
                Client.get(clientId, function (response) {
                    $scope.client = response;
                });
            } else {
                $scope.client = null;
            }
        };

        var isBillTypeA = function () {
            return $scope.bill.bill_type_code.endsWith('_A');
        };

        var loadData = function () {
            if ($scope.isNew()) {
                $scope.bill = {
                    "date": new Date(),
                    "aliquot": 21,
                    "taxed_amount": 1,
                    "untaxed_amount": 0,
                    "total_amount": 0,
                    "vat_amount": 0,
                    "currency_code": "ARS",
                    "bill_type_code": "BILL_A"
                };
                setDefaultBillNumber();
                $scope.updateVatAndTotal();
            } else {
                Bill.get($routeParams.bill_id, function (response) {
                    response.date = new Date(response.date);
                    $scope.bill = response;
                    setBillNumber(response.number);
                    loadBillClient($scope.bill.bill_to_id);
                });
            }
        };
        
        var setDefaultBillNumber = function () {
            Bill.getNextNumber($scope.bill.bill_type_code, function (billNumber) {
                setBillNumber(billNumber);
            });
        };

        var setBillNumber = function (billNumber) {
            $scope.bill.number = $filter('BillNumberFormatter')(billNumber);
        };

        var setSection = function () {
            if ($routeParams.action == "view") {
                $rootScope.section = "BILL-VIEW"
            } else if ($routeParams.action == "add") {
                $rootScope.section = "BILL-ADD"
            } else if ($routeParams.action == "edit") {
                $rootScope.section = "BILL-EDIT"
            }
        };

        var onSaveOk = function (response) {
            $scope.isSaving = false;
            $location.path('/bills/view/' + response.id);
        };

        var onSaveError = function (response) {
            $scope.isSaving = false;
            if (response.status != 200) {
                $rootScope.globalError = 'Error al guardar la factura';
            }
        };

        var loadAliquots = function () {
            Aliquot.getAll(function (response) {
                $scope.aliquots = response;
                $scope.bill.aliquot = $scope.bill.aliquot || 21;
            });
        };

        var loadBillTypeCodes = function () {
            BillTypeCode.getAllEnabled(function (response) {
                $scope.bill_type_codes = response;
            });
        };

        var loadCurrencies = function () {
            Currency.getAllEnabled(function (response) {
                $scope.currencies = response;
            });
        };

        $scope.initialize();

    }]);
//end bill_controller.js