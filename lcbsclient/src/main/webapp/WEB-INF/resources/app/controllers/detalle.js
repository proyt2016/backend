(function () {
    'use strict';
    angular.module('lacbus').controller('detalleCtrl', ['$scope', '$routeParams', '$localStorage', '$location', 'toastr', 'viajeService', 'pasajeService', 'uiGmapGoogleMapApi', detalleCtrl]);

    function detalleCtrl($scope, $routeParams, $localStorage, $location, toastr, viajeService, pasajeService, uiGmapGoogleMapApi) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
        $scope.viaje = null;
        
        $scope.map = { center: { latitude: -32.5383022, longitude: -58.0605055 }, zoom: 10 };
        
        uiGmapGoogleMapApi.then(function(){
        	viajeService.getId(id).then(function (datos) {
                $scope.viaje = datos;
                
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
                
                console.log($scope.recorrido);
            });
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