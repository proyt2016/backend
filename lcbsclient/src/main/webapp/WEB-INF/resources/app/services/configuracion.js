(function () {
    'use strict';
    angular.module('lacbus').service('configuracionService', ["$http", "$q", configuracionService]);

    function configuracionService($http, $q) {

        var getConfiguracion = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/empresa/getconfirguacionempresa/')
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