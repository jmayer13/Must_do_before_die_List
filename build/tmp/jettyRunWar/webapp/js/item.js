/* addItem
 * removeItem
 * invertActive
 * editItem
 * getItems
 */

function loadItems($scope, $http) {
    $http.get("/getItems")
            .success(function (data, status, headers, config) {
                $scope.items = data;
            });
}

function addItem($scope, $http) {
    $scope.item = {};
    $scope.submitAddItemForm = function () {
        $http({
            method: 'POST',
            url: '/addItem',
            data: $scope.item
        });
    };
}

function editItem($scope, $http) {
    $scope.create = function () {
        $http({
            method: 'POST',
            url: '/addList',
            data: {"oldItem": $scope.oldItem, "newItem": $scope.newItem}
        });
    };
}
function deleteItem($scope, $http) {
    $scope.item = {};
    $scope.create = function () {
        $http({
            method: 'POST',
            url: '/eraseItem',
            data: $scope.item
        });
    };
}

