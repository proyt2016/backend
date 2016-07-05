(function () {
    'use strict';
    angular.module('lacbus').controller('historialCompraCtrl', ['$scope', historialCompraCtrl]);

    function historialCompraCtrl($scope) {

        $scope.mostrarInfo = function (info) {
            // $scope.info = info;
            $('#modal-info').modal('show');
        }

        $scope.cambiarHorario = function () {
            $('#modal-cambiar-horario').modal('show');
        }

        $scope.transferirPasaje = function () {
            $('#modal-transferir-pasaje').modal('show');
        }

        $('#modal-transferir-pasaje').on('hidden.bs.modal', function (e) {

        });

        $('#modal-cambiar-horario').on('hidden.bs.modal', function (e) {

        });

        $('#modal-info').on('hidden.bs.modal', function (e) {
            // $scope.info = null;
        });
    }

})();