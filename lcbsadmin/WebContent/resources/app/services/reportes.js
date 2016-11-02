(function () {
    'use strict';
    angular.module('lacbus').service('reportesService', ["$http", "$q", reportesService]);

    function reportesService($http, $q) {

        var getPasajesVendidos = function(){
            var defer = $q.defer();
           
            //defer.resolve();
             $http.get('/lcbsapi/rest/viajes/getpasajes/1/9998889')
	    	 .success(function (pasajes) {
	    	     defer.resolve(pasajes);
	    	 })
	    	 .error(function(){
	    	     defer.reject('server error')
	     });
            return defer.promise;
        };

        var getEncomiendasPagas = function(){
            var defer = $q.defer();

            $http.get('/lcbsapi/rest/encomiendas/getencomiendas/1/99999899')
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