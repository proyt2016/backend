(function () {
    'use strict';
    angular.module('lacbus').controller('reservasCtrl', ['$scope', '$routeParams', 'viajeService', '$location', 'uiGmapGoogleMapApi', '$localStorage', 'usuarioService', 'reservaService', reservasCtrl]);

    function reservasCtrl($scope, $routeParams, viajeService, $location, uiGmapGoogleMapApi, $localStorage, usuarioService, reservaService) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.viaje = null;
        $scope.usuario = null;
        $scope.comprador = null;
        $scope.resultados = null;
        $scope.reserva 	= null;
        $scope.comprar = {};

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                $scope.map = { center: { latitude: -32.5383022, longitude: -58.0605055 }, zoom: 9 };
        
                uiGmapGoogleMapApi.then(function(){
                    reservaService.getId(id).then(function (datos) {
                    	$scope.reserva = datos;
                        $scope.viaje = datos.viaje;

                        $scope.comprar['origen']    = datos.origen;
                        $scope.comprar['destino']   = datos.destino;
                        
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
                        
                        var puntosRecorrido = datos.viaje.recorrido.puntosDeRecorrido;
                        
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
                        
                        $scope.calcularPrecio();
                    });
                });
            }
            
            viajeService.getTerminales().then(function (data){
                $scope.terminales = data;
            });
        }
        
        $scope.calcularPrecio = function(){
        	var viaje = this.viaje;
        	var origen = $scope.comprar.origen;
            var destino = $scope.comprar.destino;
            
            console.log(origen, destino);
            
        	viajeService.getPrecio(origen.id, destino.id, viaje.recorrido.id).then(function (prc){
        		$scope.comprar['precio'] = prc;
            });
        }

        $scope.buscarUsuario = function(email){
        	var usuario = $scope.emisorStrg;
        	usuarioService.buscarUsuario(usuario).then(function (data) {
                $scope.emisor = data;
            });
        }
        
        $scope.buscar = function(){
            var filtro = {
            	ciPersona : this.ciPersona
            };

            if($scope.emisor != null && $scope.emisor != ''){
            	filtro['usuarioReserva'] = {
                	id : $scope.emisor.id
                };
            }

            reservaService.buscarReserva(filtro).then(function (data) {
                $scope.resultados = data;
            });
        } 

        $scope.procesarCompra = function () {
        	var reserva = this.reserva;
        	
        	if(!this.comprar['origen']){
                toastr.warning('Debe seleccionar un origen', 'Ups');
                return;
            }

            if(!this.comprar['destino']){
                toastr.warning('Debe seleccionar un destino', 'Ups');
                return;
            }

	    	var pasaje = {
	    		id : reserva.id
	    	}
	    	
	    	reservaService.comprar(pasaje).then(function (datos) {
	            toastr.success('Su compra se realizo con exito, que disfrute su viaje.', 'Compra exitosa.');
	            setTimeout(function(){ 
	            	$location.url('/');
	            }, 3000);
	        });
        }

        initialize();
    }

})();