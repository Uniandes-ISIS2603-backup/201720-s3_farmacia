(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'clienteModule',
        'ItemModule',
        'productoModule',

        'facturaModule'


    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
