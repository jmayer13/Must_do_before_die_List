categoriesApp.controller("updateList", function ($scope, $http) {
    $scope.list = {};
    $scope.showEditList = false;
    $scope.edit = function (oldList) {
        var dataList = {
            oldList: oldList,
            newList: $scope.list
        };

        $http({
            method: 'POST',
            url: 'editList',
            data: dataList
        })
                .success(function () {
                    $scope.showEdit();
                    oldList.name = $scope.list.name;
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR in updateList " + data);
                });
    };
    $scope.showEdit = function () {
        $scope.showEditList = !$scope.showEditList;
    };
});