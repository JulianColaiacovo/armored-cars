App.filter("DocumentFormatter", function () {
    return function (document, documentType) {
        if (documentType === 'CUIL' || documentType === 'CUIT') {
            return document.replace(/(\d{2})(\d{7,8})(\d)/, '$1-$2-$3')
        }
        return document;
    }
});