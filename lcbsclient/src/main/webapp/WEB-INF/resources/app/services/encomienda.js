(function () {
    'use strict';
    angular.module('lacbus').service('encomiendaService', ['$http', '$q', encomiendaService]);

    function encomiendaService($http, $q) {

        var getAll = function(usuarioId){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/listarencomiendasusuario/' + usuarioId + '/1/1000')
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