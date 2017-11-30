(function (ng){
    var mod = ng.module("carritoModule");
    
    mod.controller("carritoCtrl", ['$scope', '$state', '$stateParams', '$http', 'carritoContext', function ($scope, $state, $stateParams, $http, context){
          $scope.carritoR = {};
            $http.get(context).then(function (response) {
                $scope.carritoR = response.data[0];
                
            }); 
            
             this.deleteCarrito = function(idCliente) {
                $http.get('api/carrito/').then(function (response) {
                $scope.carritoR = response.data[0];
                $scope.carritoR.idCliente = idCliente;
                $http.delete(context + "/" + 1)
                        .then(function () {
                             $state.go('carritoList');
                });
            });
                
            };
    }]);
})(window.angular);