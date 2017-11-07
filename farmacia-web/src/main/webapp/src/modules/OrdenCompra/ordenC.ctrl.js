(function (ng){
    var mod = ng.module("OrdenCModule");
    
    mod.controller("ordenCCtrl", ['$scope', '$state', '$stateParams', '$http', 'ordenCContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });  
            if ($stateParams.id !== null && $stateParams.id !== undefined) {
                id = $stateParams.id;
                $http.get(context + "/" + id)
                        .then(function (response) {
                            $scope.currentRecord = response.data;
                        });
                } else {
                $scope.currentRecord = {
                    id: undefined,
                    name: '',
                    fecha : '',
                    costoTotal:undefined,
                    idCliente:undefined
                };

                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id == null) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('ordenCList');
                            });

                } else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('ordenCList');
                            });
                }
                ;
            };
            
    }]);
})(window.angular);

