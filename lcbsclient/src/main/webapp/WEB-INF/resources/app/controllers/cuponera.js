(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', '$localStorage', 'cuponeraService', cuponeraCtrl]);

    function cuponeraCtrl($scope, $localStorage, cuponeraService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	$scope.pagos = 'paypal';

    	$scope.recargar = function () {
	    	console.log(this.pagos, this.recarga);

	    	var recarga = {
	    		idUsuario 	: $scope.usuarioLogueado.id,
	    		saldo 		: this.recarga + ''
	    	};

	        cuponeraService.recargar(recarga).then(function (datos) {
	            console.log('RECARGA CUPONERA PRONTO')
	        });
        }
    }

})();