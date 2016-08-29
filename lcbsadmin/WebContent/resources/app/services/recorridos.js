(function () {
    'use strict';
    angular.module('lacbus').service('recorridosService', ["$http", "$q", recorridosService]);

    function recorridosService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/viajes/listarrecorridos/1/10000')
             .success(function (recorridos) {
                 defer.resolve(recorridos);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(recorrido){
            var defer = $q.defer();
            $http.post('/lcbsapi/rest/viajes/crearrecorrido', recorrido)
            .success(function (rec) {
                defer.resolve(rec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(recorrido){
            var defer = $q.defer();
            console.info(recorrido);
            $http.post('/lcbsapi/rest/viajes/editarrecorrido', recorrido)
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
            .success(function (rec) {
                defer.resolve(rec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getrecorrido/'+id)
            .success(function (rec) {
                defer.resolve(rec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll        : getAll,
            getId         : getId,
            add           : add,
            borrar        : borrar,
            edit          : edit
        }

    }

})();