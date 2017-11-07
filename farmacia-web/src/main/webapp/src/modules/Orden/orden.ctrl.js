(function (ng) {
    var mod = ng.module("ordenModule");
    mod.controller("ordenCtrl",['$scope','$state','$stateParams', '$http', 'ordenContext', function($scope, $state, $stateParams, $http, context){
           
            $scope.records = {};
            $http.get(context).then(function(response) {
                $scope.records = response.data;
            });
            
            if($stateParams.ordenId !== null && $stateParams.ordenId !== undefined){
                
                id = $stateParams.ordenId;
                
                $http.get(context + "/" + id).then(function(response){
                    $scope.currentRecord = response.data;
                });
                
            } else {
                $scope.currentRecord = {
                    id: undefined,
                    name: '',
                    tipo: ''
                };
                $scope.alerts = [];
            }
            
            this.create = function(id){
                currentRecord = $scope.currentRecord;
                
                return $http.post(context, currentRecord).then(function(){
                    $state.go("ordenList");
                });
            };
    }])
})(window.angular);


