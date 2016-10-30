(function () {
    'use strict';
    angular.module('lacbus').service('reportesService', ["$http", "$q", reportesService]);

    function reportesService($http, $q) {

        var getPasajesVendidos = function(dateNow){
            var defer = $q.defer();
           
            //defer.resolve();
             $http.get('/lcbsapi/rest/viajes/gettotalpasajesvendidos/1/9998889/'+dateNow)
	    	 .success(function (pasajes) {
	    	     defer.resolve(pasajes);
	    	 })
	    	 .error(function(){
	    	     defer.reject('server error')
	     });
            return defer.promise;
        };

        var getEncomiendasPagas = function(dateNow){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getencomiendaspagas/1/99999899/'+dateNow)
            .success(function (encomiendas){
            defer.resolve(encomiendas);
            
        })
            .error(function(){
                defer.reject('server error')
            });
            return defer.promise;
        }

        return {
            getPasajesVendidos : getPasajesVendidos,
            getEncomiendasPagas : getEncomiendasPagas
        }

    }

})();