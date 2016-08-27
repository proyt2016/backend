(function () {
    'use strict';
    angular.module('lacbus').controller('reglacobroencomiendaCtrl', ['$scope', '$routeParams', 'reglacobroencomiendaService', reglacobroencomiendaCtrl]);

    function reglacobroencomiendaCtrl($scope, $routeParams, reglacobroencomiendaService) {
        $scope.reglacobroencomiendas     = [];
        $scope.reglacobroencomienda     = null;
        $scope.criterias = [];
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                reglacobroencomiendaService.getId(id).then(function (data) {
                    $scope.reglacobroencomienda = data;
                    $scope.criterias = $scope.reglacobroencomienda.criterias;
                });
            }else{
                reglacobroencomiendaService.getAll().then(function (data) {
                    $scope.reglacobroencomiendas = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var reglacobroencomienda     = this.reglacobroencomienda;
            console.log('casaaaa');
            console.log(reglacobroencomienda);
            reglacobroencomienda.criterias = $scope.criterias;
            reglacobroencomiendaService.add(reglacobroencomienda).then(
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
            var reglacobroencomienda     = this.reglacobroencomienda;
            reglacobroencomiendaService.edit(reglacobroencomienda).then(
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
            var reglacobroencomienda = this.reglacobroencomienda;
            console.info(reglacobroencomienda);
            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                reglacobroencomiendaService.borrar(reglacobroencomienda.id).then(
                 function () {
                     $scope.reglacobroencomiendas.splice(index, 1);
                     $scope.saving = false;

                 }, function () {
                     $scope.saving = false;

                 }
             );
            }
        }

        $scope.addRelation = function () {
            if($scope.criterias.length == 0){
                var nuevaCrit = {'operador':'<=','valor':'0','precio':''};
                $scope.criterias.push(nuevaCrit);
                nuevaCrit = {'operador':'>','valor':'0','precio':''};
                $scope.criterias.push(nuevaCrit);
            }else{
                $scope.criterias[$scope.criterias.length - 1].operador = "<=";
                var nuevaCrit = {'operador':'>','valor':(parseFloat($scope.criterias[$scope.criterias.length - 1].valor)),'precio':''};
                $scope.criterias.push(nuevaCrit);
            }
        }

        $scope.updateNext = function (index) {
            if($scope.criterias.length-2 == index || index == -1){
                $scope.criterias[$scope.criterias.length - 1].valor = (parseFloat($scope.criterias[$scope.criterias.length - 2].valor));
            }
        }

        $scope.removeRelation = function (index) {
            $scope.criterias.splice(index, 1);
            $scope.updateNext(-1);
        }

        initialize();
    }

})();