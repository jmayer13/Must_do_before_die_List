categoriesApp.controller("selectList", function ($scope, data, $rootScope, $http) {
    $rootScope.showItems = false;
    $scope.select = function (b) {
        $("* .list.active").removeClass("active");
        data.setSelectedList(b);
        $rootScope.showItems = true;
    };
});