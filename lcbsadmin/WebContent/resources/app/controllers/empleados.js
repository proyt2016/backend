(function () {
    'use strict';
    angular.module('lacbus').controller('empleadosCtrl', ['$scope', '$routeParams', 'empleadosService', empleadosCtrl]);

    function empleadosCtrl($scope, $routeParams, empleadosService) {
        $scope.empleados     = [];
        $scope.empleado     = null;
        $scope.showAlert    = false;

        empleadosService.getAll().then(function (data) {
            $scope.empleados = data;
        });

        $scope.add = function(){
            $scope.saving   = true;
            var empleado     = this.empleado;
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

        $scope.edit = function(){
        	console.log('aaaddd')
        }

       /* var mostrarNotificacion = function(tipo){
            var title   = '';
            var text    = '';

            if(tipo == 'success'){
                var title   = 'Exito!';
                var text    = 'Acción realizada con exito.';
            }else if(tipo == 'error'){
                var title   = 'Oh No!';
                var text    = 'Ha ocurrido un error.';
            }

            new PNotify({
                title       : title,
                text        : text,
                type        : tipo,
                nonblock    : {
                    nonblock : true
                }
            });
        }*/

    }

})();