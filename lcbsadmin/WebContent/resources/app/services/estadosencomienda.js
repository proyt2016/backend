(function () {
    'use strict';
    angular.module('lacbus').service('estadosencomiendaService', ["$http", "$q", estadosencomiendaService]);

    function estadosencomiendaService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/encomiendas/listarestadosencomienda/1/10000')
             .success(function (estadosencomienda) {
                 defer.resolve(estadosencomienda);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(estadosencomienda){
            var defer = $q.defer();
            console.info(estadosencomienda);
            $http.post('/lcbsapi/rest/encomiendas/altaestadoencomienda', estadosencomienda)
            .success(function (estadosencomienda) {
                defer.resolve(estadosencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(estadosencomienda){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/encomiendas/editarestadoencomienda', estadosencomienda)
            .success(function (estadosencomienda) {
                defer.resolve(estadosencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            $http.delete('/lcbsapi/rest/encomiendas/borrarestadoencomienda/'+id)
            .success(function (estadosencomienda) {
                defer.resolve(estadosencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getestadoencomienda/'+id)
            .success(function (estadosencomienda) {
                defer.resolve(estadosencomienda);
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