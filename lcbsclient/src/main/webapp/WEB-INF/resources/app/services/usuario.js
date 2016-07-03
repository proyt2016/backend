(function () {
    'use strict';
    angular.module('lacbus').service('usuarioService', ['$http', '$q', usuarioService]);

    function usuarioService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

            $http.get('/admin/api/usuario')
            .success(function (usuarios) {
                defer.resolve(usuarios);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var add = function(usuario){
            var defer = $q.defer();

            $http.post('/admin/api/usuario', usuario)
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

            $http.post('/admin/api/usuario/' + id, usuario)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();

            $http.post('/admin/api/usuario/' + id)
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

            $http.get('/admin/api/usuario/' + id)
            .success(function (usuario) {
                defer.resolve(usuario);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll  : getAll,
            getId   : getId,
            add     : add,
            delete  : borrar,
            edit    : edit,
        }

    }

})();