(function (ng){
    var mod = ng.module("facturaModule");
    
    mod.controller("facturaCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });  
            if ($stateParams.facturaId !== null && $stateParams.facturaId !== undefined) {
                id = $stateParams.facturaId;
                $http.get(context + "/clientes/" + id + "/facturas")//revisar URL
                        .then(function (response) {
                            $scope.currentRecord = response.data;
                        });
            } else {
                $scope.currentRecord = {
                    id: undefined,
                    name: '',
                };
                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if (id == null) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('facturaList');
                            });

                   } else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('facturaList');
                            });
                }
                ;
            };
            
            this.deleteRecord = function(record) {
                 return $http.delete(context + "/" + 1)
                            .then(function () {
                                var index = $scope.records.indexOf(record);
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                            });
            }
    }]);
})(window.angular);
