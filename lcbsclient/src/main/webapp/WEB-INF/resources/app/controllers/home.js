(function () {
    'use strict';
    angular.module('lacbus').controller('homeCtrl', ['$scope', 'toastr', 'terminalService', 'recorridoService', 'viajeService', homeCtrl]);

    function homeCtrl($scope, toastr, terminalService, recorridoService, viajeService) {
        $scope.terminales 	= null;
        $scope.recorridos 	= null;
        $scope.resultados 	= null;
        $scope.info 		= null;

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
            var filtrosIda = null;
            var filtrosVuelta = null;

            if(!this.filter['origen']){
            	toastr.warning('Debe seleccionar un origen', 'Ups');
                return;
            }
            
            if(!this.filter['destino']){
            	toastr.warning('Debe seleccionar un destino', 'Ups');
                return;
            }
            
            if(!this.filter['fechaIda']){
            	toastr.warning('Debe seleccionar una fecha de ida', 'Ups');
                return;
            }
            
            filtrosIda = {
            		fechaSalida : moment(this.filter['fechaIda'], 'DD/MM/YYYY').format('YYYY-MM-DD'),
            		recorrido : {
            			idOrigen : this.filter['origen'],
                        idDestino : this.filter['destino']
            		}      
            }
            
            if(this.filter['recorrido']){
            	filtrosIda.recorrido = {
                    id : this.filter['recorrido']
                }
            }

            if(this.filter['fechaVuelta']){
            	filtrosVuelta = {
                		fechaSalida : moment(this.filter['fechaVuelta'], 'DD/MM/YYYY').format('YYYY-MM-DD'),
                		recorrido : {
                			idDestino : this.filter['origen'],
                            idOrigen : this.filter['destino']
                		}      
                }
            	
            	if(this.filter['recorrido']){
                    filtrosVuelta.recorrido = {
                        id : this.filter['recorrido']
                    }
                }
            }

            viajeService.buscar(filtrosIda).then(function (datos) {
                $scope.resultadosIda = datos;
            });
            
			if(filtrosVuelta){
				viajeService.buscar(filtrosVuelta).then(function (datos) {
	                $scope.resultadosVuelta = datos;
	            });
			}
        };

        $scope.mostrarReservar = function (resultado) {
        	$scope.info = resultado;
            $('#modal-reservar').modal('show');
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {
        	$scope.info = null;
        });

        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2({
                placeholder: 'Seleccione'
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