(function () {
    'use strict';
    angular.module('lacbus').service('perfilService', ["$http", "$q", perfilService]);

    function perfilService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/usuarios/listarperfiles/1/10000')
             .success(function (perfil) {
                 defer.resolve(perfil);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(perfil){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/altaperfil', perfil)
            .success(function (perf) {
                defer.resolve(perf);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(perfil){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/editarperfil', perfil)
            .success(function (perf) {
                defer.resolve(perf);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            $http.delete('/lcbsapi/rest/usuarios/eliminarperfil/'+id)
            .success(function (perfil) {
                defer.resolve(perfil);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/usuarios/getperfil/'+id)
            .success(function (perfil) {
                defer.resolve(perfil);
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
            borrar  : borrar,
            edit    : edit
        }

    }

})();