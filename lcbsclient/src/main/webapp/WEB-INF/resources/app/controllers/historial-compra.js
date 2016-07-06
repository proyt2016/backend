(function () {
    'use strict';
    angular.module('lacbus').controller('historialCompraCtrl', ['$scope', 'pasajeService', historialCompraCtrl]);

    function historialCompraCtrl($scope, pasajeService) {
    	$scope.compras = [];

        var transferirA = null;
    	
    	pasajeService.getAll().then(function (datos) {
            $scope.compras = datos;
        });

        $scope.cambiarHorario = function (pasajeId, horario) {
            var r = confirm("Seguro que quiere cambiar el horario?");
            if (r == true) {
                var pasaje = {
                    idPasaje    : pasajeId,
                    idViaje     : horario
                }

                pasajeService.cambiarHorario(pasaje).then(function (datos) {
                    console.log('CAMBIAR HORARIO PRONTO')
                });
            }
        }

        $scope.transferirPasaje = function (pasajeId) {
            var r = confirm("Seguro que quiere transferir el pasaje?");
            if (r == true) {
                var pasaje = {
                    idPasaje    : pasajeId,
                    idUsuario   : transferirA
                }

                pasajeService.transferir(pasaje).then(function (datos) {
                    console.log('TRANSFERENCIA PRONTA');
                });

                transferirA = null;
            }
        }

        $scope.mostrarInfo = function (info) {
            $scope.info = info;
            $('#modal-info').modal('show');
        }

        $scope.mostrarHorarios = function (info) {
        	$scope.info = info;
            $('#modal-cambiar-horario').modal('show');
        }

        $scope.mostrarPasajes = function (info) {
        	$scope.info = info;
            $('#modal-transferir-pasaje').modal('show');
        }

        $('#modal-transferir-pasaje').on('hidden.bs.modal', function (e) {
        	$scope.info = null;
        });

        $('#modal-cambiar-horario').on('hidden.bs.modal', function (e) {
        	$scope.info = null;
        });

        $('#modal-info').on('hidden.bs.modal', function (e) {
            $scope.info = null;
        });
        
        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2({
                placeholder : 'Seleccione un usuario'
            }).on('select2:select', function (evt) {
              transferirA = evt.currentTarget.value
            });
        });
    }

})();