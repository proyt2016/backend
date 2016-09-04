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
		}).when("/encomiendas/search", {
		    templateUrl : "app/views/encomiendas/search.html",
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

		// ruta de perfiles
		$routeProvider.when("/perfil", {
		    templateUrl : "app/views/perfil/list.html",
		    controller  : 'perfilCtrl'
		}).when("/perfil/add", {
		    templateUrl : "app/views/perfil/add.html",
		    controller  : 'perfilCtrl'
		}).when("/perfil/edit/:id", {
		    templateUrl : "app/views/perfil/edit.html",
		    controller  : 'perfilCtrl'
		});

		// ruta de recorridos
		$routeProvider.when("/recorridos", {
		    templateUrl : "app/views/recorridos/list.html",
		    controller  : 'recorridosCtrl'
		}).when("/recorridos/add", {
		    templateUrl : "app/views/recorridos/add.html",
		    controller  : 'recorridosCtrl'
		}).when("/recorridos/edit/:id", {
		    templateUrl : "app/views/recorridos/edit.html",
		    controller  : 'recorridosCtrl'
		}).when("/recorridos/precios/:id", {
		    templateUrl : "app/views/recorridos/precios.html",
		    controller  : 'preciorecorridoCtrl'
		}).when("/recorridos/horarioslist/:id", {
		    templateUrl : "app/views/recorridos/horarioslist.html",
		    controller  : 'horariorecorridoCtrl'
		}).when("/recorridos/horariosadd/:id", {
		    templateUrl : "app/views/recorridos/horariosadd.html",
		    controller  : 'horariorecorridoCtrl'
		}).when("/recorridos/horariosedit/:id/:idHorario", {
		    templateUrl : "app/views/recorridos/horariosedit.html",
		    controller  : 'horariorecorridoCtrl'
		});

		// ruta de estadosencomienda
		$routeProvider.when("/estadosencomienda", {
		    templateUrl : "app/views/estadosencomienda/list.html",
		    controller  : 'estadosencomiendaCtrl'
		}).when("/estadosencomienda/add", {
		    templateUrl : "app/views/estadosencomienda/add.html",
		    controller  : 'estadosencomiendaCtrl'
		}).when("/estadosencomienda/edit/:id", {
		    templateUrl : "app/views/estadosencomienda/edit.html",
		    controller  : 'estadosencomiendaCtrl'
		});

		// ruta de reglacobroencomienda
		$routeProvider.when("/reglacobroencomienda", {
		    templateUrl : "app/views/reglacobroencomienda/list.html",
		    controller  : 'reglacobroencomiendaCtrl'
		}).when("/reglacobroencomienda/add", {
		    templateUrl : "app/views/reglacobroencomienda/add.html",
		    controller  : 'reglacobroencomiendaCtrl'
		}).when("/reglacobroencomienda/edit/:id", {
		    templateUrl : "app/views/reglacobroencomienda/edit.html",
		    controller  : 'reglacobroencomiendaCtrl'
		});

		// ruta de puntosrecorrido
		$routeProvider.when("/puntosrecorrido", {
		    templateUrl : "app/views/puntosrecorrido/map.html",
		    controller  : 'puntosrecorridoCtrl'
		})

		
    }

})();