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
                     templateUrl: basePath + 'Producto.List.html'
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
            url : '/itemInventario/:productoId',
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
          }).state('anadirProducto',{
            url : '/:productoId',
            param : {
              productoId : null
            },
            views :{
              'mainView' : {
                controller : 'productoCtrl',
                controllerAs: 'ctrl',
                templateUrl: basePath + 'Producto.List.html'
                
                },'detailView' : {
                     controller : 'productoCtrl',
                     controllerAs: 'ctrl',
                    templateUrl: basePath +  'anadir.producto.html'
                     
                 }
            }
          });
      }]);     
})(window.angular);