(function () {
    'use strict';
    angular.module('lacbus').controller('historialReservaCtrl', ['$scope', 'reservaService', historialReservaCtrl]);

    function historialReservaCtrl($scope, reservaService) {
        $scope.reservas = [];
    	
    	reservaService.getAll().then(function (datos) {
            $scope.reservas = datos;
        });

        $scope.cancelar = function (pasaje) {
            var r = confirm("Seguro que quiere cancelar la reserva?");
            if (r == true) {
                reservaService.cancelar(pasaje.id).then(function (datos) {
                    console.log('CANCELAR PASAJE PRONTO')
                });
            }
        }
    }

})();