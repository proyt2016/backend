(function () {

    var app = angular.module('lacbus', ['ngRoute', 'ngAnimate', 'toastr', 'ngStorage', 'pusher-angular', 'uiGmapgoogle-maps']);

    app.config(['$routeProvider', '$httpProvider', 'toastrConfig', 'uiGmapGoogleMapApiProvider', 'CONFIGURACION', configFunction]);
    
    app.controller('appCtrl', ['$scope', '$location', '$localStorage', '$pusher', 'toastr', appCtrl]);
    
    /*@ngInject*/
    function configFunction($routeProvider, $httpProvider, toastrConfig, uiGmapGoogleMapApiProvider, CONFIGURACION) {
    	$httpProvider.defaults.headers.common['lcbs-tenant'] = CONFIGURACION.tenant_id;
    	
        uiGmapGoogleMapApiProvider.configure({
            key: 'AIzaSyBVL227yFvpTa6b0oolhl3PW_BPGLFMnwI',
            v: '3.20', //defaults to latest 3.X anyhow
            libraries: 'weather,geometry,visualization'
        });
        
        angular.extend(toastrConfig, {
            positionClass: 'toast-top-center',
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
    
    function appCtrl($scope, $location, $localStorage, $pusher, toastr) {
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
        $scope.logout = function(){
        	$localStorage.$reset();
        	$scope.usuarioLogueado = null;
        	$location.url('/');
        }
        
        $scope.$on('localStorage:changed', function(event, data){
        	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
        });
        
        var client = new Pusher('e782ddf887a873098d22');
        var pusher = $pusher(client);
        pusher.subscribe('notificaciones');
        pusher.bind('nueva-notificacion',
          function(data) {
        	toastr.success(data.mensaje, 'Nueva notificacion')
          }
        );
    }
    
    $.ajax({
    	//url			: 'http://192.168.43.49:8080/lcbsapi/rest/empresa/getconfirguacionempresa/',
    	url			: '/lcbsapi/rest/empresa/getconfirguacionempresa/',
    	method		: 'GET',
    	dataType 	: 'json',
    	headers		: {
            'lcbs-tenant' : 'f2b21f34-2554-4b83-9bab-4bb3e5e7b03d'
        }
	}).done(function( configuracion ) {
		$('head').append('<style>' + configuracion.css + '</style>');
		
		configuracion['url'] 		= '/lcbsapi/rest/'; //http://192.168.43.49:8080/lcbsapi/rest/
		configuracion['tenant_id'] 	= 'f2b21f34-2554-4b83-9bab-4bb3e5e7b03d';
				
		angular.element(document).ready(function() {
	    	app.constant('CONFIGURACION', configuracion);
	        angular.bootstrap(document, ['lacbus']);
	    });
	});

})();