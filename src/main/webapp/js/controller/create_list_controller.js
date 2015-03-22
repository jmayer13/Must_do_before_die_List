categoriesApp.controller("createList", function ($scope, $rootScope, $http) {
    $scope.list = {};
    $scope.create = function () {
        console.log($scope.list);
        $http({
            method: 'POST',
            url: 'addList',
            data: $scope.list
        })
                .success(function () {
                    $scope.list.date = new Date();
                    $rootScope.names.push($scope.list);
                    $scope.addList.$setPristine();
                    $scope.list = {};
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR" + data);
                });
    };
});