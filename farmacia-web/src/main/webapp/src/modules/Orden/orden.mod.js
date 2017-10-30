(function (ng) {
    var mod = ng.module("ordenModule",[]);
        mod.constant("ordenContext", "api/ordenes");
        mod.config(['$stateProvider','$urlRouterProvider',function ($stateProvider, $urlRouterProvider){ 
                var basePath = 'src/modules/Orden/';
                $urlRouterProvider.otherwise("/ordenList");
                $stateProvider.state('ordenList', {
                    url: '/Orden',
                    views: {
                        'mainView': {
                            controller: 'ordenCtrl',
                            controllerAs: 'ctrl',
                            templateUrl: basePath + 'orden.list.html'
                        }
                    }
                }).state('ordenCreate', {
                    url: '/Orden/create',
                    views: {
                        'mainView': {
                            controller:'ordenCtrl',
                            controllerAs: 'ctrl',
                            templateUrl: basePath + 'orden.create.html'
                        }
                    }
                });
        }]);
})(window.angular);


