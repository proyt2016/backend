(function () {
    'use strict';
    angular.module('lacbus').service('viajeService', ['$http', '$q', viajeService]);

    function viajeService($http, $q) {

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

            $http.get('/lcbsapi/rest/viajes/getterminal/' + id)
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
            getId   : getId
        }

    }

})();