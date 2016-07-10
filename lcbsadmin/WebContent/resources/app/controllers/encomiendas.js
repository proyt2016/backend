(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendasCtrl', ['$scope', '$routeParams', 'encomiendasService', encomiendasCtrl]);

    function encomiendasCtrl($scope, $routeParams, encomiendasService) {
        $scope.encomiendas     = [];
        $scope.encomienda     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                encomiendasService.getId(id).then(function (data) {
                    $scope.encomienda = data;
                });
            }else{
                console.info("no tengo id"+id);
                encomiendasService.getAll().then(function (data) {
                    $scope.encomiendas = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var encomienda     = this.encomienda;
            console.info(encomienda);
            encomiendasService.add(encomienda).then(
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
            var encomienda     = this.encomienda;
            encomiendasService.edit(encomienda).then(
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
            console.log($scope.encomiendas);
            $scope.saving = true;
            var encomienda = this.encomienda;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                encomiendasService.borrar(encomienda.id).then(
                 function () {
                     $scope.encomiendas.splice(index, 1);
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