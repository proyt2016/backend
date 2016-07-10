(function () {
    'use strict';
    angular.module('lacbus').service('notificacionService', ['$http', '$q', notificacionService]);

    function notificacionService($http, $q) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/usuarios/listanotificaciones/' + usuarioId)
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