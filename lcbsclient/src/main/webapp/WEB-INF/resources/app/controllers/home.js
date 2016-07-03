(function () {
    'use strict';
    angular.module('lacbus').controller('homeCtrl', ['$scope', 'terminalService', homeCtrl]);

    function homeCtrl($scope, terminalService) {
        $scope.terminales = null;
        $scope.recorridos = null;
        $scope.resultados = null;

        $scope.filter = {
            origen : null,
            destino : null,
            fechaIda : null,
            fechaVuelta : null,
            recorrido : null,
            viajes : 'todos',
            asientos : 'todos',
            idaVuelta : 'vuelta'
        }
        
        terminalService.getAll().then(function (datos) {
            $scope.terminales = datos;
        });

        $scope.buscar = function(){
            console.log('filters', this.filter);
        };

        $scope.reservar = function () {
            $('#modal-reservar').modal('show');
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {

        });

        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2();

            $('.select2').on('select2:select', function (evt) {
              $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            });

            //Date picker
            $('.datepicker').datepicker({
                autoclose : true
            }).on('changeDate', function(evt) {
                $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            });
        });
        
    }

    

})();