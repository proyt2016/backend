(function () {
    'use strict';
    angular.module('lacbus').controller('loginCtrl', ['$rootScope', '$scope', '$location', '$localStorage', 'usuarioService', loginCtrl]);

    function loginCtrl($rootScope, $scope, $location, $localStorage, usuarioService) {
        $scope.login = function(){
        	usuarioService.login(this.usuario).then(function (datos) {
        		$localStorage.usuarioLogueado = datos;
        		$rootScope.$broadcast('localStorage:changed');
        		$location.url('/');
            });
        }
    }

})();