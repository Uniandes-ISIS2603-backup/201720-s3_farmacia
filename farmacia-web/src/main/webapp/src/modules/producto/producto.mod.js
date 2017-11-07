/* 
 * hecho por alguien, idk
 */

(function (ng){
    

  var mod = ng.module("productoModule",[]);
      mod.constant("productoContext","api/Productos");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/producto/';
          $urlRouterProvider.otherwise('/view');
          
          //definir estados
          $stateProvider.state('productoList',{
             url : '/productos',
             views :{
                 'mainView' : {
                     controller : 'productoCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'producto.List.html'
                     
                 }
             }
          }).state('productoCreate',{
            url : '/producto/createProducto',
            views :{
                  'mainView' : {
                      controller : 'productoCtrl',
                      controllerAs: 'ctrl',
                      templateUrl: basePath + 'producto.Create.html'
                  }
              }
          }).state('productoEdit',{
            url : '/producto/:productoId',
            param: {
              productoId : null
            },
            views :{
              'mainView' : {
                controller : 'productoCtrl',
                controllerAs: 'ctrl',
                templateUrl: basePath + 'producto.Create.html'
              }
            }
          }).state('productoItem',{
            url : 'productoItem/:productoId',
            param : {
              productoId : null
            },
            views :{
              'mainView' : {
                controller : 'ItemCtrl',
                controllerAs: 'ctrl',
                templateUrl: 'src/modules/ItemInventario/item.List.html'
              }
            }
          });
      }]);     
})(window.angular);