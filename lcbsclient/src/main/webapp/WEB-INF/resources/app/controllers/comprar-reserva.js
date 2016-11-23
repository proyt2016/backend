(function () {
    'use strict';
    angular.module('lacbus').controller('comprarReservaCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'reservaService', 'usuarioService', comprarReservaCtrl]);

    function comprarReservaCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, reservaService, usuarioService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null;
		$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	
        $scope.reserva 	= null;
        $scope.comprar  = {
            pagos : 'cuponera',
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
        	var comprar = this.comprar;
        	
        	if($scope.comprar.pagos == 'stripe' && !$scope.usuarioLogueado.ultimosCuatroDigitos){
        		$('#modal-stripe').modal('show');
            	return;
        	}
        	
        	$scope.procesarPago();
        };

        $scope.procesarPago = function () {
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
        
        $scope.guardarTarjeta = function(){
	        var pago = this.tarjeta;
	
	        Stripe.card.createToken({
	          cvc       : pago.cvc,
	          number    : pago.numero,
	          currency  : 'usd',
	          exp_month : pago.mm,
	          exp_year  : pago.yy,
	        }, function(status, response){
	          if (response.error) { // Problem!
	            alert(response.error.message);
	          } else { // Token was created!
	            // Get the token ID:
	            var token = response.id;
	            var card  = response.card.last4;
	
	            var tokenData = {
	              token                 : token,
	              idUsuario             : $scope.usuarioLogueado.id,
	              ultimosDigitosTarjeta : card
	            }
	
	            usuarioService.agregarTarjeta(tokenData).then(function (response) {
	            	$scope.usuarioLogueado['ultimosCuatroDigitos']  = card;
	              
	            	$scope.procesarPago();
	            	
	            }, function(err) {
	              alert(err.message);
	            });
	          }
	        });
        }
    }

})();