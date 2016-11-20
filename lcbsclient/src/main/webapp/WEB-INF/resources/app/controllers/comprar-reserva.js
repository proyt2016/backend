(function () {
    'use strict';
    angular.module('lacbus').controller('comprarReservaCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'reservaService', comprarReservaCtrl]);

    function comprarReservaCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, reservaService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null;
		$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	
        $scope.reserva 	= null;
        $scope.comprar  = {
            pagos : 'paypal',
            cantidad : 1
        };
    	
        reservaService.getId(id).then(function (datos) {
            $scope.reserva = datos;

            $scope.comprar['origen']    = '0';
            $scope.comprar['destino']   = datos.viaje.recorrido.puntosDeRecorrido.length - 1 + '';
            
            var origen = datos.viaje.recorrido.puntosDeRecorrido[$scope.comprar['origen']];
            var destino = datos.viaje.recorrido.puntosDeRecorrido[$scope.comprar['destino']];
            
            viajeService.getPrecio(origen.id,destino.id, datos.viaje.recorrido.id).then(function (prc){
            	$scope.precio = prc;
            });
        });

        $scope.procesarCompra = function () {
	    	var reserva = $scope.reserva;

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
            }

//            var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
//            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];

	    	var pasaje = {
	    		id : reserva.id
	    	}
	    	
	    	reservaService.comprar(pasaje).then(function (datos) {
	            toastr.success('Su compra se realizo con exito, que disfrute su viaje.', 'Compra exitosa.');
	            setTimeout(function(){ 
	            	$location.url('/');
	            }, 3000);
	        });
        }
    }

})();