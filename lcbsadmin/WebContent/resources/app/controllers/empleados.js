﻿(function () {
    'use strict';
    angular.module('lacbus').controller('empleadosCtrl', ['$scope', '$routeParams', 'empleadosService', empleadosCtrl]);

    function empleadosCtrl($scope, $routeParams, empleadosService) {
        $scope.empleados     = [];
        $scope.empleado     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                empleadosService.getId(id).then(function (data) {
                    $scope.empleado = data;
                });
            }else{
                console.info("no tengo id"+id);
                empleadosService.getAll().then(function (data) {
                    $scope.empleados = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var empleado     = this.empleado;
            console.info(empleado);
            empleadosService.add(empleado).then(
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
            var empleado     = this.empleado;
            empleadosService.edit(empleado).then(
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
            console.log($scope.empleados);
            $scope.saving = true;
            var empleado = this.empleado;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                empleadosService.borrar(empleado.id).then(
                 function () {
                     $scope.empleados.splice(index, 1);
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