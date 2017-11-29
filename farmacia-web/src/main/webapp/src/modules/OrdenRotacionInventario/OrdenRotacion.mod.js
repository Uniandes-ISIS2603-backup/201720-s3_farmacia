/* 
 *Hecho por alguien, idk
 */
(function (ng){

    

  var mod = ng.module("OrdenRotacionModule",['ui.router']);

  var mod = ng.module("OrdenRotacionModule",[]);

      mod.constant("OrdenRotacionContext","api/OrdenDeRotacionDeInventario");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/OrdenRotacionInventario/';
          $urlRouterProvider.otherwise('/OrdenRotacionList');
          
          //definir estados
          $stateProvider.state('OrdenRotacionList',{
             url : '/OrdenRotacion',
             views :{
                 'mainView' : {
                     controller : 'OrdenRotacionCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'OrdenRotacion.list.html'
                     
                 }
             }
          }).state('OrdenRotacionCreate', {
              url : '/OrdenRotacion/Create', 
              views : {
                  'mainView' : {
                          controller: 'OrdenRotacionCtrl',
                          controllerAs : 'ctrl',
                          templateUrl: basePath + 'OrdenRotacion.create.html'
                      }
              }
          });
      }]);
        
        
        
})(window.angular);


