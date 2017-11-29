(function (ng){
    var mod = ng.module("pagoModule");
    
    mod.controller("pagoCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoContext', function ($scope, $state, $stateParams, $http, context){
          $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data[0];
                 
                
            });
            
    }]);
})(window.angular);

