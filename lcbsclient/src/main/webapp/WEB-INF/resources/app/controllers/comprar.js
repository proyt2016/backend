    (function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', comprarCtrl]);

    function comprarCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
    	$scope.pagos 	= 'paypal';
    	$scope.viaje 	= null;
        $scope.cantidad = 1;
    	
        viajeService.getId(id).then(function (datos) {
            $scope.viaje = datos;
        });

        $scope.comprar = function () {
	    	var viaje = $scope.viaje;
	    	
	    	var pasaje = {
	    		comprador : {
	    			id : $scope.usuarioLogueado.id
	    		},
	    		viaje : viaje,
	    		fechaCompra : moment().format('YYYY-MM-DD') 
	    	}
	    	
	        pasajeService.comprar(pasaje).then(function (datos) {
	            toastr.success('Su compra se realizo con exito, que disfrute su viaje.', 'Compra exitosa.');
	            setTimeout(function(){ 
	            	$location.url('/');
	            }, 3000);
	        });
        }
    }

})();