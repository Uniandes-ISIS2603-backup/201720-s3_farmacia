/* 
 * hecho por alguien, idk
 */

(function (ng){
    

  var mod = ng.module("suministroModule",[]);
      mod.constant("suministroContext","api/Suministros");
      mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
          var basePath = 'src/modules/suministro/';
          $urlRouterProvider.otherwise('/suministroList');
          
          //definir estados
          $stateProvider.state('suministroList',{
             url : '/suministro',
             views :{
                 'mainView' : {
                     controller : 'suministroCtrl',
                     controllerAs: 'ctrl',
                     templateUrl: basePath + 'suministro.List.html'
                     
                 }
             }
          }).state('suministroCreate',{
            url : '/suministro/createSuministro',
            views :{
              'mainView' : {
                controller :'suministroCtrl',
                controllerAs : 'ctrl',
                templateUrl: basePath + 'suministro.Create.html'
              }
            }
          }).state('suministroEdit',{
            url : '/suministro/:suministroId',
            param: {
              suministroId : null
            },
            views :{
              'mainView':{
              controller : 'suministroCtrl',
              controllerAs :'ctrl',
              templateUrl: basePath + 'suministro.Create.html'
            }
            }
          }).state('suministroItem',{
            url : '/itemRaw/:suministroId',
            param :{
              suministroId : null
            },
            views :{
              'mainView' : {
                controller : 'itemRawCtrl',
                controllerAs: 'ctrl',
                templateUrl : 'src/modules/item/item.List.html'
              }
            }
          });
      }]);     
})(window.angular);