(function () {
    'use strict';
    angular.module('lacbus').service('terminalService', ['$http', '$q', terminalService]);

    function terminalService($http, $q) {

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
            getId   : getId
        }

    }

})();