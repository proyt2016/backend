(function () {
    'use strict';
    angular.module('lacbus').controller('estadosencomiendaCtrl', ['$scope', '$routeParams', 'estadosencomiendaService', estadosencomiendaCtrl]);

    function estadosencomiendaCtrl($scope, $routeParams, estadosencomiendaService) {
        $scope.estadosencomiendas     = [];
        $scope.estadosencomienda     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                estadosencomiendaService.getId(id).then(function (data) {
                    $scope.estadosencomienda = data;
                });
            }else{
                estadosencomiendaService.getAll().then(function (data) {
                    $scope.estadosencomiendas = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var estadosencomienda     = this.estadosencomienda;
            estadosencomiendaService.add(estadosencomienda).then(
                function (data) {
                    $scope.saving = false;
                
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                }
            );
        }

        $scope.edit = function(){
            $scope.saving   = true;
            var estadosencomienda     = this.estadosencomienda;
            estadosencomiendaService.edit(estadosencomienda).then(
                function (data) {
                    $scope.saving = false;
                
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                }
            );
        }

        $scope.borrar = function (index) {
            $scope.saving = true;
            var estadosencomienda = this.estadosencomienda;
            console.info(estadosencomienda);
            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                estadosencomiendaService.borrar(estadosencomienda.id).then(
                 function () {
                     $scope.estadosencomiendas.splice(index, 1);
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