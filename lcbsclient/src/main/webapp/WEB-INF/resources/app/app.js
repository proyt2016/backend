(function () {

    angular.module('lacbus', ['ngRoute', 'ngAnimate', 'toastr']);

    angular.module('lacbus').config(['$routeProvider', 'toastrConfig', configFunction]);

    /*@ngInject*/
    function configFunction($routeProvider, toastrConfig) {
        angular.extend(toastrConfig, {
            positionClass: 'toast-top-center',
            preventDuplicates: true,
            preventOpenDuplicates: true,
            timeOut: 2500
        });

        // Routes
        $routeProvider.when('/', {
            templateUrl : 'app/views/home.html',
            controller  : 'homeCtrl'
        }).otherwise({
            redirectTo  : '/'
        });

        $routeProvider.when('/login', {
            templateUrl: 'app/views/login.html',
            controller: 'loginCtrl'
        });

        $routeProvider.when('/registro', {
            templateUrl: 'app/views/registro.html',
            controller: 'registroCtrl'
        });

        $routeProvider.when('/detalle/:id', {
            templateUrl: 'app/views/detalle.html',
            controller: 'detalleCtrl'
        });

        $routeProvider.when('/comprar/:id', {
            templateUrl: 'app/views/comprar.html',
            controller: 'comprarCtrl'
        });

        $routeProvider.when('/encomiendas', {
            templateUrl: 'app/views/encomienda.html',
            controller: 'encomiendaCtrl'
        });

        $routeProvider.when('/cuponera', {
            templateUrl: 'app/views/cuponera.html',
            controller: 'cuponeraCtrl'
        });

        $routeProvider.when('/historial-compra', {
            templateUrl: 'app/views/historial-compra.html',
            controller: 'historialCompraCtrl'
        });

        $routeProvider.when('/historial-reserva', {
            templateUrl: 'app/views/historial-reserva.html',
            controller: 'historialReservaCtrl'
        });

        $routeProvider.when('/comprar-reserva/:id', {
            templateUrl: 'app/views/comprar-reserva.html',
            controller: 'comprarReservaCtrl'
        });

        $routeProvider.when('/historial-notifacion', {
            templateUrl: 'app/views/historial-notificacion.html',
            controller: 'historialNotificacionCtrl'
        });
    }

})();