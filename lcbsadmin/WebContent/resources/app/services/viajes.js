(function () {
    'use strict';
    angular.module('lacbus').service('viajeService', ["$http", "$q", viajesService]);

    function viajesService($http, $q) {

        var buscar = function(filtro){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/buscarviaje/1/1000', filtro)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error');
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getviaje/' + id)
            .success(function (dato) {
                defer.resolve(dato);
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

        var getTerminales = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getterminales/1/10000')
            .success(function (terminales) {
                defer.resolve(terminales);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var buscarUsuario = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/buscarusuariopormail/', usuario)
            .success(function (usr) {
                defer.resolve(usr);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var getPrecio = function(origenId, destinoId, recorridoId){
        	var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getpreciodepasaje/' + origenId + '/' + destinoId + '/' + recorridoId)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            buscar : buscar,
            getId : getId,
            comprar : comprar,
            cambiarHorario : cambiarHorario,
            transferir : transferir,
            reservar : reservar,
            getTerminales : getTerminales,
            buscarUsuario : buscarUsuario,
            getPrecio : getPrecio,
        }

    }

})();