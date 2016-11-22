(function () {
    'use strict';
    angular.module('lacbus').controller('empleadosCtrl', ['$scope', '$routeParams', 'empleadosService', 'configuracionService', 'perfilService', '$localStorage', '$location', empleadosCtrl]);

    function empleadosCtrl($scope, $routeParams, empleadosService, configuracionService, perfilService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.empleados     = [];
        $scope.empleado     = null;
        $scope.showAlert    = false;
        $scope.configuracion = null;
        $scope.perfiles     = [];
        
        configuracionService.getConfiguracion().then(function (data) {
            $scope.configuracion = data;
        });

        perfilService.getAll().then(function (data) {
            $scope.perfiles = data;
        });

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                empleadosService.getId(id).then(function (data) {
                    $scope.empleado = data;
                });
            }else{
                empleadosService.getAll().then(function (data) {
                    $scope.empleados = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var empleado     = this.empleado;
            var perfilSeleccionado = empleado.perfil;
            empleado.perfil = {"id":null};
            empleado.perfil.id = perfilSeleccionado;
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
            var perfilSeleccionado = empleado.perfil;
            empleado.perfil = {"id":null};
            empleado.perfil.id = perfilSeleccionado;
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