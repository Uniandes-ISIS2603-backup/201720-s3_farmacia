/* 
 * Hecho por alguien, idk
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("loginModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/Login/';

            $urlRouterProvider.otherwise("/login");


            $stateProvider.state('login', {
                url: '/login',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'login.html',
                        controller: 'loginCtrl'
                    }
                }
            }).state('logout', {
                url: '/logout',
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'logout.html',
                        controller: 'loginCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


