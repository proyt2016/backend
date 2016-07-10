(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', 'routesUrls', usuarioService]);
  /*  .factory('usuarioFactory', [usuarioFactory]);
    var usuarioFactory = function(name, domain, id, isActive, isDelete){
    	return {
    		  "id": null,
    		  "nombrePila": null,
    		  "apellido": null,
    		  "email": null,
    		  "telefonosContacto": null,
    		  "fechaNacimiento": null,
    		  "eliminado": false,
    		  "clave": null, 
		}
    };*/
    function usuarioService($http, $q, routesUrls) {

        var add = function(usuario){
            var defer = $q.defer();

            $http.post(routesUrls.user.create, usuario)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(usuario){
            var defer = $q.defer();

            $http.post(routesUrls.user.edit, usuario)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get(routesUrls.user.get+ "/" + id)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var login = function(usuario){
            var defer = $q.defer();

            $http.post(routesUrls.user.login, usuario)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getId   : getId,
            add     : add,
            edit    : edit,
            login   : login
        }

    }

})();