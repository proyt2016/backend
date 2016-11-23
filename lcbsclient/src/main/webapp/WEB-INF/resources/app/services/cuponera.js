(function () {
    'use strict';
    angular.module('lacbus').service('cuponeraService', ['$http', '$q', 'CONFIGURACION', cuponeraService]);

    function cuponeraService($http, $q, CONFIGURACION) {

        var recargar = function(recarga){
            var defer = $q.defer();

            $http.post(CONFIGURACION.url + 'usuarios/cargarsaldocuponerastripe', recarga)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            recargar : recargar,
        }

    }

})();