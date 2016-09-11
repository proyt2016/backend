(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendasCtrl', ['$scope', '$routeParams', 'encomiendasService', encomiendasCtrl]);

    function encomiendasCtrl($scope, $routeParams, encomiendasService) {
        $scope.encomiendas = [];
        $scope.encomienda = {
        	origen : null,
        	destino : null,
        	reglaCobro : null
        };
        $scope.filtro = {
            origen : null,
            destino : null
        };
        $scope.showAlert = false;
        $scope.terminales = null;
        $scope.reglasCobro = null;
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
            }
            
            encomiendasService.getTerminales().then(function (data){
                $scope.terminales = data;
            });
            
            encomiendasService.getReglasCobro().then(function (data){
                $scope.reglasCobro = data;
            });
        }

        $scope.buscarUsuario = function(email, tipo){
        	var usuario = $scope[email];
            encomiendasService.buscarUsuario(usuario).then(function (data) {
                $scope[tipo] = data;
            });
        }
        
        $scope.buscar = function(){
            var filtro = $scope.filtro;
            if(filtro['fechaIngreso']){
                filtro.fechaIngreso = moment(filtro['fechaIngreso'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(filtro['destino'] == null){
                delete filtro['destino'];
            }

            if(filtro['origen'] == null){
                delete filtro['origen'];
            }

            encomiendasService.buscar(filtro).then(function (data) {
                $scope.encomiendas = data;
            });
        } 

        $scope.add = function(){
            $scope.saving = true;
            var encomienda = this.encomienda;
            
            if($scope.emisor != null && $scope.emisor != ''){
                encomienda["emisor"] = {
                	id : $scope.emisor.id
                };
            }
            
            if($scope.receptor != null && $scope.receptor != ''){
                encomienda["receptor"] = {
                	id : $scope.receptor.id
                };
            }
            
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
            $scope.saving = true;
            var encomienda = this.encomienda;
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

        $scope.calcularPrecio = function () {
            var encomienda = this.encomienda;
            
            if(encomienda['reglaCobro']){
            	encomiendasService.calcularPrecio(encomienda['reglaCobro'].id, encomienda.monto).then(
                    function (data) {
                        $scope.encomienda.precio = data;
                    }
                );
            }
        }

        initialize();
        
        $( document ).ready(function() {
            //Initialize Select2 Elements
            $('.select2').select2({
                placeholder: 'Seleccione',
                allowClear: true
            }).on('select2:select', function (evt) {
            	if(evt.currentTarget.name == 'reglaCobro') {
            		$scope.encomienda[evt.currentTarget.name] = {
            			id : evt.currentTarget.value
                    }
            	} else {
            		$scope.encomienda[evt.currentTarget.name] = {
            			id : evt.currentTarget.value,
            			tipo : 'Terminal'
                    }
            	}
            }).on("select2:unselect", function (evt) { 
              $scope.encomienda[evt.currentTarget.name] = null;
            });

            $('.select2-filtro').select2({
                placeholder: 'Seleccione',
                allowClear: true
            }).on('select2:select', function (evt) {
                $scope.filtro[evt.currentTarget.name] = {
                    id : evt.currentTarget.value,
                    tipo : 'Terminal'
                }
            }).on("select2:unselect", function (evt) { 
              $scope.filtro[evt.currentTarget.name] = null;
            });
        });
    }

})();