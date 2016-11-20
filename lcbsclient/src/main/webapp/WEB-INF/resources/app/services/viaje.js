(function () {
    'use strict';
    angular.module('lacbus').service('viajeService', ['$http', '$q', 'CONFIGURACION', viajeService]);

    function viajeService($http, $q, CONFIGURACION) {

        var buscar = function(filtro){
            var defer = $q.defer();

            $http.post(CONFIGURACION.url + 'viajes/buscarviaje/1/1000', filtro)
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

            $http.get(CONFIGURACION.url + 'viajes/getviaje/' + id)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var getPrecio = function(origenId, destinoId, recorridoId){
        	var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'viajes/getpreciodepasaje/' + origenId + '/' + destinoId + '/' + recorridoId)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            buscar  : buscar,
            getId   : getId,
            getPrecio : getPrecio
        }

    }

})();