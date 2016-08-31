(function () {
    'use strict';
    angular.module('lacbus').controller('configuracionCtrl', ['$scope', '$routeParams', 'configuracionService', configuracionCtrl]);

    function configuracionCtrl($scope, $routeParams, configuracionService) {
        $scope.configuracion     = null;
        $scope.saving    = false;
        $scope.emailsEmpresa = [];
        $scope.telsEmpresa = [];

        configuracionService.getConfiguracion().then(function (data) {
            $scope.configuracion = data;
            $scope.emailsEmpresa = $scope.configuracion.emails;
            $scope.telsEmpresa = $scope.configuracion.telefonos;
        });

        $scope.edit = function(){
            var configuracion     = this.configuracion;
            configuracion.emails = $scope.emailsEmpresa;
            configuracion.telefonos = $scope.telsEmpresa;
            configuracionService.updateConfig(configuracion).then(
                function (data) {
                	$scope.saving = false;
                }, function() {
                    $scope.saving = false;
                }
            );
        }

        $scope.addRelation = function (type) {
            if(type == 'email'){
                var nuevoEmail = { 'descripcion' : '', 'email' : '' };
                $scope.emailsEmpresa.push(nuevoEmail);
            }
            if(type == 'tels'){
                var nuevoTel = { 'descripcion' : '', 'telefono' : '' };
                $scope.telsEmpresa.push(nuevoTel);
            }
        }

        $scope.removeRelation = function (type, index) {
            if(type == 'email'){
                $scope.emailsEmpresa.splice(index, 1);
            }
            if(type == 'tels'){
                $scope.telsEmpresa.splice(index, 1);
            }
        }

    }

})();