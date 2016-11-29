(function () {
    'use strict';
    angular.module('lacbus').service('vehiculosService', ["$http", "$q", vehiculosService]);

    function vehiculosService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/vehiculos/listarvehiculos/1/10000')
             .success(function (vehiculos) {
                 defer.resolve(vehiculos);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var getVehiculoMantenimiento = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/vehiculos/vehiculosenmantenimiento/1/10000')
             .success(function (vehiculos) {
                 defer.resolve(vehiculos);
             })
             .error(function(){
                 defer.reject('server error')
             });

            return defer.promise;
        };

        var add = function(vehiculo){
            var defer = $q.defer();
            $http.post('/lcbsapi/rest/vehiculos/altavehiculo', vehiculo)
            .success(function (vlo) {
                defer.resolve(vlo);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(vehiculo){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/vehiculos/editarvehiculo', vehiculo)
            .success(function (vlo) {
                defer.resolve(vlo);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            var aEliminar = { 'idVehiculo' : id}
            $http.post('/lcbsapi/rest/vehiculos/bajavehiculo',aEliminar)
            .success(function (vlo) {
                defer.resolve(vlo);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/vehiculos/getvehiculo/'+id)
            .success(function (vlo) {
                defer.resolve(vlo);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

         var guardarMantenimiento = function(id, mantenimiento){
            var defer = $q.defer();
            $http.post('/lcbsapi/rest/vehiculos/altamantenimiento/'+id,mantenimiento)
            .success(function (vlo) {
                defer.resolve(vlo);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var buscarCoche = function(usuario){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/vehiculos/obtenervehiculopornumero/' + usuario)
            .success(function (usr) {
                defer.resolve(usr);
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
            edit    : edit,
            guardarMantenimiento : guardarMantenimiento,
            getVehiculoMantenimiento : getVehiculoMantenimiento,
            buscarCoche : buscarCoche
        }

    }

})();