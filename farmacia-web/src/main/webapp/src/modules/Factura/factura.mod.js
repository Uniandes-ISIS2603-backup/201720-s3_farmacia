(function (ng) {
var mod = ng.module("facturaModule", []);
    mod.constant("facturaContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Factura/';
            $urlRouterProvider.otherwise("/view/viewFactura");

            $stateProvider.state('facturaList', {
                url: '/clientes/:clienteId/facturas',
                views: {
                    'mainView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'factura.list.html'
                    }
                }
            }).state('facturaCreate', {
                url: '/clientes/:clienteId/facturas/create',
                views: {
                    'mainView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'factura.create.html'
                    }
                }

            }).state('facturaEdit', {
                url: '/clientes/:clienteId/facturas/:facturaId',
                param: {
                    clienteId: null
                },
                views: {
                    'mainView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'factura.create.html'
                    }
                }
            });
        }]);
})(window.angular);


