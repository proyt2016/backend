(function () {
    'use strict';
    angular.module('lacbus').service('puntosrecorridoService', ["$http", "$q", puntosrecorridoService]);

    function puntosrecorridoService($http, $q) {

        var getTerminales = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/viajes/getterminales/1/10000')
             .success(function (puntosrecorrido) {
                 defer.resolve(puntosrecorrido);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var getParadas = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/viajes/getparadas/1/10000')
             .success(function (puntosrecorrido) {
                 defer.resolve(puntosrecorrido);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var addEdit = function(puntosrecorrido, tipodepunto){
            var defer = $q.defer();
            var apiAUsar;
            if(tipodepunto == "Terminal"){
                if(puntosrecorrido.id == null){
                    apiAUsar = '/lcbsapi/rest/viajes/altaterminal';
                }else{
                    apiAUsar = '/lcbsapi/rest/viajes/editarterminal';
                }
            }else{
                if(puntosrecorrido.id == null){
                    apiAUsar = '/lcbsapi/rest/viajes/altaparada';
                }else{
                    apiAUsar = '/lcbsapi/rest/viajes/editarparada';
                }
            }
            $http.post(apiAUsar, puntosrecorrido)
            .success(function (ptorec) {
                defer.resolve(ptorec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            var aEliminar = { 'idRecorrido' : id}
            $http.post('/lcbsapi/rest/viajes/bajarecorrido',aEliminar)
            .success(function (ptorec) {
                defer.resolve(ptorec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getPorCoord = function(coord){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getpuntoporcoordenada/'+coord)
            .success(function (ptorec) {
                defer.resolve(ptorec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getTerminales  : getTerminales,
            getParadas  : getParadas,
            getPorCoord   : getPorCoord,
            addEdit     : addEdit,
            borrar  : borrar
        }

    }

})();