categoriesApp.controller("readItem", function ($scope, data, $rootScope, $http) {
    $scope.shuffleArray = function () {
        var size = $rootScope.items.length;
        var i = size;
        var x;
        while (i > 0) {
            var randomIndex = Math.floor(Math.random() * size);
            i = i - 1;
            x = $rootScope.items[randomIndex];
            $rootScope.items[randomIndex] = $rootScope.items[i];
            $rootScope.items[i] = x;
        }
    };

    $rootScope.items = {};
    var update = function ( ) {
        $http({
            method: 'POST',
            url: 'getItems',
            data: data.getSelectedList()
        }).success(function (data, status, headers, config) {
            $rootScope.items = data;
        });
    };

    $scope.$watch(function ( ) {
        return data.getSelectedList();
    }, update);
});