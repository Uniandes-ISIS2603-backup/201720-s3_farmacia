(function (ng){
    var mod = ng.module("clienteModule");
    
    mod.controller("clienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'clienteContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });  
            if ($stateParams.clienteId !== null && $stateParams.clienteId !== undefined) {
                id = $stateParams.clienteId;
                $http.get(context + "/" + id)
                        .then(function (response) {
                            $scope.currentRecord = response.data;
                        });
            } else {
                $scope.currentRecord = {
                    id: undefined,
                    name: '',
                    cedula : undefined,
                };

                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id == null || id==undefined) {

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
            };
            this.findRecord = function(id){
                $http.get(context+"/"+ id)
                        .then(function(response) {
                           $scope.records = [ response.data ];
                           $state.go('clienteList');
                });
            };
    }]);
})(window.angular);

