(function () {
    'use strict';
    angular.module('lacbus').service('cuponeraService', ['$http', '$q', cuponeraService]);

    function cuponeraService($http, $q) {

        var recargar = function(recarga){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/cargarcuponera', recarga)
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