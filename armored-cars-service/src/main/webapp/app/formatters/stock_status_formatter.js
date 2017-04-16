App.filter("StockStatusFormatter", function () {
    return function (stockStatus) {
        if (stockStatus == 'WITHOUT_STOCK') {
            return "No";
        } else if (stockStatus == 'STOCK_REQUESTED') {
            return "Pedido";
        } else if (stockStatus == 'WITH_STOCK') {
            return "Si";
        } else {
            return stockStatus;
        }
    }
});