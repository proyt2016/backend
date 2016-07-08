(function () {
    'use strict';
    angular.module('lacbus').service('pasajeService', ['$http', '$q', pasajeService]);

    function pasajeService($http, $q) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/listarhistorialpasajes/' + usuarioId + '/1/1000')
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/verdetallepasaje/' + id)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var comprar = function(pasaje){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/comprarpasaje', pasaje)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var cambiarHorario = function(pasaje){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/cambiarhorariopasaje', pasaje)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var transferir = function(pasaje){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/transferirpasaje', pasaje)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var reservar = function(pasaje){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/reservapasaje', pasaje)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll          : getAll,
            getId           : getId,
            comprar         : comprar,
            cambiarHorario  : cambiarHorario,
            transferir      : transferir,
            reservar        : reservar,
        }

    }

})();