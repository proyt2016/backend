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
        
        var recargarCuponera = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/cargarcuponera', usuario)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var buscarUsuario = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/buscarusuariopormail/', usuario)
            .success(function (usr) {
                defer.resolve(usr);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            login   			: login,
            recargarCuponera 	: recargarCuponera,
            buscarUsuario 		: buscarUsuario
        }

    }

})();