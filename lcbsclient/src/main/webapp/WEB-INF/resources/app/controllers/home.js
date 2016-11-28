(function () {
    'use strict';
    angular.module('lacbus').controller('homeCtrl', ['$scope', 'toastr', '$localStorage', 'terminalService', 'recorridoService', 'viajeService', 'pasajeService', homeCtrl]);

    function homeCtrl($scope, toastr, $localStorage, terminalService, recorridoService, viajeService, pasajeService) {
        $scope.terminales 		= null;
        $scope.recorridos 		= null;
        $scope.resultados 		= null;
        $scope.info 			= null;
        $scope.usuarioLogueado 	= $localStorage.usuarioLogueado;
        $scope.reservaData 		= {};

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
            var filtrosIda = {};

            // if(!this.filter['origen']){
            // 	toastr.warning('Debe seleccionar un origen', 'Ups');
            //     return;
            // }
            
            // if(!this.filter['destino']){
            // 	toastr.warning('Debe seleccionar un destino', 'Ups');
            //     return;
            // }
            
            // if(!this.filter['fechaIda']){
            // 	toastr.warning('Debe seleccionar una fecha de ida', 'Ups');
            //     return;
            // }

            if(this.filter['fechaSalida']){
                filtrosIda.fechaSalida = moment(this.filter['fechaSalida'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(this.filter['origen']){
                filtrosIda.idOrigen = this.filter['origen'];
            }

            if(this.filter['destino']){
                filtrosIda.idDestino = this.filter['destino'];
            }

            if(this.filter['recorrido']){
            	filtrosIda.recorrido = {
                    id : this.filter['recorrido']
                }
            }

            viajeService.buscar(filtrosIda).then(function (datos) {
                $scope.resultados = datos;

                if(!datos.length){
                    toastr.info('No se encontraron viajes con los filtros seleccionados.', 'No hay viajes');
                }
            });
        };

        $scope.mostrarReservar = function (resultado) {
        	$scope.info = resultado;
        	
        	$scope.reservaData['origen']    = '0';
            $scope.reservaData['destino']   = resultado.recorrido.puntosDeRecorrido.length - 1 + '';
            
            $('#modal-reservar').modal('show');
        }
        
        $scope.reservar = function() {
        	var origen = $scope.info.recorrido.puntosDeRecorrido[this.reservaData.origen];
            var destino = $scope.info.recorrido.puntosDeRecorrido[this.reservaData.destino];
            
        	var pasaje = {
    			viaje : {
    				id : $scope.info.id
    			},
        		usuarioReserva : {
        			id : $scope.usuarioLogueado.id
        		},
        		origen : {
                    id : origen.id,
                    tipo : origen.tipo
                },
                destino : {
                    id : destino.id,
                    tipo : destino.tipo
                },
        		fechaReserva : moment().format('YYYY-MM-DD')
        	}
        	
        	pasajeService.reservar(pasaje).then(function (datos) {
                $('#modal-reservar').modal('hide');
            });
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {
        	$scope.info = null;
        });

        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2({
                placeholder: 'Seleccione',
                allowClear: true
            }).on('select2:select', function (evt) {
              $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            }).on("select2:unselect", function (evt) { 
              $scope.filter[evt.currentTarget.name] = null;
            });

            //Date picker
            $('.datepicker').datepicker({
                autoclose : true,
                format: 'dd/mm/yyyy',
                disableTouchKeyboard : true,
                clearBtn : true
            }).on('changeDate', function(evt) {
                $scope.filter[evt.currentTarget.name] = evt.currentTarget.value
            });
        });
    }

})();