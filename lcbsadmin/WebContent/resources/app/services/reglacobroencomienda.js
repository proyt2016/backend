(function () {
    'use strict';
    angular.module('lacbus').service('reglacobroencomiendaService', ["$http", "$q", reglacobroencomiendaService]);

    function reglacobroencomiendaService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/encomiendas/getreglascobro/1/10000')
             .success(function (reglacobroencomienda) {
                 defer.resolve(reglacobroencomienda);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(reglacobroencomienda){
            var defer = $q.defer();
            console.info(reglacobroencomienda);
            $http.post('/lcbsapi/rest/encomiendas/altareglacobro', reglacobroencomienda)
            .success(function (reglacobroencomienda) {
                defer.resolve(reglacobroencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(reglacobroencomienda){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/encomiendas/editarreglacobroencomienda', reglacobroencomienda)
            .success(function (reglacobroencomienda) {
                defer.resolve(reglacobroencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            $http.delete('/lcbsapi/rest/encomiendas/borrarreglacobroencomienda/'+id)
            .success(function (reglacobroencomienda) {
                defer.resolve(reglacobroencomienda);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getreglacobro/'+id)
            .success(function (reglacobroencomienda) {
                defer.resolve(reglacobroencomienda);
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