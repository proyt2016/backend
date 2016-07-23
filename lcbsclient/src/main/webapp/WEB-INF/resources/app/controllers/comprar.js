    (function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', comprarCtrl]);

    function comprarCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
    	$scope.viaje 	= null;
        $scope.comprar  = {
            pagos : 'paypal',
            cantidad : 1
        };
    	
        viajeService.getId(id).then(function (datos) {
            $scope.viaje = datos;

            $scope.comprar['origen']    = '0';
            $scope.comprar['destino']   = datos.recorrido.puntosDeRecorrido.length - 1 + '';

            console.log($scope.comprar);
        });

        $scope.procesarCompra = function () {
	    	var viaje = $scope.viaje;

            if(!this.comprar['origen']){
                toastr.warning('Debe seleccionar un origen', 'Ups');
                return;
            }

            if(!this.comprar['destino']){
                toastr.warning('Debe seleccionar un destino', 'Ups');
                return;
            }

            if(!this.comprar['cantidad']){
                toastr.warning('Debe seleccionar la cantidad de pasajes', 'Ups');
                return;
            }

            var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];

	    	var pasaje = {
	    		comprador : {
	    			id : $scope.usuarioLogueado.id
	    		},
	    		viaje : {
                    id : viaje.id 
                },
                origen : {
                    id : origen.id,
                    tipo : origen.tipo
                },
                destino : {
                    id : destino.id,
                    tipo : destino.tipo
                },
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