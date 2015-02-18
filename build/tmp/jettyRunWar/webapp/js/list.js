
function loadCategories($scope, $http) {
    $http.get("/getList")
            .success(function (data, status, headers, config) {
                $scope.names = data;
            });
}

function addList($scope, $http) {
    $scope.list = {};
    $scope.create = function () {
        $http({
            method: 'POST',
            url: '/addList',
            data: $scope.list
        });
    };
}

function editList($scope, $http) {
    $scope.list = {};
    $scope.create = function () {
        $http({
            method: 'POST',
            url: '/editList',
            data: {"oldList": $scope.oldList, "newList": $scope.newList}
        });
    };
}
function deleteList($scope, $http) {
    $scope.list = {};
    $scope.create = function () {
        $http({
            method: 'POST',
            url: '/eraseList',
            data: $scope.list
        });
    };
} 