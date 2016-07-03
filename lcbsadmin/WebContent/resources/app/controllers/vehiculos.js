(function () {
    'use strict';
    angular.module('lacbus').controller('vehiculosCtrl', ['$scope', '$routeParams', 'vehiculosService', vehiculosCtrl]);

    function vehiculosCtrl($scope, $routeParams, vehiculosService) {
        $scope.vehiculos     = [];
        $scope.showAlert    = false;

        console.log($routeParams);

        vehiculosService.getAll().then(function (data) {
            $scope.vehiculos = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();