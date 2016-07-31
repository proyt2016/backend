(function () {
    'use strict';
    angular.module('lacbus').controller('historialNotificacionCtrl', ['$scope', '$localStorage', 'notificacionService', historialNotificacionCtrl]);

    function historialNotificacionCtrl($scope, $localStorage, notificacionService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
        $scope.notificaciones = [];
    	
    	notificacionService.getAll($scope.usuarioLogueado.id).then(function (datos) {
            $scope.notificaciones = datos;
        });
    }

})();