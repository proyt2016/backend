    (function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', 'usuarioService', comprarCtrl]);

    function comprarCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService, usuarioService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null;
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
    	$scope.viaje 	= null;
    	$scope.saving 	= false;
        $scope.comprar  = {
            pagos : 'cuponera',
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
        	var comprar = this.comprar;
        	
        	if($scope.comprar.pagos == 'stripe' && !$scope.usuarioLogueado.ultimosCuatroDigitos){
        		$('#modal-stripe').modal('show');
            	return;
        	}
        	
        	$scope.procesarPago();
        };
        
        $scope.procesarPago = function () {
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
	        	$location.url('/');
	        });
        };
        
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