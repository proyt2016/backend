(function () {
    'use strict';
    angular.module('lacbus').controller('loginCtrl', ['$scope', 'usuarioService', loginCtrl]);

    function loginCtrl($scope, usuarioService) {
        $scope.login = function(){
        	console.log(this.usuario);

        	usuarioService.login(this.usuario).then(function (datos) {
                console.log(datos);
            });
        }
    }

})();