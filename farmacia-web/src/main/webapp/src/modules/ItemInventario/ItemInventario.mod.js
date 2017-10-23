/* 
 * hecho por alguien, idk
 */

(function (ng){
    

  var mod = ng.module("ItemModule",[]);
      mod.constants("ItemInventarioContext","api/ItemInventario");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/ItemInventario/';
          $urlRouterProvider.otherwise('/ItemList');
          
          //definir estados
          $stateProvider.state('itemList',{
             url : '/itemInventario',
             views :{
                 'mainView' : {
                     controller : 'ItemCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'item.List.html'
                     
                 }
             }
          }).state('itemCreate', {
              url : '/itemInventario/Create', 
              views : {
                  'mainView' : {
                          controller: 'ItemCtrl',
                          controllerAs : 'ctrl',
                          templateUrl: basePath + 'item.create.html'
                      }
              }
          });
      }]);
        
        
        
})(window.angular);


