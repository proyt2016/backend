(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', usuarioService]);

    function usuarioService($http, $q) {
    	
        var login = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/loginempleado', usuario)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            login   : login
        }

    }

})();