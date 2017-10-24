(function (ng) {
var mod = ng.module("facturaModule", []);
    
    mod.constant("clienteContext", "api/clientes");
    mod.constant("facturaContext", "facturas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Factura/';
            
            $urlRouterProvider.otherwise("/facturas");

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
                    clienteId:null,
                    facturaId: null
                },
                views: {
                    'mainView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'factura.editar.html'
                    }
                }
            }).state('facturaCrear', {
                url: '/clientes/:clienteId/facturas',
                param: {
                    clienteId:null,
                    facturaId: null
                },
                views: {
                    'mainView': {
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'factura.create.html'
                    }
                }
            });;
        }]);
})(window.angular);


