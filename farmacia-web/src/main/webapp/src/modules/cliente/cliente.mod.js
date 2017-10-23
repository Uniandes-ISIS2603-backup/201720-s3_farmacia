(function (ng) {
var mod = ng.module("clienteModule", []);
    mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/cliente/';
            $urlRouterProvider.otherwise("/view");

            $stateProvider.state('clienteList', {
                url: '/clientes',
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.list.html'
                    }
                }
            }).state('clienteCreate', {
                url: '/clientes/create',
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }

            }).state('clienteEdit', {
                url: '/cliente/:clienteId',
                param: {
                    clienteId: null
                },
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }
            }).state('facturitasKAWAI', {
                url: '/cliente/:clienteId',
                param: {
                    clienteId: null
                },
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }
            });
        }]);
})(window.angular);


