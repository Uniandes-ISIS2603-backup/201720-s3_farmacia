(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'clienteModule',
        'ItemModule',  
        'facturaModule',
        'itemRawModule',
        'suministroModule',
        'ordenModule',
        'OrdenCModule',
        'productoModule',
        'OrdenRotacionModule',
        'loginModule',
        'carritoModule',
    ]);
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
