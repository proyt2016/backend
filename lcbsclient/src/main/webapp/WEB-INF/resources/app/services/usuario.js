(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', usuarioService]);

    function usuarioService($http, $q) {

        var add = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/altausuario', usuario)
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

            $http.post('/lcbsapi/rest/usuarios/editarusuario', usuario)
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

            $http.get('/lcbsapi/rest/usuarios/getusuario/' + id)
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

            $http.post('/lcbsapi/rest/usuarios/loginusuario', usuario)
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