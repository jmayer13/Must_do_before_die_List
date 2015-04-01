categoriesApp.controller("deleteList", function ($scope, $rootScope, $http) {
    $scope.delete = function (list) {
        $http({
            method: 'POST',
            url: 'eraseList',
            data: list
        })
                .success(function () {
                    var index = $rootScope.names.indexOf(list);
                    if (index > -1) {
                        $rootScope.names.splice(index, 1);
                    }
                })
                .error(function (data, status, headers, config) {
                    console.log("ERROR in updateList " + data);
                });
    };
});