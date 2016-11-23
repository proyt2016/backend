(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', 'CONFIGURACION', usuarioService]);

    function usuarioService($http, $q, CONFIGURACION) {
    	
    	var getAll = function(id){
            var defer = $q.defer();

            $http.get(CONFIGURACION.url + 'usuarios/listarusuarios/1/1000')
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var add = function(usuario){
            var defer = $q.defer();

            $http.post(CONFIGURACION.url + 'usuarios/altausuario', usuario)
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

            $http.post(CONFIGURACION.url + 'usuarios/editarusuario', usuario)
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

            $http.get(CONFIGURACION.url + 'usuarios/getusuario/' + id)
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

            $http.post(CONFIGURACION.url + 'usuarios/loginusuario', usuario)
            .success(function (dato) {
                defer.resolve(dato);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        
        var agregarTarjeta = function(usuario){
            var defer = $q.defer();

            $http.post(CONFIGURACION.url + 'usuarios/guardartokenusuario/', usuario)
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getId   		: getId,
            getAll  		: getAll,
            add     		: add,
            edit    		: edit,
            login   		: login,
            agregarTarjeta 	: agregarTarjeta
        }

    }

})();