(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService) {
        $scope.reportes     = [];
        $scope.showAlert    = false;

        console.log($routeParams);

        reportesService.getAll().then(function (data) {
            $scope.reportes = data;
        });

        $scope.add = function(){
        	console.log('aaaddd')
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();