(function () {
    'use strict';
    angular.module('lacbus').service('horariorecorridoService', ["$http", "$q", horariorecorridoService]);

    function horariorecorridoService($http, $q) {

        var add = function(horario,recorridoId){
            var defer = $q.defer();
            $http.post('/lcbsapi/rest/viajes/crearhorarioenrecorrido/'+recorridoId, horario)
            .success(function (hor) {
                defer.resolve(hor);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(horario,recorridoId){
            var defer = $q.defer();
            $http.post('/lcbsapi/rest/viajes/editarhorarioenrecorrido/'+recorridoId, horario)
            .success(function (hor) {
                defer.resolve(hor);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(horarioId,recorridoId){
            var defer = $q.defer();
            var aEliminar = { 'idHorarioRecorrido' : horarioId}
            $http.post('/lcbsapi/rest/viajes/borrarhorarioenrecorrido/'+recorridoId,aEliminar)
            .success(function (hor) {
                defer.resolve(hor);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            add           : add,
            borrar        : borrar,
            edit          : edit
        }

    }

})();