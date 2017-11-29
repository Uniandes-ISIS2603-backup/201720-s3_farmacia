/* 
 * hecho por alguien, idk
 */
(function(ng) {
    
    var mod = ng.module("OrdenRotacionModule");
    
    mod.controller("OrdenRotacionCtrl",['$scope', '$state', '$stateParams', '$http', 'OrdenRotacionContext', function ($scope, $state, $stateParams, $http, context) {
            
            //si mal no entiendo, uno primero hace los casos de lo que le podria llegar para ir cambiando 
            //el scope, luego define funciones que si se llaman en en el modulo y las views.
            
            $scope.records = {};
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            });
            
            //para crear, no me debe llegar nada
            if($stateParams.ordenID === null && $stateParams.ordenID === undefined)
            {
                //construyo un objeto y lo guardo en el current record, es decir, lo que estoy manoseando :v
                //id undefined porque aun no existe
                $scope.currentRecord = {
                    id : undefined,
                    justificacion : '',
                    encargado: ''
                };
                
                $scope.alerts = [];
            }
            
            //funciones para hacer cositas y eso
            
            //ahora, crea y elimina
            this.create = function(codigo) {
                currentRecord = $scope.currentRecord;
               
                return $http.post(context, currentRecord).then(function (){
                    $state.go("OrdenRotacionList");
                });
            };
            
            this.delete = function(codigo){
                
                return $http.delete(context + "/" + codigo.id)
                            .then(function () {
                                // $http.delete es una promesa
                                 //cuando termine bien, cambie de estado
                                var index = $scope.records.indexOf(codigo); //el problema debe estar aca
                                if (index > -1) {
                                    $scope.records.splice(index, 1);
                                }
                          });
                //$http.delete(context + "/" + codigo);
                //$state.reload('itemList');
            };
            
    }]);
})(window.angular);



