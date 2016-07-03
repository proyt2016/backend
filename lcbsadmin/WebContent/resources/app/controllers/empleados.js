(function () {
    'use strict';
    angular.module('lacbus').controller('empleadosCtrl', ['$scope', '$routeParams', 'empleadosService', empleadosCtrl]);

    function empleadosCtrl($scope, $routeParams, empleadosService) {
        $scope.empleados     = [];
        $scope.showAlert    = false;

        empleadosService.getAll().then(function (data) {
        	console.log("etewt",data);
            $scope.empleados = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();