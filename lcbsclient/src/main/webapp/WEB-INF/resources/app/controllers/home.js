(function () {
    'use strict';
    angular.module('lacbus').controller('homeCtrl', ['$scope', 'terminalService', 'recorridoService', 'viajeService', homeCtrl]);

    function homeCtrl($scope, terminalService, recorridoService, viajeService) {
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

        recorridoService.getAll().then(function (datos) {
            $scope.recorridos = datos;
        });

        $scope.buscar = function(){
            console.log('filters', this.filter);

            var filtros = {};

            if(this.filter['origen']){
                filtros.origen = {
                    id : this.filter['origen']
                }
            }

            if(this.filter['destino']){
                filtros.destino = {
                    id : this.filter['destino']
                }
            }

            if(this.filter['fechaIda']){
                filtros.fechaIda = moment(this.filter['fechaIda'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(this.filter['fechaVuelta']){
                filtros.fechaVuelta = moment(this.filter['fechaVuelta'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(this.filter['recorrido']){
                filtros.recorrido = {
                    id : this.filter['recorrido']
                }
            }

            viajeService.buscar(filtros).then(function (datos) {
                $scope.resultados = datos;
            });
        };

        $scope.reservar = function () {
            $('#modal-reservar').modal('show');
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {

        });

        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2({
                placeholder : 'Seleccione una terminal'
            }).on('select2:select', function (evt) {
              $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            });

            //Date picker
            $('.datepicker').datepicker({
                autoclose : true,
                format: 'dd/mm/yyyy',
            }).on('changeDate', function(evt) {
                $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            });
        });
        
    }

    

})();