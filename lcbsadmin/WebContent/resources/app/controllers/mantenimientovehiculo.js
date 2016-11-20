    (function () {
    'use strict';
    angular.module('lacbus').controller('mantenimientoVehiculoCtrl', ['$scope', '$routeParams', 'vehiculosService', '$localStorage', '$location', mantenimientoVehiculoCtrl]);

    function mantenimientoVehiculoCtrl($scope, $routeParams, vehiculosService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.vehiculos     = [];
        $scope.vehiculo     = null;
        $scope.mantenimiento     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                vehiculosService.getId(id).then(function (data) {
                    $scope.vehiculo = data;
                    $scope.mantenimiento = data.mantenimientos[data.mantenimientos.length - 1];
                    console.log(data.mantenimientos);
                    console.log(data.mantenimientos[data.mantenimientos.length - 1]);
                });
            }else{
               
                vehiculosService.getVehiculoMantenimiento().then(function (data) {
                    $scope.vehiculos = data;
                });
            }
        }


         $scope.guardarMantenimiento = function(){
            $scope.saving   = true;
            var vehiculo     = this.vehiculo;
            var mantenimiento = this.mantenimiento;
            
            console.info(vehiculo);
            console.info(mantenimiento);

            vehiculosService.guardarMantenimiento(vehiculo.id, mantenimiento).then(
                function (data) {
                    $scope.saving = false;
                    //mostrarNotificacion('success');
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                    //mostrarNotificacion('error');
                }
            );
        }

        $scope.edit = function(){
            $scope.saving   = true;
            var vehiculo     = this.vehiculo;
            vehiculo.mantenimientos[vehiculo.mantenimientos.length - 1] = this.mantenimiento;
            vehiculo.enMantenimiento = false;
            vehiculosService.edit(vehiculo).then(
                function (data) {
                    $scope.saving = false;
                
                    //mostrarNotificacion('success');
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                    //mostrarNotificacion('error');
                }
            );
        }

        initialize();

    }

})();