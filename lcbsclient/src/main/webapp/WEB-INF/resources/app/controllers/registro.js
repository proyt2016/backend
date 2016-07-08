(function () {
    'use strict';
    angular.module('lacbus').controller('registroCtrl', ['$rootScope', '$scope','$location', '$localStorage', 'usuarioService', registroCtrl]);

    function registroCtrl($rootScope, $scope, $location, $localStorage, usuarioService) {
        $scope.registrarse = function(){
        	var usuario = this.usuario;
            
        	var dataUsuario = {
                email 		: {
                	email : usuario.email
                },
                clave 		: usuario.clave,
                nombrePila 	: usuario.nombre,
                apellido 	: usuario.apellido
            }
        	
        	usuarioService.add(dataUsuario).then(function (datos) {
        		$localStorage.usuarioLogueado = datos;
        		$rootScope.$broadcast('localStorage:changed');
        		$location.url('/');
            });
        }
    }

})();