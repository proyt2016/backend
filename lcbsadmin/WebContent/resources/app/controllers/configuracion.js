(function () {
    'use strict';
    angular.module('lacbus').controller('configuracionCtrl', ['$scope', '$routeParams', 'configuracionService', configuracionCtrl]);

    function configuracionCtrl($scope, $routeParams, configuracionService) {
        $scope.configuracion     = [];
        $scope.showAlert    = false;

        console.log($routeParams);

        configuracionService.getAll().then(function (data) {
            $scope.configuracion = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();