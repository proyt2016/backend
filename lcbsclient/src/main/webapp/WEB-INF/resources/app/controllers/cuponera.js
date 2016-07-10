(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', '$localStorage', 'toastr', 'cuponeraService', cuponeraCtrl]);

    function cuponeraCtrl($scope, $localStorage, toastr, cuponeraService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	$scope.pagos = 'paypal';

    	$scope.recargar = function () {
	    	var saldo = this.recarga;
	    	var recarga = {
	    		idUsuario 	: $scope.usuarioLogueado.id,
	    		saldo 		: this.recarga + ''
	    	};

	        cuponeraService.recargar(recarga).then(function (datos) {
	        	$scope.usuarioLogueado.cuponera.saldo += saldo;
	        	$scope.recarga = null;
	        	
	        	toastr.success('Su cuponera se recargo con exito!', 'Recarga exitosa');
	        });
        }
    }

})();