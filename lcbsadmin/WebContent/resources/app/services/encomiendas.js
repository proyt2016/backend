(function () {
    'use strict';
    angular.module('lacbus').service('encomiendasService', ["$http", "$q", encomiendasService]);

    function encomiendasService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/encomiendas/getencomiendas/1/10000')
             .success(function (encomiendas) {
                 defer.resolve(encomiendas);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(encomienda){
            var defer = $q.defer();
            console.info(encomienda);
            $http.post('/lcbsapi/rest/encomiendas/altaencomienda', encomienda)
            .success(function (enc) {
                defer.resolve(enc);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(encomienda){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/encomiendas/editarencomienda', encomienda)
            .success(function (enc) {
                defer.resolve(enc);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            $http.delete('/lcbsapi/rest/encomiendas/borrarencomienda',id)
            .success(function (enc) {
                defer.resolve(enc);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getencomienda/'+id)
            .success(function (enc) {
                defer.resolve(enc);
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