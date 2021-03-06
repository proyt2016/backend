﻿(function () {
    'use strict';
    angular.module('lacbus').controller('vehiculosCtrl', ['$scope', '$routeParams', 'vehiculosService', '$localStorage', '$location', vehiculosCtrl]);

    function vehiculosCtrl($scope, $routeParams, vehiculosService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.vehiculos     = [];
        $scope.vehiculo     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                vehiculosService.getId(id).then(function (data) {
                    $scope.vehiculo = data;
                });
            }else{
               
                vehiculosService.getAll().then(function (data) {
                    $scope.vehiculos = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var vehiculo     = this.vehiculo;
            console.info(vehiculo);
            vehiculosService.add(vehiculo).then(
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

         $scope.guardarMantenimiento = function(){
            $scope.saving   = true;
            var vehiculo     = this.vehiculo;
            var mantenimiento = this.mantenimiento;
            
            mantenimiento['fechaIngreso'] = moment().format('YYYY-MM-DD');

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

        $scope.borrar = function (index) {
            console.log($scope.vehiculos);
            $scope.saving = true;
            var vehiculo = this.vehiculo;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                vehiculosService.borrar(vehiculo.id).then(
                 function () {
                     $scope.vehiculos.splice(index, 1);
                     $scope.saving = false;

                 }, function () {
                     $scope.saving = false;

                 }
             );
            }
        }

        initialize();

    }

})();