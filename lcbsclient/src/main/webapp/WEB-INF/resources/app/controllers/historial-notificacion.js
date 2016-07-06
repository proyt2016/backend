(function () {
    'use strict';
    angular.module('lacbus').controller('historialNotificacionCtrl', ['$scope', 'notificacionService', historialNotificacionCtrl]);

    function historialNotificacionCtrl($scope, notificacionService) {
        $scope.notificaciones = [];
    	
    	notificacionService.getAll().then(function (datos) {
            $scope.notificaciones = datos;
        });
    }

})();