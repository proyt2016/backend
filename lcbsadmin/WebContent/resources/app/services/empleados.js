(function () {
    'use strict';
    angular.module('lacbus').service('empleadosService', ["$http", "$q", empleadosService]);

    function empleadosService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/usuarios/listarempleados/1/10000')
	    	 .success(function (empleados) {
	    	     defer.resolve(empleados);
	    	 })
	    	 .error(function(){
	    	     defer.reject('server error')
	    	 });

            return defer.promise;
        };

        var add = function(empleado){
            var defer = $q.defer();
            console.info(empleado);
            $http.post('/lcbsapi/rest/usuarios/altaempleado', empleado)
            .success(function (emp) {
                defer.resolve(emp);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var edit = function(empleado){
            var defer = $q.defer();

            $http.post('/lcbsapi/rest/usuarios/editarempleado', empleado)
            .success(function (emp) {
                defer.resolve(emp);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var borrar = function(id){
            var defer = $q.defer();
            var aEliminar = { 'idEmpleado' : id}
            $http.post('/lcbsapi/rest/usuarios/bajaempleado',aEliminar)
            .success(function (emp) {
                defer.resolve(emp);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getId = function(id){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/usuarios/getempleado/'+id)
            .success(function (emp) {
                defer.resolve(emp);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        var getConfiguracion = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/empresa/getconfirguacionempresa/')
            .success(function (configuracion) {
                defer.resolve(configuracion);
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
            getConfiguracion : getConfiguracion
        }

    }

})();