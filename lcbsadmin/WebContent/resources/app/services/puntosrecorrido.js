(function () {
    'use strict';
    angular.module('lacbus').service('puntosrecorridoService', ["$http", "$q", puntosrecorridoService]);

    function puntosrecorridoService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/viajes/listarrecorridos/1/10000')
             .success(function (puntosrecorrido) {
                 defer.resolve(puntosrecorrido);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(puntosrecorrido, tipodepunto){
            var defer = $q.defer();
            var apiAUsar;
            if(tipodepunto == "Terminal"){
                apiAUsar = '/lcbsapi/rest/viajes/altaterminal';
            }else{
                apiAUsar = '/lcbsapi/rest/viajes/altaparada';
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

        var edit = function(puntosrecorrido){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/editarrecorrido', puntosrecorrido)
            .success(function (rec) {
                defer.resolve(rec);
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

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getrecorrido/'+id)
            .success(function (ptorec) {
                defer.resolve(ptorec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll  : getAll,
            getId   : getId,
            add     : add,
            borrar  : borrar,
            edit    : edit
        }

    }

})();