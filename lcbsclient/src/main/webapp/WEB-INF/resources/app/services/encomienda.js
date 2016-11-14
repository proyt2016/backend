(function () {
    'use strict';
    angular.module('lacbus').service('encomiendaService', ['$http', '$q', 'CONFIGURACION', encomiendaService]);

    function encomiendaService($http, $q, CONFIGURACION) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'encomiendas/listarencomiendasusuario/' + usuarioId + '/1/1000')
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll : getAll
        }

    }

})();