/* 
 * hecho por alguien, idk
 */

(function (ng){
    

  var mod = ng.module("productoModule",[]);
      mod.constant("productoContext","api/Productos");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/producto/';
          $urlRouterProvider.otherwise('/productoList');
          
          //definir estados
          $stateProvider.state('productoList',{
             url : '/producto',
             views :{
                 'mainView' : {
                     controller : 'productoCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'producto.List.html'
                     
                 }
             }
          });
      }]);     
})(window.angular);