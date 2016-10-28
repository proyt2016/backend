(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', '$localStorage', '$location', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.pasajes    = [];
        $scope.encomiendas = [];
        $scope.showAlert    = false;

        console.log($routeParams);

       var initialize  = function(){
           var now = moment();
           var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
        	reportesService.getPasajesVendidos(dateNow).then(function (data) {
                    $scope.pasajes = data;
                });          
        }

       var initializeEnco  = function(){
           var now = moment();
           var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
           reportesService.getEncomiendasPagas(dateNow).then(function (data){
                    $scope.encomiendas = data;
            }); 
        }
        initialize();
        initializeEnco();
    }

})();