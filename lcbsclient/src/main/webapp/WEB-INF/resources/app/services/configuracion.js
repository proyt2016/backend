(function () {
    'use strict';
    angular.module('lacbus').service('configuracionService', ["$http", "$q", "CONFIGURACION", configuracionService]);

    function configuracionService($http, $q, CONFIGURACION) {

        var getConfiguracion = function(){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'empresa/getconfirguacionempresa/')
            .success(function (configuracion) {
                defer.resolve(configuracion);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getConfiguracion : getConfiguracion
        }

    }

})();