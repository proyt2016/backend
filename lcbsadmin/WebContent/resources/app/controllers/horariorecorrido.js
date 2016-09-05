(function () {
    'use strict';
    angular.module('lacbus').controller('horariorecorridoCtrl', ['$scope', '$routeParams', 'recorridosService', 'horariorecorridoService', horariorecorridoCtrl]);

    function horariorecorridoCtrl($scope, $routeParams, recorridosService, horariorecorridoService) {
        $scope.recorrido     = null;
        $scope.horario     = {'tipo':'diasSemana'};
        $scope.horarios       = [];
        $scope.horariosRows       = [];
        $scope.horas = [];
        $scope.minutos = [];

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            var idHorario = $routeParams && $routeParams['idHorario'] ? $routeParams['idHorario'] : null
            if(id){
              recorridosService.getId(id).then(function (data) {
                $scope.recorrido = data;
                $scope.horarios = data.horarios;
                if(idHorario){
                  for(var i = 0; i < $scope.horarios.length; i++){
                    if($scope.horarios[i].id == idHorario){
                      $scope.horario = $scope.horarios[i];
                      break;
                    }
                  }

                  if($scope.horario != null){
                    for(var i = 0; i < $scope.horario.horarios.length; i++){
                      var nuevohorario = {'nombre':$scope.horario.horarios[i].nombre};
                      $scope.horariosRows.push(nuevohorario);
                    }
                  }
                  $scope.horario.diasSemana = generarListaDias($scope.horario.diasSemana);
                  console.log($scope.horario.diasSemana);
                }
              });
            }
        }

        $scope.add = function(){
            $scope.saving   = true;
            var recorrido     = this.recorrido;
            var horario     = this.horario;
            horario.horarios = this.horariosRows;
            horario.diasSemana = limpiarListaDias(horario.diasSemana);
            horariorecorridoService.add(horario,recorrido.id).then(
                function (data) {
                    $scope.saving = false;
                
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                }
            );
        }

        var limpiarListaDias = function(obj){
          var result = [];
          if(obj.Lunes != null && obj['Lunes']){
            result.push('Lunes');
          }
          if(obj.Martes != null && obj['Martes']){
            result.push('Martes');
          }
          if(obj.Miercoles != null && obj['Miercoles']){
            result.push('Miercoles');
          }
          if(obj.Jueves != null && obj['Jueves']){
            result.push('Jueves');
          }
          if(obj.Viernes != null && obj['Viernes']){
            result.push('Viernes');
          }
          if(obj.Sabado != null && obj['Sabado']){
            result.push('Sabado');
          }
          if(obj.Domingo != null && obj['Domingo']){
            result.push('Domingo');
          }
          return result;
        }

        var generarListaDias = function(obj){
          console.log(obj);
          var result = new Object;
          if(obj.indexOf('Lunes') > -1){
            result['Lunes'] = true;
          }else{
            result['Lunes'] = false;
          }
          if(obj.indexOf('Martes') > -1){
            result['Martes'] = true;
          }else{
            result['Martes'] = false;
          }
          if(obj.indexOf('Miercoles') > -1){
            result['Miercoles'] = true;
          }else{
            result['Miercoles'] = false;
          }
          if(obj.indexOf('Jueves') > -1){
            result['Jueves'] = true;
          }else{
            result['Jueves'] = false;
          }
          if(obj.indexOf('Viernes') > -1){
            result['Viernes'] = true;
          }else{
            result['Viernes'] = false;
          }
          if(obj.indexOf('Sabado') > -1){
            result['Sabado'] = true;
          }else{
            result['Sabado'] = false;
          }
          if(obj.indexOf('Domingo') > -1){
            result['Domingo'] = true;
          }else{
            result['Domingo'] = false;
          }
          return result;
        }

        $scope.edit = function(){
            $scope.saving   = true;
            var recorrido     = this.recorrido;
            var horario     = this.horario;
            horario.horarios = this.horariosRows;
            horario.diasSemana = limpiarListaDias(horario.diasSemana);
            horariorecorridoService.edit(horario,recorrido.id).then(
                function (data) {
                    $scope.saving = false;
                }, function() {
                    $scope.saving = false;
                }
            );
        }

        $scope.borrar = function (index) {
            $scope.saving = true;
            var recorrido     = this.recorrido;
            var horario     = this.horario;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                horariorecorridoService.borrar($scope.horarios[index].id,recorrido.id).then(
                 function () {
                     $scope.horarios.splice(index, 1);
                     $scope.saving = false;

                 }, function () {
                     $scope.saving = false;

                 }
             );
            }
        }

        $scope.addRelation = function () {
          var nuevohorario = {'nombre':'00:00'};
          $scope.horariosRows.push(nuevohorario);
        }

        $scope.removeRelation = function (index) {
          $scope.horariosRows.splice(index, 1);
        }

        initialize();

    }

})();