(function () {
    'use strict';
    angular.module('lacbus').controller('loginCtrl', ['$rootScope', '$scope', '$location', '$localStorage', 'usuarioService', loginCtrl]);

    function loginCtrl($rootScope, $scope, $location, $localStorage, usuarioService) {
        $scope.login = function(){
        	usuarioService.login(this.usuario).then(function (datos) {
        		if(datos){
        			$localStorage.empleadoLogueado = datos;
            		$rootScope.$broadcast('localStorage:changed', datos);
        		}else{
        			mostrarNotificacion('error', 'El usuario no existe o las credenciales no son las correctas');
        		}
            }, function() {
            	mostrarNotificacion('error', 'El usuario no existe o las credenciales no son las correctas');
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
    }

})();