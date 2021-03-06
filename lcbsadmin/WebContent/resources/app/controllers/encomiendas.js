﻿(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendasCtrl', ['$scope', '$routeParams', 'encomiendasService', '$localStorage', '$location', encomiendasCtrl]);

    function encomiendasCtrl($scope, $routeParams, encomiendasService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
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
                    
                    if(data.emisor){
                    	$scope.emisorStrg = data.emisor.email.email;
                    }
                    
                    if(data.receptor){
                    	$scope.receptorStrg = data.receptor.email.email;
                    }
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
            var filtro = angular.copy($scope.filtro);
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
            var encomienda = this.encomienda;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                encomiendasService.borrar(encomienda.id).then(
                 function () {
                     $scope.encomiendas.splice(index, 1);
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
        
        $scope.getEmisor = function(encomienda) {
        	if(encomienda.ciEmisor){
        		return encomienda.ciEmisor;
        	}
        	
        	if(encomienda.emisor){
        		return encomienda.emisor.nombrePila + ' ' + encomienda.emisor.apellido;
        	}
        	
        	return 'No usuario';
        }
        
        $scope.getReceptor = function(encomienda) {
        	if(encomienda.ciReceptor){
        		return encomienda.ciReceptor;
        	}
        	
        	if(encomienda.receptor){
        		return encomienda.receptor.nombrePila + ' ' + encomienda.receptor.apellido;
        	}
        	
        	return 'No usuario';
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
            
            $('.datepicker').daterangepicker({
            	singleDatePicker : true,
            	calender_style : "picker_2",
            	format : 'DD/MM/YYYY',
            }).on('apply.daterangepicker', function(ev, picker) {
            	$scope.filtro[ev.currentTarget.name] = picker.startDate.format('DD/MM/YYYY');
        	});
        });
    }
})();