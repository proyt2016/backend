(function () {
    'use strict';
    angular.module('lacbus').service('notificacionService', ['$http', '$q', 'CONFIGURACION', notificacionService]);

    function notificacionService($http, $q, CONFIGURACION) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'usuarios/listanotificaciones/' + usuarioId)
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