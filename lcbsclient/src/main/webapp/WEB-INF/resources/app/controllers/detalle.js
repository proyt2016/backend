(function () {
    'use strict';
    angular.module('lacbus').controller('detalleCtrl', ['$scope', '$routeParams', detalleCtrl]);

    function detalleCtrl($scope, $routeParams) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
    	console.log('id', id);

    	$scope.reservar = function () {
            $('#modal-reservar').modal('show');
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {

        });
    }

})();