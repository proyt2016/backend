﻿(function () {
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
        $scope.dia = null;
        var cantPasajes = 10;
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
      var mybarChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ["January", "February", "March", "April", "May", "June", "July"],
          datasets: [{
            label: 'Pasajes',
            backgroundColor: "#26B99A",
            data: [cantPasajes, 30, 40, 28, 92, 50, 45]
          }, {
            label: 'Encomiendas',
            backgroundColor: "#03586A",
            data: [cantPasajes, 56, 25, 48, 72, 34, 12]
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
       




      
    }


    

})();

