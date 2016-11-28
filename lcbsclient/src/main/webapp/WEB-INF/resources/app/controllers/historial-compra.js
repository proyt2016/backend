(function () {
    'use strict';
    angular.module('lacbus').controller('historialCompraCtrl', ['$scope', '$localStorage', 'toastr', 'pasajeService', 'usuarioService', historialCompraCtrl]);

    function historialCompraCtrl($scope, $localStorage, toastr, pasajeService, usuarioService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	$scope.compras = [];

        var transferirA = null;
    	
    	pasajeService.getAll($scope.usuarioLogueado.id).then(function (datos) {
            $scope.compras = datos;
        });

        $scope.cambiarHorario = function (pasaje, viaje) {
        	var index = $scope.compras.indexOf(pasaje);
            var r = confirm("Seguro que quiere cambiar el horario?");
            if (r == true) {
                var pasajeData = {
                    idPasaje	: pasaje.id,
                    idViaje     : viaje.id
                }

                pasajeService.cambiarHorario(pasajeData).then(function (datos) {
                	$scope.compras[index]['viaje'] = viaje;
                	$('#modal-cambiar-horario').modal('hide');
                });
            }
        }

        $scope.transferirPasaje = function (pasaje) {
        	var index = $scope.compras.indexOf(pasaje);
            var r = confirm("Seguro que quiere transferir el pasaje?");
            if (r == true) {
                var pasaje = {
                    idPasaje    : pasaje.id,
                    idUsuario   : transferirA
                }

                pasajeService.transferir(pasaje).then(function () {
                	$scope.compras.splice(index, 1);
                	$('#modal-transferir-pasaje').modal('hide');
                });

                transferirA = null;
            }
        }

        $scope.mostrarHorarios = function (info) {
        	$scope.info = info;
        	pasajeService.listarHorarios(info.id).then(function (datos) {
        		$scope.info['listaHorarios'] = datos;
        		$('#modal-cambiar-horario').modal('show');
            });
        }

        $scope.mostrarPasajes = function (info) {
        	$scope.info = info;
            $('#modal-transferir-pasaje').modal('show');
            
            usuarioService.getAll().then(function (datos) {
                $scope.usuarios = datos;
            });
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