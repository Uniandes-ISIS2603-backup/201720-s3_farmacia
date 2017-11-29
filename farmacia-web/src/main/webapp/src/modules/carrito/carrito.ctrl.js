(function (ng){
    var mod = ng.module("carritoModule");
    
    mod.controller("carritoCtrl", ['$scope', '$state', '$stateParams', '$http', 'carritoContext', function ($scope, $state, $stateParams, $http, context){
          $scope.carritoR = {};
            $http.get(context).then(function (response) {
                $scope.carritoR = response.data[0];
            }); 
            
    }]);
})(window.angular);