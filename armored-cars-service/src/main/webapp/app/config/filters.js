App.filter('startFrom', function () {
    return function (input, start) {
        if (input == undefined) {
            return input;
        }
        start = +start; //parse to int
        return input.slice(start);
    }
});
