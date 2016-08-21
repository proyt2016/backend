(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendasCtrl', ['$scope', '$routeParams', 'encomiendasService', encomiendasCtrl]);

    function encomiendasCtrl($scope, $routeParams, encomiendasService) {
        $scope.encomiendas     = [];
        $scope.encomienda     = null;
        $scope.filtro     = null;
        $scope.showAlert    = false;
        $scope.terminales = [];
        $scope.reglasCobro = [];
        $scope.emisorStrg = null;
        $scope.receptorStrg = null;
        $scope.emisor = null;
        $scope.receptor = null;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                encomiendasService.getId(id).then(function (data) {
                    $scope.encomienda = data;
                });
            }else{
                
            }
            encomiendasService.getTerminales().then(function (data){
                $scope.terminales = data;
            });
            encomiendasService.getReglasCobro().then(function (data){
                $scope.reglasCobro = data;
            });
        }

        $scope.buscarEmisor = function(){
            encomiendasService.buscarUsuario($scope.emisorStrg).then(function (data) {
                $scope.emisor = data;
                if($scope.emisor == null || $scope.emisor == '')
                    alert('Usuario incorrecto');
            });
        }

        $scope.buscarReceptor = function(){
            encomiendasService.buscarUsuario($scope.receptorStrg).then(function (data) {
                $scope.receptor = data;
                if($scope.receptor == null || $scope.receptor == '')
                    alert('Usuario incorrecto');
            });
        }

        $scope.buscarReceptor = function(){
            encomiendasService.buscarUsuario($scope.receptorStrg).then(function (data) {
                $scope.receptor = data;
                if($scope.receptor == null || $scope.receptor == '')
                    alert('Usuario incorrecto');
            });
        }

        $scope.buscar = function(){
            var filtro = $scope.filtro;
            var fecha = filtro['fechaIngreso'];
            if(filtro['fechaIngreso']){
                filtro.fechaIngreso = moment(filtro['fechaIngreso'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }
            encomiendasService.buscar(filtro).then(function (data) {
                $scope.encomiendas = data;
                filtro.fechaIngreso = fecha;
            });

        } 

        $scope.add = function(){
            $scope.saving   = true;
            var encomienda     = this.encomienda;
            encomienda["origen"] = $scope.terminales[encomienda["origen"]];
            encomienda["destino"] = $scope.terminales[encomienda["destino"]];
            encomienda["reglaCobro"] = $scope.reglasCobro[encomienda["reglaCobro"]];
            if($scope.emisor != null && $scope.emisor != '')
                encomienda["emisor"] = $scope.emisor;
            if($scope.receptor != null && $scope.receptor != '')
                encomienda["receptor"] = $scope.receptor;
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