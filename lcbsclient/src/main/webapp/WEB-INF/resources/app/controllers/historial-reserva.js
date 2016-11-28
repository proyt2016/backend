(function () {
    'use strict';
    angular.module('lacbus').controller('historialReservaCtrl', ['$scope', '$localStorage', 'toastr', 'reservaService', historialReservaCtrl]);

    function historialReservaCtrl($scope, $localStorage, toastr, reservaService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
        $scope.reservas = [];
    	
    	reservaService.getAll($scope.usuarioLogueado.id).then(function (datos) {
            $scope.reservas = datos;
        });

        $scope.cancelar = function (pasaje) {
            var r = confirm("Seguro que quiere cancelar la reserva?");
            if (r == true) {
                reservaService.cancelar(pasaje.id).then(function () {
                	pasaje.eliminada = true;
                });
            }
        }
    }
})();