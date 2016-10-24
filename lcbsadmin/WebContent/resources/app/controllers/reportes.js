(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', '$localStorage', '$location', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.pasajes     = [];
        $pasaje             = null;
        $scope.showAlert    = false;
        $scope.Date = new Date();

        console.log($routeParams);

        $scope.getPasajesVendidos = function(){
            
        	reportesService.getAllPasajes(Date).then(function (data) {
                    $scope.pasajes = data;
                });
            
        }

        $scope.edit = function(){
        	console.log('aaaddd')
        }

    }

})();