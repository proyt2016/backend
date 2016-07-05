(function () {
    'use strict';
    angular.module('lacbus').controller('recorridosCtrl', ['$scope', '$routeParams', 'recorridosService', recorridosCtrl]);

    function recorridosCtrl($scope, $routeParams, recorridosService) {
        $scope.recorridos     = [];
        $scope.recorrido     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                recorridosService.getId(id).then(function (data) {
                    $scope.recorrido = data;
                });
            }else{
                console.info("no tengo id"+id);
                recorridosService.getAll().then(function (data) {
                    $scope.recorridos = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var recorrido     = this.recorrido;
            console.info(recorrido);
            recorridosService.add(recorrido).then(
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
            var recorrido     = this.recorrido;
            recorridosService.edit(recorrido).then(
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
            console.log($scope.recorridos);
            $scope.saving = true;
            var recorrido = this.recorrido;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                recorridosService.borrar(recorrido.id).then(
                 function () {
                     $scope.recorridos.splice(index, 1);
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