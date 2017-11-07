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
          });
      }]);     
})(window.angular);