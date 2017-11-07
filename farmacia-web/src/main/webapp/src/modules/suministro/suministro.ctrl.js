(function (ng) {
    var mod = ng.module("suministroModule");
    mod.constant("suministroContext", "api/Suministros");
    mod.controller('suministroCtrl', ['$scope', '$http', 'suministroContext', '$state',
        function ($scope, $http, suministroContext, $state) {
            $http.get(suministroContext).then(function (response) {
                $scope.suministroRecords = response.data;
            });
            if ($state.params.id !== undefined) {
                $http.get(suministroContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentsuministro = response.data;
                });
            }
        }
    ]);
}
)(angular);