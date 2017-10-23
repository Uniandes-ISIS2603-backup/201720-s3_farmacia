/* 
 * hecho por alguien, idk
 */
(function(ng) {
    
    var mod = ng.module("ItemModule");
    
    mod.controller("ItemCtrl",['$scope', '$state', '$stateParams', '$http', 'ItemInventarioContext', function ($scope, $state, $stateParams, $http, context) {
            
            //si mal no entiendo, uno primero hace los casos de lo que le podria llegar para ir cambiando 
            //el scope, luego define funciones que si se llaman en en el modulo y las views.
            
            $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
            
            //para crear, no me debe llegar nada
            
            if($stateParams.itemID === null && $stateParams.itemID === undefined)
            {
                //construyo un objeto y lo guardo en el current record, es decir, lo que estoy manoseando :v
                //id undefined porque aun no existe
                $scope.currentRecord = {
                    id : undefined,
                    codigo : ''
                    //TODO, AGREGAR FECHA, COMO MANEJO ESO?!
                };
                
                $scope.alerts = [];
            }
            
            //funciones para hacer cositas y eso
            
            //por ahora, solo crea
            this.create = function(id) {
                currentRecord = $scope.currentRecord;
                
                return $http.post(context, currentRecord).then(function (){
                    $state.go("itemList");
                });
            };
            
    }]);
})(window.angular);



