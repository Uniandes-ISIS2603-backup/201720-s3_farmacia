(function (ng) {
    var mod = ng.module("itemRawModule");
    mod.constant("itemRawContext", "api/Items");
    mod.controller('itemRawCtrl', ['$scope', '$http', 'itemRawContext', '$state',
        function ($scope, $http, itemRawContext, $state) {
            $http.get(itemRawContext).then(function (response) {
                $scope.itemRecords = response.data;
            });
            if ($state.params.id !== undefined) {
                $http.get(itemContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentitem = response.data;
                });
            }
        }
    ]);
}
)(angular);