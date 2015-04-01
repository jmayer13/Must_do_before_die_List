categoriesApp.controller("deleteItem", function ($scope, $rootScope, data, $http) {
    $scope.delete = function (item) {
        var delet = {
            list: data.getSelectedList(),
            item: item
        };
        $http({
            method: 'POST',
            url: 'eraseItem',
            data: delet
        })
                .success(function () {
                    var index = $rootScope.items.indexOf(item);
                    if (index > -1) {
                        $rootScope.items.splice(index, 1);
                    }
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR in updateList " + data);
                });
    };
});