(function (ng) {
var mod = ng.module("OrdenCModule", []);
    mod.constant("ordenCContext", "api/ordenesCompra");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/OrdenCompra/';
            $urlRouterProvider.otherwise("/view");

            $stateProvider.state('ordenCList', {
                url: '/ordenesCompra',
                views: {
                    'mainView': {
                        controller: 'ordenCCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'ordenC.list.html'
                    }
                }
            }).state('ordenCCreate', {
                url: '/ordenesCompra/create',
                views: {
                    'mainView': {
                        controller: 'ordenCCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'ordenC.create.html'
                    }
                }

            })
        }]);
})(window.angular);


