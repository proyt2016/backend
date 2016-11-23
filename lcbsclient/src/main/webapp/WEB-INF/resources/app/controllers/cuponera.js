(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', '$localStorage', 'toastr', 'cuponeraService', 'usuarioService', cuponeraCtrl]);

    function cuponeraCtrl($scope, $localStorage, toastr, cuponeraService, usuarioService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	
    	$scope.procesarRecarga = function () {        	
        	if(!$scope.usuarioLogueado.ultimosCuatroDigitos){
        		$scope.guardarTarjeta();
            	return;
        	}
        	
        	$scope.recargar();
        };

    	$scope.recargar = function () {
	    	var saldo = this.recarga;
	    	
	    	if(!saldo){
                toastr.warning('Debe ingresar el saldo a recargar', 'Ups');
                return;
            }
	    	
	    	var recarga = {
	    		idUsuario 	: $scope.usuarioLogueado.id,
	    		saldo 		: saldo + ''
	    	};

	        cuponeraService.recargar(recarga).then(function (datos) {
	        	$scope.usuarioLogueado.cuponera.saldo += saldo;
	        	$scope.recarga = null;
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
	              
	            	$scope.recargar();

	            }, function(err) {
	              alert(err.message);
	            });
	          }
	        });
        }
    }

})();