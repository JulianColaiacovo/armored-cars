App.filter("ClientFormatter", function ($filter) {
    return function (client) {
        if (client) {
            var document = $filter('DocumentFormatter')(client.document, client.document_type);
            return client.name + ' ' + client.document_type + ' ' + document;
        }
        return "";
    }
});