categoriesApp.controller("updateItem", function ($scope, data, $http) {
    $scope.itemNew = {};
    $scope.showEditItem = false;

    $scope.edit = function (oldItem) {
        var dataItem = {
            list: data.getSelectedList(),
            oldItem: oldItem,
            newItem: $scope.itemNew

        };
        $http({
            method: 'POST',
            url: 'editItem',
            data: dataItem
        })
                .success(function () {
                    $scope.changeShowEditItem();
                    oldItem.text = $scope.itemNew.text;
                    oldItem.date = new Date();
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR in updateList " + data);
                });


        $scope.changeShowEditItem = function () {
            $scope.showEditItem = !$scope.showEditItem;
        };
    };
});