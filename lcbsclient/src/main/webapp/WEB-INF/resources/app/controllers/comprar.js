    (function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', comprarCtrl]);

    function comprarCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null;
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
    	$scope.viaje 	= null;
    	$scope.saving = false;
        $scope.comprar  = {
            pagos : 'paypal',
            cantidad : 1
        };
        $scope.precio = null;
    	
        viajeService.getId(id).then(function (datos) {
            $scope.viaje = datos;

            $scope.comprar['origen']    = '0';
            $scope.comprar['destino']   = datos.recorrido.puntosDeRecorrido.length - 1 + '';
            
            var origen = datos.recorrido.puntosDeRecorrido[$scope.comprar['origen']];
            var destino = datos.recorrido.puntosDeRecorrido[$scope.comprar['destino']];
            
            viajeService.getPrecio(origen.id,destino.id, $scope.viaje.recorrido.id).then(function (prc){
            	$scope.precio = prc;
            });
        });
        
        $scope.recalcularPrecio = function () {
        	var viaje = $scope.viaje;
        	var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];
            
        	viajeService.getPrecio(origen.id,destino.id, $scope.viaje.recorrido.id).then(function (prc){
            	$scope.precio = prc;
            });
        }

        $scope.procesarCompra = function () {
        	$scope.saving = true;
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
                pago : true,
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