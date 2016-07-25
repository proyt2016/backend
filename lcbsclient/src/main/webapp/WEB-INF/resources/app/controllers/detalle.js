(function () {
    'use strict';
    angular.module('lacbus').controller('detalleCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', detalleCtrl]);

    function detalleCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
        $scope.viaje = null;
        
        viajeService.getId(id).then(function (datos) {
            $scope.viaje = datos;
        });

    	$scope.mostrarReservar = function () {
            $('#modal-reservar').modal('show');
        }
        
        $scope.reservar = function() {
            var pasaje = {
                viaje : {
                    id : this.reservar.viaje
                },
                usuarioReserva : {
                    id : $scope.usuarioLogueado.id
                },
                fechaReserva : moment().format('YYYY-MM-DD')
            }
            
            pasajeService.reservar(pasaje).then(function (datos) {
                toastr.success('El pasaje fue reservado con exito.', 'Reserva de pasaje');
            });
        }
    }

})();