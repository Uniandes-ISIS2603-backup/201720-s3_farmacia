(function (ng){
    var mod = ng.module("pagoModule");
    
    mod.controller("pagoCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data[0];
                 
                
            });
            
            this.pagar = function (id) {
                currentRecord = $scope.currentRecord;
                    if (id === null) {

                    return $http.post(context, currentRecord)
                            .then(function () {
                                $state.go('carritoList');
                            });

                } else {
                    return $http.put(context + "/dar/" + currentRecord.id, currentRecord)
                            .then(function () {
                                $state.go('carritoList');
                            });
                }
                ;
            };
            
    }]);
})(window.angular);

