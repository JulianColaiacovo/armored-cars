//begin bill_controller.js
App.controller('BillController', ['$rootScope', '$scope', '$filter', '$location', '$routeParams', '$http', 'Aliquot', 'Bill', 'BillTypeCode', 'Currency', 'Armored', 'Client',
    function ($rootScope, $scope, $filter, $location, $routeParams, $http, Aliquot, Bill, BillTypeCode, Currency, Armored, Client) {

        $scope.initialize = function () {
            $scope.isSaving = false;
            $scope.bill = {
                bill_type_code: "BILL_A"
            };
            $scope.formattedData = {};
            initModals();
            setSection();
            loadAliquots();
            loadBillTypeCodes();
            loadCurrencies();
            loadData();
            $scope.armoredModalSearch();
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
            if ($scope.form.$valid && $scope.modals.armored.selected) {
                $scope.isSaving = true;
                $scope.bill.armored_id = $scope.modals.armored.selected.id;
                $scope.bill.bill_to_id = $scope.client.id;
                $scope.bill.apply_bill_id = getBillSelectedId();
                var billNumber = $scope.formattedData.billNumber.replace("-", "");
                $scope.bill.number = Number(billNumber);
                Bill.save($scope.bill, onSaveOk, onSaveError);
            } else {
                angular.forEach($scope.form.$error, function (controls, errorName) {
                    angular.forEach(controls, function (control) {
                        control.$setDirty();
                    });
                });
            }
        };

        $scope.updateBillNumber = function () {
            setDefaultBillNumber();
        };

        $scope.updateModalBillsToApply = function () {
            BillTypeCode.getPossibleApplies($scope.bill.bill_type_code, function (response) {
                $scope.modals.bill.bill_type_codes = response;
                if (response.length > 0) {
                    $scope.modals.bill.bill_type_code = response[0]['id'];
                }
            });
        };

        $scope.isCreditNote = function () {
            return $scope.bill.bill_type_code.startsWith('CREDIT_NOTE_');
        };

        $scope.selectArmored = function (armored) {
            $scope.modals.armored.selected = armored;
            $scope.hideArmoredModal();
            searchArmoredClient(armored);
        };

        $scope.armoredModalSearch = function () {
            var armored = $scope.modals.armored.selected | {};
            Armored.search(armored.code, armored.brand, function (response) {
                $scope.modals.armored.items = response;
            });
        };

        $scope.showArmoredModal = function () {
            $scope.modals.armored.visible = true;
            BillTypeCode.getAllEnabled(function (response) {
                $scope.modals.bill.bill_type_codes = response;
            });
        };

        $scope.hideArmoredModal = function () {
            $scope.modals.armored.visible = false;
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
            searchBillArmored(bill);
        };

        var getBillSelectedId = function () {
            if ($scope.modals.bill.selected) {
                return $scope.modals.bill.selected.id;
            }
            return null;
        };

        var searchBillArmored = function (bill) {
            if (bill && bill.armored_id) {
                Armored.get(bill.armored_id, function (response) {
                    $scope.selectArmored(response);
                });
            } else {
                $scope.selectArmored(null);
            }
        };

        var searchArmoredClient = function (armored) {
            if (armored && armored.id) {
                Armored.getBillToClient(armored.id, function (response) {
                    $scope.client = response;
                });
            } else {
                $scope.client = null;
            }
        };

        var initModals = function () {
            $scope.modals = {
                "armored": {
                    "visible": false,
                    "selected": null
                },
                "bill": {
                    "visible": false,
                    "selected": null
                }
            };
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

        var loadArmored = function (armoredId) {
            if (armoredId != null) {
                Armored.get(armoredId, function (response) {
                    $scope.modals.armored.selected = response;
                });
            } else {
                $scope.modals.armored.selected = null;
            }
        };

        var loadApplyBill = function (billId) {
            if (billId) {
                Bill.get(billId, function (response) {
                    $scope.modals.bill.selected = response;
                });
            } else {
                $scope.modals.bill.selected = null;
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
                    loadArmored($scope.bill.armored_id);
                    loadApplyBill($scope.bill.apply_bill_id);
                });
            }
        };
        
        var setDefaultBillNumber = function () {
            Bill.getNextNumber($scope.bill.bill_type_code, function (billNumber) {
                setBillNumber(billNumber);
            });
        };

        var setBillNumber = function (billNumber) {
            $scope.formattedData.billNumber = $filter('BillNumberFormatter')(billNumber);
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