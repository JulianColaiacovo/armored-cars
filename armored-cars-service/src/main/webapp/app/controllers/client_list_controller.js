//begin client_list_controller.js
App.controller('ClientListController', ['$rootScope', '$scope', '$location', '$http', 'Client',
    function ($rootScope, $scope, $location, $http, Client) {

        $scope.initialize = function () {
            $rootScope.section = "CLIENT-LIST";
            $scope.page_size = 15;
            $scope.current_page = 1;
            $scope.loadClients();
        };

        $scope.loadClients = function () {
            Client.getAll(function (response) {
                $scope.clients = response;
            });
        };

        $scope.initialize();

        $scope.view = function (id) {
            $location.path("clients/view/" + id);
        };

        $scope.edit = function (id) {
            $location.path("clients/edit/" + id);
        };

        $scope.delete = function (client) {
            if (confirm("Esta seguro que desea eliminar el cliente " + client.name + "?")) {
                Client.delete(client.id, onDeleteOk, onDeleteError);
            }
        };

        $scope.uploadFile = function () {
            if ($scope.validateBillDate()) {
                var fd = new FormData();
                //Take the first selected file
                fd.append("file", $scope.selectedFile);

                $http.post("/" + $rootScope.appContext + "/reprocess/file", fd, {
                    withCredentials: true,
                    headers: {'Content-Type': undefined},
                    params: {
                        "countryCode": $rootScope.countryCode,
                        "user": $rootScope.logged_user,
                        "billDate": $scope.billDate
                    },
                    transformRequest: angular.identity
                }).success(function (data, status, headers, config) {
                    $location.path("reprocess/" + data.file_id)
                }).error("");

            } else {
                console.log("invalid date, not going to upload")
            }


        };

        var onDeleteOk = function () {
            $location.path('/clients/');
        };

        var onDeleteError = function (response) {
            if (response.status != 200) {
                $rootScope.globalError = 'Error deleting client';
            }
        };

    }]);
//end client_list_controller.js