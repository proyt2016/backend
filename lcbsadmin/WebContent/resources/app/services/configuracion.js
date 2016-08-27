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

        var updateConfig = function(configuracion){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/empresa/editarconfiguracion', configuracion)
            .success(function (conf) {
                defer.resolve(conf);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getConfiguracion : getConfiguracion,
            updateConfig     : updateConfig
        }

    }

})();