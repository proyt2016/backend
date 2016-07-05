(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', usuarioService]);

    function usuarioService($http, $q) {

        var add = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/altausuario/', usuario)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(id, usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/altausuario/' + id, usuario)
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

            $http.get('/lcbsapi/rest/usuarios/altausuario/' + id)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getId   : getId,
            add     : add,
            edit    : edit
        }

    }

})();