(function (ng) {
var mod = ng.module("pagoModule", []);
    mod.constant("pagoContext", "api/pagos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pago/';
            $urlRouterProvider.otherwise("/view");

            $stateProvider.state('pagoCreate', {
                url: '/pagos/create',
                views: {
                    'mainView': {
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pago.create.html'
                    }
                }
            })
        }]);
})(window.angular);