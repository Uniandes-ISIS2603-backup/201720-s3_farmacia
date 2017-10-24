(function (ng) {
    var mod = ng.module("productoModule");
    mod.constant("productosContext", "api/Productos");
    mod.controller('productoCtrl', ['$scope', '$http', 'productoContext', '$state',
        function ($scope, $http, productoContext, $state) {
            $http.get(productoContext).then(function (response) {
                $scope.productoRecords = response.data;
            });
            if ($state.params.id !== undefined) {
                $http.get(productoContext + '/' + $state.params.id).then(function (response) {
                    $scope.currentproducto = response.data;
                });
            }
        }
    ]);
}
)(angular);