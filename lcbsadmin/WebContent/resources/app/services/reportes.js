(function () {
    'use strict';
    angular.module('lacbus').service('reportesService', ["$http", "$q", reportesService]);

    function reportesService($http, $q) {

        var getAllPasajes = function(){
            var defer = $q.defer();

            defer.resolve();

             $http.get('/lcbsapi/rest/viajes/gettotalpasajesvendidos/1/10000')
	    	 .success(function (pasajes) {
	    	     defer.resolve(pasajes);
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