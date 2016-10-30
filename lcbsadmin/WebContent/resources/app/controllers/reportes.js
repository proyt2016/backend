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
        $scope.cantPasajes = [];
        $scope.cantEncomiendas = [];
        $scope.showAlert    = false;
        $scope.dia = null;
        $scope.grafPasaje = [];
        $scope.grafEncomiendas = [];
        var fechaHoy = moment();
        $scope.dia = moment(fechaHoy,'DD/MM/YYYY').format('YYYY-MM-DD');

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
                            //console.log(grafPasaje);
                        }

            });       

        }

        var initializeEnco  = function(){
           var totalEn = 0;
           var now = moment();
           var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
           reportesService.getEncomiendasPagas(dateNow).then(function (data){
                    $scope.encomiendas = data;
                    console.log($scope.encomiendas);
                    for(var i = 0; i < $scope.encomiendas.length; i++){
                            totalEn  = totalEn + $scope.encomiendas[i].monto;
                            $scope.totalEnco = totalEn;
                        }   
            }); 
        }


     

        initialize();
        initializeEnco();
        
    
   $(document).ready(function() {
            $('#single_cal5').daterangepicker({
                singleDatePicker: true,
                calender_style: "picker_4"
            }, function(start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
        });

   $(document).ready(function(){
      var ctx = document.getElementById("mybarChart");
      var now = moment();
      var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
      var pasajeMonto = 0;
      reportesService.getPasajesVendidos(dateNow).then(function (data) {
                    $scope.pasajes = data;
                        for(var i = 0; i < $scope.pasajes.length; i++){
                            pasajeMonto = $scope.pasajes[i].precio.monto;
                            $scope.grafPasaje.push(pasajeMonto);
                            console.log($scope.grafPasaje);
                                $scope.cantPasajes.push(i)
                            //console.log(grafPasaje);
                        }

        var mybarChart = new Chart(ctx, {
            type: 'bar',
                data: {
                    labels: $scope.cantPasajes,
                datasets: [{
                    label: 'Pasaje',
                    backgroundColor: "#26B99A",
                    data :  $scope.grafPasaje
                }]
                },

                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
      });
    });           
});

$(document).ready(function(){
      var ctx = document.getElementById("mybarChart2");
      var now = moment();
      var encomiendaMonto = 0;
      var dateNow =  moment(now, 'DD/MM/YYYY').format('YYYY-MM-DD');
           reportesService.getEncomiendasPagas(dateNow).then(function (data){
                    $scope.encomiendas = data;
                    for(var i = 0; i < $scope.encomiendas.length; i++){
                           encomiendaMonto = $scope.encomiendas[i].monto;
                           $scope.grafEncomiendas.push(encomiendaMonto);
                           if($scope.encomiendas.length == 1){
                            $scope.cantEncomiendas.push(1)}else{
                                $scope.cantEncomiendas.push(i)
                            }
                        }   
           

        var mybarChart = new Chart(ctx, {
            type: 'bar',
                data: {
                    labels: $scope.cantEncomiendas,
                datasets: [{
                    label: 'Encomienda',
                    backgroundColor: "#26B99A",
                    data :  $scope.grafEncomiendas
                }]
                },

                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
      });
    }); 
 });           

       

}



      
    


    

})();

