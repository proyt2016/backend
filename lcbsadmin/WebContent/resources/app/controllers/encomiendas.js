(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendasCtrl', ['$scope', '$routeParams', 'encomiendasService', encomiendasCtrl]);

    function encomiendasCtrl($scope, $routeParams, encomiendasService) {
        $scope.encomiendas     = [];
        $scope.showAlert    = false;

        console.log($routeParams);

        encomiendasService.getAll().then(function (data) {
            $scope.encomiendas = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();