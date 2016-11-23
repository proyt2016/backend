(function () {
    'use strict';
    angular.module('lacbus').service('reservaService', ['$http', '$q', reservaService]);

    function reservaService($http, $q) {
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
        
        var buscarReserva = function(reserva){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/viajes/buscarreservas', reserva)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getId   		: getId,
            comprar			: comprar,
            buscarReserva 	: buscarReserva
        }

    }

})();