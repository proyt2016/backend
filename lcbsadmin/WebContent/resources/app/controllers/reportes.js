(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', '$localStorage', '$location', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.pasajes    = [];
        $scope.encomiendas = [];
        $scope.totalPasaje = 0;
        $scope.totalEnco = 0;
        $scope.showAlert    = false;

        console.log($routeParams);

        var initialize  = function(){
           var now = moment();
           var total = 0;
           var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
        	reportesService.getPasajesVendidos(dateNow).then(function (data) {
                    $scope.pasajes = data;
                        for(var i = 0; i < $scope.pasajes.length; i++){
                            total  = total + $scope.pasajes[i].precio.monto;
                            $scope.totalPasaje = total;
                            console.log(total);  
                        }   
            });       

        }

        var initializeEnco  = function(){
           var totalEn = 0;
           var now = moment();
           var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
           reportesService.getEncomiendasPagas(dateNow).then(function (data){
                    $scope.encomiendas = data;
                    for(var i = 0; i < $scope.encomiendas.length; i++){
                            totalEn  = totalEn + $scope.encomiendas[i].monto;
                            $scope.totalEnco = totalEn;
                            console.log(total);  
                        }   
            }); 
        }

     

        initialize();
        initializeEnco();
    }

})();