(function () {

    angular.module('lacbus', ['ngRoute']);

    angular.module('lacbus').config(['$routeProvider', configFunction]);

    /*@ngInject*/
    function configFunction($routeProvider) {
        // Routes
        $routeProvider.when("/", {
		    templateUrl : "app/views/home.html",
		    controller  : 'homeCtrl'
		}).otherwise({
            redirectTo 	: '/'
        });

		// ruta de viajes
		$routeProvider.when("/viajes", {
		    templateUrl : "app/views/viajes/list.html",
		    controller  : 'viajesCtrl'
		}).when("/viajes/add", {
		    templateUrl : "app/views/viajes/add.html",
		    controller  : 'viajesCtrl'
		}).when("/viajes/edit/:id", {
		    templateUrl : "app/views/viajes/edit.html",
		    controller  : 'viajesCtrl'
		});

		// ruta de encomiendas
		$routeProvider.when("/encomiendas", {
		    templateUrl : "app/views/encomiendas/list.html",
		    controller  : 'encomiendasCtrl'
		}).when("/encomiendas/add", {
		    templateUrl : "app/views/encomiendas/add.html",
		    controller  : 'encomiendasCtrl'
		}).when("/encomiendas/edit/:id", {
		    templateUrl : "app/views/encomiendas/edit.html",
		    controller  : 'encomiendasCtrl'
		});

		// ruta de empleados
		$routeProvider.when("/empleados", {
		    templateUrl : "app/views/empleados/list.html",
		    controller  : 'empleadosCtrl'
		}).when("/empleados/add", {
		    templateUrl : "app/views/empleados/add.html",
		    controller  : 'empleadosCtrl'
		}).when("/empleados/edit/:id", {
		    templateUrl : "app/views/empleados/edit.html",
		    controller  : 'empleadosCtrl'
		});

		// ruta de vehiculos
		$routeProvider.when("/vehiculos", {
		    templateUrl : "app/views/vehiculos/list.html",
		    controller  : 'vehiculosCtrl'
		}).when("/vehiculos/add", {
		    templateUrl : "app/views/vehiculos/add.html",
		    controller  : 'vehiculosCtrl'
		}).when("/vehiculos/edit/:id", {
		    templateUrl : "app/views/vehiculos/edit.html",
		    controller  : 'vehiculosCtrl'
		});

		// ruta de configuracion
		$routeProvider.when("/configuracion", {
		    templateUrl : "app/views/configuracion/list.html",
		    controller  : 'configuracionCtrl'
		}).when("/configuracion/add", {
		    templateUrl : "app/views/configuracion/add.html",
		    controller  : 'configuracionCtrl'
		}).when("/configuracion/edit/:id", {
		    templateUrl : "app/views/configuracion/edit.html",
		    controller  : 'configuracionCtrl'
		});

		// ruta de reportes
		$routeProvider.when("/reportes", {
		    templateUrl : "app/views/reportes/list.html",
		    controller  : 'reportesCtrl'
		}).when("/reportes/add", {
		    templateUrl : "app/views/reportes/add.html",
		    controller  : 'reportesCtrl'
		}).when("/reportes/edit/:id", {
		    templateUrl : "app/views/reportes/edit.html",
		    controller  : 'reportesCtrl'
		});
    }

})();