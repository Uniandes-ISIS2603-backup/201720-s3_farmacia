(function (ng) {
var mod = ng.module("carritoModule", []);
    mod.constant("carritoContext", "api/carrito");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/carrito/';
            $urlRouterProvider.otherwise("/view");

            $stateProvider.state('carritoList', {
                url: '/carrito',
                views: {
                    'mainView': {
                        controller: 'carritoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'carrito.list.html'
                    }
                }
            })
        }]);
})(window.angular);