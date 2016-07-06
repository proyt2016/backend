(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', 'cuponeraService', cuponeraCtrl]);

    function cuponeraCtrl($scope, cuponeraService) {
    	$scope.pagos = 'paypal';

    	$scope.recargar = function () {
	    	console.log(this.pagos, this.recarga);

	    	var recarga = {
	    		idUsuario 	: 1,
	    		saldo 		: this.recarga
	    	};

	        cuponeraService.recargar(recarga).then(function (datos) {
	            console.log('RECARGA CUPONERA PRONTO')
	        });
        }
    }

})();