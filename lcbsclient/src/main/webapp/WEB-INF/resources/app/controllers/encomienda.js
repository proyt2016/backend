(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendaCtrl', ['$scope', 'toastr', '$localStorage', 'encomiendaService',  encomiendaCtrl]);

    function encomiendaCtrl($scope, toastr, $localStorage, encomiendaService) {
    	$scope.usuarioLogueado = $localStorage.usuarioLogueado;
    	$scope.encomiendas = [];
    	
    	encomiendaService.getAll($scope.usuarioLogueado.id).then(function (datos) {
            $scope.encomiendas = datos;
        });
    }

})();