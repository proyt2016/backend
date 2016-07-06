(function () {
    'use strict';
    angular.module('lacbus').controller('comprarReservaCtrl', ['$scope', '$routeParams', 'reservaService', comprarReservaCtrl]);

    function comprarReservaCtrl($scope, $routeParams, reservaService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
    	
    	$scope.pagos 	= 'paypal';
    	$scope.reserva 	= null;
    	
    	reservaService.getId(id).then(function (datos) {
            $scope.reserva = datos;
        });

        $scope.comprar = function () {
	    	var reserva = $scope.reserva;

	    	console.log(this.pagos, reserva);

	        reservaService.comprar(reserva).then(function (datos) {
	            console.log('COMPRAR reserva PRONTO')
	        });
        }
    }

})();