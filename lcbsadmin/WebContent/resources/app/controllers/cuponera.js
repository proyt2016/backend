(function () {
    'use strict';
    angular.module('lacbus').controller('cuponeraCtrl', ['$scope', '$routeParams', 'usuarioService', '$localStorage', cuponeraCtrl]);

    function cuponeraCtrl($scope, $routeParams, usuarioService, $localStorage) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
    	$scope.emisor 	= null;
    	$scope.monto 	= null;
    	
    	$scope.buscarUsuario = function(email){
        	var usuario = $scope.emisorStrg;
        	usuarioService.buscarUsuario(usuario).then(function (data) {
                $scope.emisor = data;
            });
        }
    	
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
        
        $scope.procesarRecarga = function(){
        	if($scope.emisor == null || $scope.emisor == ''){
        		mostrarNotificacion('error', 'Debe ingresar un usuario');
                return;
            }
        	
        	if($scope.monto == null || $scope.monto == ''){
        		mostrarNotificacion('error', 'Debe ingresar un monto');
                return;
            }

        	var recarga = {
    			idUsuario 	: $scope.emisor.id,
    			saldo 		: $scope.monto
        	}

            usuarioService.recargarCuponera(recarga).then(function (data) {
            	mostrarNotificacion('success', 'Recarga realizada con exito');
            });
        }
    }

})();