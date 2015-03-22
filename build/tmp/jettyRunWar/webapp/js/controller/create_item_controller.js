categoriesApp.controller("createItem", function ($scope, data, $rootScope, $http) {


    $scope.create = function () {
        var add = {
            list: data.getSelectedList(),
            item: $scope.item
        };
        console.log("createItem list:" + add.list + " item " + $scope.item);
        $http({
            method: 'POST',
            url: 'addItem',
            data: add
        })
                .success(function () {
                    var date = new Date();
                    var day = date.getDate();
                    var month = date.getMonth();
                    var year = date.getFullYear();
                    $scope.item.date = year + "-" + month + "-" + day;
                    $rootScope.items.push($scope.item);
                    $scope.itemForm.$setPristine();
                    $scope.item = {};
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR" + data);
                });
    };
});