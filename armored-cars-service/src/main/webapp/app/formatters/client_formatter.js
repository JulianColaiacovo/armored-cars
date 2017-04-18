App.filter("ClientFormatter", function () {
    return function (client) {
        if (client) {
            return client.name + ' ' + client.document_type + '-' + client.document;
        }
        return "";
    }
});