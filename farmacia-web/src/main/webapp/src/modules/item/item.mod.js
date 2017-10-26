/* 
 * hecho por alguien, idk
 */

(function (ng){
    

  var mod = ng.module("itemRawModule",[]);
      mod.constant("itemRawContext","api/Items");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/item/';
          $urlRouterProvider.otherwise('/itemRawLista');
          
          //definir estados
          $stateProvider.state('itemRawLista',{
             url : '/items',
             views :{
                 'mainView' : {
                     controller : 'itemRawCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'item.List.html'
                     
                 }
             }
          });
      }]);     
})(window.angular);