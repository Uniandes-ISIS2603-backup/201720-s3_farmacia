(function (ng){
    var mod = ng.module("carritoModule");
    
    mod.controller("carritoCtrl", ['$scope', '$state', '$stateParams', '$http', 'carritoContext', function ($scope, $state, $stateParams, $http, context){
          $scope.carritoR = {};
            $http.get(context).then(function (response) {
                $scope.carritoR = response.data[0];

            }); 
            this.deleteRecord = function(carrito) {
                return $http.delete(context + "/" +1)
                            .then(function () {
                                var index = $scope.records.indexOf(1);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                            });
            };
    }]);
})(window.angular);