//begin document_type_service.js
App.factory('DocumentType', ['$resource', '$rootScope', function ($resource, $rootScope) {

    var urlBase = "/" + $rootScope.appContext + "/document-types/";

    var documentTypeOp = {};

    documentTypeOp.getAll = function (callback) {
        $resource(urlBase, {}, {}).query(callback);
    };

    return documentTypeOp;
    
}
]);

//end document_type_service.js