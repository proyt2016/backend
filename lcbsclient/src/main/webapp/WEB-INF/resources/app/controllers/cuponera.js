(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', '$localStorage', 'toastr', 'cuponeraService', cuponeraCtrl]);

    function cuponeraCtrl($scope, $localStorage, toastr, cuponeraService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	$scope.pagos = 'paypal';

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
    }

})();