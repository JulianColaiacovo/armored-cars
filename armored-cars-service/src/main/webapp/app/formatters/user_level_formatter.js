App.filter("UserLevelFormatter", function () {
    return function (userLevel) {
        if (userLevel == 'WORKSHOP') {
            return "Taller";
        } else if (userLevel == 'ACCOUNTING') {
            return "Contador";
        } else if (userLevel == 'FINANCIAL') {
            return "Administrador";
        }
        return userLevel;
    }
});