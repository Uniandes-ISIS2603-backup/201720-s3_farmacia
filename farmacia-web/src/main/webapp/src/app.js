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
        'productoModule'



    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
