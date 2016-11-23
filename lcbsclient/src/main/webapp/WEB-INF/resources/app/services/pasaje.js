(function () {
    'use strict';
    angular.module('lacbus').service('pasajeService', ['$http', '$q', 'CONFIGURACION', pasajeService]);

    function pasajeService($http, $q, CONFIGURACION) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'viajes/listarhistorialpasajes/' + usuarioId + '/1/1000')
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

            $http.get(CONFIGURACION.url + 'viajes/verdetallepasaje/' + id)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var comprar = function(pasaje, tipo){
            var defer = $q.defer();
            
            var url = CONFIGURACION.url + 'viajes/comparapasajecuponera';
            if(tipo == 'stripe'){
            	url = CONFIGURACION.url + 'viajes/comprarpasajestripe'
            }

            $http.post(url, pasaje)
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

            $http.post(CONFIGURACION.url + 'viajes/cambiarhorariopasaje', pasaje)
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

            $http.post(CONFIGURACION.url + 'viajes/transferirpasaje', pasaje)
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

            $http.post(CONFIGURACION.url + 'viajes/reservapasaje', pasaje)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var listarHorarios = function(pasajeId){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'viajes/listarviajescambiohorario/' + pasajeId)
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
            listarHorarios 	: listarHorarios,
        }

    }

})();