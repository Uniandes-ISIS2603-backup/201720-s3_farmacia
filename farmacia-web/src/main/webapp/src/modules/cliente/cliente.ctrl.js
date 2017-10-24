(function (ng){
    var mod = ng.module("clienteModule");
    
    mod.controller("clienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            // carga los clientes
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });  
            // el controlador recibió una  cedula de un cliente (un ID)  ??
            // revisa los parámetros
            if ($stateParams.clienteId !== null && $stateParams.clienteId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.clienteId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un ID
            } else {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    cedula : undefined,
                };

                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id == null) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('clienteList');
                            });

                } else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('clienteList');
                            });
                }
                ;
            };
            
            this.deleteRecord = function(record) {
                return $http.delete(context + "/" + record.id)
                            .then(function () {
                                var index = $scope.records.indexOf(record);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                });
            }
    }]);
})(window.angular);

