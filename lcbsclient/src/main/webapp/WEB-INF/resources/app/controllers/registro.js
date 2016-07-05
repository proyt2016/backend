(function () {
    'use strict';
    angular.module('lacbus').controller('registroCtrl', ['$scope', 'usuarioService', registroCtrl]);

    function registroCtrl($scope, usuarioService) {
        $scope.registrarse = function(){
        	console.log(this.usuario);

        	usuarioService.add(this.usuario).then(function (datos) {
                console.log(datos);
            });
        }
    }

})();