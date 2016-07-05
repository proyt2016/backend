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

            $http.post('/lcbsapi/rest/usuarios/altaempleado', empleado)
            .success(function (emp) {
                defer.resolve(emp);
            })
            .error(function(){
                defer.reject('server error')
            });

            return defer.promise;
        };

        return {
            getAll : getAll,
            add     : add
        }

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

        return {
            getAll : getAll,
            add     : add,
            edit     : edit
        }

    }

})();