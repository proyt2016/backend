(function () {
    'use strict';
    angular.module('lacbus').controller('viajesCtrl', ['$scope', '$routeParams', 'viajeService', '$location', viajesCtrl]);

    function viajesCtrl($scope, $routeParams, viajeService, $location) {
        $scope.filtro = {
            idOrigen : null,
            idDestino : null
        }
        $scope.viaje = null;
        $scope.comprar  = {
            pagos : 'paypal',
            cantidad : 1
        };
        $scope.usuario = null;
        $scope.comprador = null;
        $scope.resultados = null;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                viajeService.getId(id).then(function (datos) {
                    $scope.viaje = datos;

                    $scope.comprar['origen']    = '0';
                    $scope.comprar['destino']   = datos.recorrido.puntosDeRecorrido.length - 1 + '';
                });
            }
            
            viajeService.getTerminales().then(function (data){
                $scope.terminales = data;
            });
        }

        $scope.buscarUsuario = function(){
            var usuario = $scope.usuario;
            viajeService.buscarUsuario(usuario).then(function (data) {
                $scope.comprador = data;
            });
        }
        
        $scope.buscar = function(){
            var filtro = $scope.filtro;
            
            if(!filtro['fechaSalida']){
                mostrarNotificacion('error', 'Debe seleccionar una fecha');
                return;
            }
            
            if(filtro['fechaSalida']){
                filtro.fechaSalida = moment(filtro['fechaSalida'], 'DD/MM/YYYY').format('YYYY-MM-DD');
            }

            if(filtro['idDestino'] == null){
                delete filtro['idDestino'];
            }

            if(filtro['idOrigen'] == null){
                delete filtro['idOrigen'];
            }

            viajeService.buscar(filtro).then(function (data) {
                $scope.resultados = data;
            });
        } 

        $scope.procesarCompra = function () {
            var viaje = $scope.viaje;

            if(!this.comprar['origen']){
                mostrarNotificacion('error', 'Debe seleccionar un origen');
                return;
            }

            if(!this.comprar['destino']){
                mostrarNotificacion('error', 'Debe seleccionar un destino');
                return;
            }

            if(!this.comprar['cantidad']){
                mostrarNotificacion('error', 'Debe seleccionar la cantidad de pasajes');
                return;
            }

            var origen = viaje.recorrido.puntosDeRecorrido[this.comprar.origen];
            var destino = viaje.recorrido.puntosDeRecorrido[this.comprar.destino];

            var pasaje = {
                comprador : {
                    id : $scope.comprador.id
                },
                viaje : {
                    id : viaje.id 
                },
                origen : {
                    id : origen.id,
                    tipo : origen.tipo
                },
                destino : {
                    id : destino.id,
                    tipo : destino.tipo
                },
                fechaCompra : moment().format('YYYY-MM-DD') 
            }
            
            viajeService.comprar(pasaje).then(function (datos) {
                mostrarNotificacion('success', 'Su compra se realizo con exito, que disfrute su viaje.', 'Compra exitosa.');
                setTimeout(function(){ 
                    $location.url('/viajes');
                }, 3000);
            });
        }

        initialize();

        var mostrarNotificacion = function (tipo, text) {
            var title = '';

            if (tipo == 'success') {
                var title = 'Exito!';
                text || (text = 'Acción realizada con exito.');
            } else if (tipo == 'error') {
                var title = 'Oh No!';
                text || (text = 'Ha ocurrido un error.');
            }

            new PNotify({
                title: title,
                text: text,
                type: tipo,
                nonblock: {
                    nonblock: true
                }
            });
        }
        
        $( document ).ready(function() {
            $('.select2-filtro').select2({
                placeholder: 'Seleccione',
                allowClear: true
            }).on('select2:select', function (evt) {
                $scope.filtro[evt.currentTarget.name] = evt.currentTarget.value;
            }).on("select2:unselect", function (evt) { 
              $scope.filtro[evt.currentTarget.name] = null;
            });
        });
    }

})();