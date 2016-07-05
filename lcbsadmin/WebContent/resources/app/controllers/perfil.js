(function () {
    'use strict';
    angular.module('lacbus').controller('perfilCtrl', ['$scope', '$routeParams', 'perfilService', perfilCtrl]);

    function perfilCtrl($scope, $routeParams, perfilService) {
        $scope.perfiles     = [];
        $scope.perfil     = null;
        $scope.showAlert    = false;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                console.info("tengo id"+id);
                perfilService.getId(id).then(function (data) {
                    $scope.perfil = data;
                });
            }else{
                console.info("no tengo id"+id);
                perfilService.getAll().then(function (data) {
                    $scope.perfiles = data;
                });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var perfil     = this.perfil;
            console.info(perfil);
            perfilService.add(perfil).then(
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
            var perfil     = this.perfil;
            perfilService.edit(perfil).then(
                function (data) {
                    $scope.saving = false;
                
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                }
            );
        }

        $scope.borrar = function (index) {
            console.log($scope.perfiles);
            $scope.saving = true;
            var perfil = this.perfil;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                perfilService.borrar(perfil.id).then(
                 function () {
                     $scope.perfiles.splice(index, 1);
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