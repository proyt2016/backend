    (function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', 'pasajeService', comprarCtrl]);

    function comprarCtrl($scope, $routeParams, pasajeService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
    	
    	$scope.pagos 	= 'paypal';
    	$scope.pasaje 	= null;
        $scope.cantidad = 1;
    	
    	pasajeService.getId(id).then(function (datos) {
            $scope.pasaje = datos;
        });

        $scope.comprar = function () {
	    	var pasaje = $scope.pasaje;

	    	console.log(this.pagos, this.cantidad, pasaje);

	        pasajeService.comprar(pasaje).then(function (datos) {
	            console.log('COMPRAR reserva PRONTO')
	        });
        }
    }

})();