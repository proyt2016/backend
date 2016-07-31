(function () {
    'use strict';
    angular.module('lacbus').service('reservaService', ['$http', '$q', reservaService]);

    function reservaService($http, $q) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/listarreservas/' + usuarioId)
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

            $http.get('/lcbsapi/rest/viajes/getreserva/' + id)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var cancelar = function(id){
            var defer = $q.defer();

            $http.delete('/lcbsapi/rest/viajes/cancelarreserva/' + id)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var comprar = function(reserva){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/comprarpasajereservado', reserva)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll  	: getAll,
            getId   	: getId,
            cancelar 	: cancelar,
            comprar		: comprar
        }

    }

})();