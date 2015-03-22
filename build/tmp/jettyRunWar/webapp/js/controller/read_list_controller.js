categoriesApp.controller("loadCategories", function ($scope, $rootScope, $http) {
    $http.get("getList")
            .success(function (data, status, headers, config) {
                $rootScope.names = data;
            });
});