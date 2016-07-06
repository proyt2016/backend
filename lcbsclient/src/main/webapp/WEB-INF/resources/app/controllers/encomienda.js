(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendaCtrl', ['$scope', 'toastr', 'encomiendaService',  encomiendaCtrl]);

    function encomiendaCtrl($scope, toastr, encomiendaService) {
    	toastr.success('Hello world!', 'Toastr fun!');

    	$scope.encomiendas = [];
    	
    	encomiendaService.getAll(1).then(function (datos) {
            $scope.encomiendas = datos;
        });
    }

})();