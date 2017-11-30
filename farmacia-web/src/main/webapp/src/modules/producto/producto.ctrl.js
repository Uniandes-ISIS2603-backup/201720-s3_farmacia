(function (ng) {
    var mod = ng.module("productoModule");
    mod.controller('productoCtrl', ['$scope', '$state', '$stateParams','$http', 'productoContext',
        function ($scope, $state, $stateParams, $http, context) {
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
            if ($stateParams.productoId !== undefined && $state.params.productoId !== null) {

                id = $stateParams.productoId;

                $http.get(context + '/' + id).then(function (response) {
                    $scope.currentRecord = response.data;
                });
            }
            else{
                $scope.currentRecord = {
                    id: undefined,
                    name:'',
                    descripcion: undefined
                };
                $scope.alerts = [];
            }

            this.createAndUpdate = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id == undefined) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('productoList');
                            });

                } else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('productoList');
                            });
                }
                ;
            };
            
            this.addCarrito = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id == undefined) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('anadirProducto');
                            });

                } else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('anadirProducto');
                            });
                }
                ;
            };
            this.deleteProducto = function(record) {
                return $http.delete(context + "/" + record.id)
                            .then(function () {
                                var index = $scope.records.indexOf(record);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                })
            };
            this.addToCart=function(id){
                $http.get('api/carrito/').then(function(response){
                    if(response==undefined){
                        var datosCarrote ={
                            	totalCarrito:0
                        };
                        $http.post('api/carrito/',datosCarrote).then(function () {
                                $state.go('productoList');
                            });
                    }else{
                          return $http.put('api/carrito/'+id).then(function(){
                            $state.go('productoList');
                        })
                    }
                })
            }
            
            this.deleteCarrito = function(idCliente) {
                console.log(idCliente)
                $http.get('api/carrito/').then(function (response) {
                $scope.carritoR = response.data[0];
                
            })
                console.log(idCliente)
                $scope.carritoR.idCliente = idCliente;
                return $http.delete(context + "/" + 1)
                        .then(function () {
                             $state.go('carritoList');
                });
            };
        }
    ]);
}
)(angular);