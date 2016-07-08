(function () {
    'use strict';
    angular.module('lacbus').controller('loginCtrl', ['$rootScope', '$scope', '$location', '$localStorage', 'toastr', 'usuarioService', loginCtrl]);

    function loginCtrl($rootScope, $scope, $location, $localStorage, toastr, usuarioService) {
        $scope.login = function(){
        	usuarioService.login(this.usuario).then(function (datos) {
        		if(datos){
        			$localStorage.usuarioLogueado = datos;
            		$rootScope.$broadcast('localStorage:changed');
            		$location.url('/');
        		}else{
        			toastr.error('El usuario no existe o las credenciales no son las correctas', 'Error');
        		}
            });
        }
    }

})();