(function () {
    'use strict';
    angular.module('lacbus').controller('viajesCtrl', ['$scope', '$routeParams', 'viajesService', viajesCtrl]);

    function viajesCtrl($scope, $routeParams, viajesService) {
        $scope.viajes     = [];
        $scope.showAlert    = false;

        console.log($routeParams);

        viajesService.getAll().then(function (data) {
            $scope.viajes = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();