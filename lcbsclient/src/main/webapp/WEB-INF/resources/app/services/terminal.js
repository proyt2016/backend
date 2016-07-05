(function () {
    'use strict';
    angular.module('lacbus').service('terminalService', ['$http', '$q', terminalService]);

    function terminalService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getterminales/1/1000')
            .success(function (datos) {
                defer.resolve(datos);
            })
            .error(function(){
                defer.reject('server error');
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getterminal/' + id)
            .success(function (dato) {
                defer.resolve(dato);
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