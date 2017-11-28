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
            


            this.deleteProducto = function(record) {
                return $http.delete(context + "/" + record.id)
                            .then(function () {
                                var index = $scope.records.indexOf(record);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                })

           
            }
            
            
            }
        
    ]);
}
)(angular);