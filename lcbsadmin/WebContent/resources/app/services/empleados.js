(function () {
    'use strict';
    angular.module('lacbus').service('empleadosService', ["$http", "$q", empleadosService]);

    function empleadosService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

             $http.get('/lcbsapi/rest/usuarios/listarempleados/1/10000')
	    	 .success(function (empleados) {
	    		 var empleadoArray = [];
	    		 for(var i in empleados){
	    			 empleadoArray.push(empleados[i]);
	    		 }
	    		 console.log(empleadoArray);
	    	     defer.resolve(empleadoArray);
	    	 })
	    	 .error(function(){
	    	     defer.reject('server error')
	    	 });

            return defer.promise;
        };

        return {
            getAll : getAll
        }

    }

})();