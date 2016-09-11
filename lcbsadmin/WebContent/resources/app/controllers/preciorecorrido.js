(function () {
    'use strict';
    angular.module('lacbus').controller('preciorecorridoCtrl', ['$scope', '$routeParams', 'recorridosService', preciorecorridoCtrl]);

    function preciorecorridoCtrl($scope, $routeParams, recorridosService) {
        $scope.recorrido = null;
        $scope.precios = [];
        $scope.terminales = null;

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            if(id){
                recorridosService.getId(id).then(function (data) {
                    $scope.recorrido = data;
                    $scope.precios = data.precios;
                    $scope.terminales = data.puntosDeRecorrido;
                    
                    for(var i = 0;i < $scope.precios.length;i++){
                      var ori = $scope.precios[i].origen.id;
                      var des = $scope.precios[i].destino.id;
                      
                      for(var j = 0;j < $scope.terminales.length;j++){
                    	var term = $scope.terminales[j].id;
                        if(ori == term){
                          $scope.precios[i].origen = term;
                        }
                        
                        if(des == term){
                          $scope.precios[i].destino = term;
                        }
                      }
                    }
                });
            }
        }

        $scope.edit = function(){
            $scope.saving = true;
            var recorrido = this.recorrido;
            //encomienda["origen"] = $scope.terminales[encomienda["origen"]];
            for(var i = 0; i < $scope.precios.length; i++){
              $scope.precios[i]["origen"] = {
               id : $scope.precios[i]["origen"],
               tipo : 'Terminal'
              }
              $scope.precios[i]["destino"] = {
        		  id : $scope.precios[i]["destino"],
        		  tipo : 'Terminal'
              }
            }
            recorrido.precios = JSON.parse(angular.toJson($scope.precios)); //para sacar los $$hashKey
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

        $scope.addRelation = function () {
          var nuevoPrecio = {'origen':'','destino':'', 'monto': ''};
          $scope.precios.push(nuevoPrecio);
        }

        $scope.removeRelation = function (index) {
          $scope.precios.splice(index, 1);
        }

        initialize();

    }

})();