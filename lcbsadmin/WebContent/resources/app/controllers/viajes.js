(function () {
    'use strict';
    angular.module('lacbus').controller('viajesCtrl', ['$scope', '$routeParams', 'viajeService', '$location', 'uiGmapGoogleMapApi', '$localStorage', viajesCtrl]);

    function viajesCtrl($scope, $routeParams, viajeService, $location, uiGmapGoogleMapApi, $localStorage) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.filtro = {
            idOrigen : null,
            idDestino : null
        }
        $scope.viaje = null;
        $scope.comprar  = {
            pagos : 'paypal',
            cantidad : 1
        };
        $scope.usuario = null;
        $scope.comprador = null;
        $scope.resultados = null;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                $scope.map = { center: { latitude: -32.5383022, longitude: -58.0605055 }, zoom: 9 };
        
                uiGmapGoogleMapApi.then(function(){
                    viajeService.getId(id).then(function (datos) {
                        $scope.viaje = datos;

                        $scope.comprar['origen']    = '0';
                        $scope.comprar['destino']   = datos.recorrido.puntosDeRecorrido.length - 1 + '';
                        
                        var origen = datos.recorrido.puntosDeRecorrido[$scope.comprar.origen];
                        var destino = datos.recorrido.puntosDeRecorrido[$scope.comprar.destino];
                        for(var i in datos.recorrido.precios){
                            var precio = datos.recorrido.precios[i];
                            
                            if(precio['origen'].id == origen.id && precio['destino'].id == destino.id){
                            	$scope.comprar['precio'] = precio['monto'];
                            }
                        }
                        
                        $scope.recorrido = {
                            visible: true,
                            geodesic: true,
                            stroke: {
                                color: '#6060FB',
                                weight: 3
                            },
                            icons: [{
                                icon: {
                                    path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW
                                },
                                offset: '25px',
                                repeat: '80px'
                            }],
                            path : []
                        };
                        
                        var puntosRecorrido = datos.recorrido.puntosDeRecorrido;
                        
                        for(var i in puntosRecorrido){
                            var coords = puntosRecorrido[i].ubicacionMapa.split(",");
                            
                            if(i == 0){
                                $scope.map.center = { latitude: coords[0], longitude: coords[1] };
                            }
                            
                            $scope.recorrido.path.unshift({
                                latitude: coords[0],
                                longitude: coords[1]
                            });
                        }
                    });
                });
            }
            
            viajeService.getTerminales().then(function (data){
                $scope.terminales = data;
            });
        }
        
        $scope.calcularPrecio = function(){
        	var viaje = this.viaje;
        	var puntosDeRecorrido = viaje.recorrido.puntosDeRecorrido;
        	var origen = puntosDeRecorrido[$scope.comprar.origen];
            var destino = puntosDeRecorrido[$scope.comprar.destino];
            for(var i in viaje.recorrido.precios){
                var precio = viaje.recorrido.precios[i];
                
                if(precio['origen'].id == origen.id && precio['destino'].id == destino.id){
                	$scope.comprar['precio'] = precio['monto'];
                }
            }
        }

        $scope.buscarUsuario = function(){
            var usuario = $scope.usuario;
            viajeService.buscarUsuario(usuario).then(function (data) {
                $scope.comprador = data;
            });
        }
        
        $scope.buscar = function(){
            var filtro = angular.copy($scope.filtro);
            
            if(!filtro['fechaSalida']){
                mostrarNotificacion('error', 'Debe seleccionar una fecha');
                return;
            }
            
            if(filtro['fechaSalida']){
                filtro.fechaSalida = moment(filtro['fechaSalida'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(filtro['idDestino'] == null){
                delete filtro['idDestino'];
            }

            if(filtro['idOrigen'] == null){
                delete filtro['idOrigen'];
            }

            viajeService.buscar(filtro).then(function (data) {
                $scope.resultados = data;
            });
        } 

        $scope.procesarCompra = function () {
            var viaje = $scope.viaje;

            if(!this.comprar['origen']){
                mostrarNotificacion('error', 'Debe seleccionar un origen');
                return;
            }

            if(!this.comprar['destino']){
                mostrarNotificacion('error', 'Debe seleccionar un destino');
                return;
            }

            var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];

            var pasaje = {
                viaje : {
                    id : viaje.id 
                },
                origen : {
                    id : origen.id,
                    tipo : origen.tipo
                },
                destino : {
                    id : destino.id,
                    tipo : destino.tipo
                },
                fechaCompra : moment().format('YYYY-MM-DD') 
            };
            
            if($scope.comprador){
            	pasaje['comprador'] = {
            		id : $scope.comprador.id
                };
            };
            
            if($scope.ciUsuario){
            	pasaje['ciPersona'] = $scope.ciUsuario;
            };
            
            viajeService.comprar(pasaje).then(function (datos) {
                mostrarNotificacion('success', 'Su compra se realizo con exito.', 'Compra exitosa.');
                $location.url('/viajes');
            });
        }
        
        $scope.procesarReservar = function() {
        	var viaje = $scope.viaje;
        	var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];

            var pasaje = {
                viaje : {
                    id : $scope.viaje.id
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
            };
            
            if($scope.comprador){
            	pasaje['usuarioReserva'] = {
            		id : $scope.comprador.id
                };
            };
            
            if($scope.ciUsuario){
            	pasaje['ciPersona'] = $scope.ciUsuario;
            };
            
            viajeService.reservar(pasaje).then(function (datos) {
            	mostrarNotificacion('success', 'El pasaje fue reservado con exito.');
            	$location.url('/viajes');
            });
        }

        initialize();

        var mostrarNotificacion = function (tipo, text) {
            var title = '';

            if (tipo == 'success') {
                var title = 'Exito!';
                text || (text = 'Acción realizada con exito.');
            } else if (tipo == 'error') {
                var title = 'Oh No!';
                text || (text = 'Ha ocurrido un error.');
            }

            new PNotify({
                title: title,
                text: text,
                type: tipo,
                nonblock: {
                    nonblock: true
                }
            });
        }
        
        $( document ).ready(function() {
            $('.select2-filtro').select2({
                placeholder: 'Seleccione',
                allowClear: true
            }).on('select2:select', function (evt) {
                $scope.filtro[evt.currentTarget.name] = evt.currentTarget.value;
            }).on("select2:unselect", function (evt) { 
              $scope.filtro[evt.currentTarget.name] = null;
            });
            
            $('.datepicker').daterangepicker({
            	singleDatePicker : true,
            	calender_style : "picker_2",
            	format : 'DD/MM/YYYY',
            }).on('apply.daterangepicker', function(ev, picker) {
            	$scope.filtro[ev.currentTarget.name] = picker.startDate.format('DD/MM/YYYY');
        	});
        });
    }

})();