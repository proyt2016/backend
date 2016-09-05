(function () {
    'use strict';
    angular.module('lacbus').service('encomiendasService', ["$http", "$q", encomiendasService]);

    function encomiendasService($http, $q) {

        var getTerminales = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/viajes/getterminales/1/10000')
            .success(function (terminales) {
                defer.resolve(terminales);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        var getReglasCobro = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getreglascobro/1/10000')
            .success(function (terminales) {
                defer.resolve(terminales);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
        var buscarUsuario = function(usuario){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/buscarusuariopormail/', usuario)
            .success(function (usr) {
                defer.resolve(usr);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var calcularPrecio = function(regla,monto){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getpreciodeencomienda/' + regla + '/' + monto)
            .success(function (prec) {
                defer.resolve(prec);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var buscar = function(filtro){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/encomiendas/buscarencomienda/1/10000', filtro)
            .success(function (encs) {
                defer.resolve(encs);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };
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
            getTerminales : getTerminales,
            getReglasCobro: getReglasCobro,
            buscarUsuario : buscarUsuario,
            calcularPrecio: calcularPrecio,
            buscar        : buscar,
            getAll        : getAll,
            getId         : getId,
            add           : add,
            borrar        : borrar,
            edit          : edit
        }

    }

})();