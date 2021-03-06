(function () {
    'use strict';
    angular.module('lacbus').service('tenantSrv', ['$http', '$q', 'routesUrls', tenantService]);
    angular.module('lacbus').factory('tenantFry', tenantFactory); 
   
    var tenantFactory = function(){
   	 return function(name, domain, id, isActive, isDelete, email){
       	return{
       		"name": name,
 		  	"domain": domain,
 		  	"id": id,
 		  	"isActive": isActive,
 		  	"isDelete": isDelete,
 		  	"email": {
 		  		"email": email
 		  	}
       	};		  
   	 };
    };
    function tenantService($http, $q, routesUrls) {

        var list = function(){
            var defer = $q.defer();
            $http.get(routesUrls.tenant.list)
            .success(function (tenantList) {
                defer.resolve(tenantList);
            })
            .error(function(){
                defer.reject('Imposible listar tenants')
            });

            return defer.promise;
        };

        var create = function(tenant){
            var defer = $q.defer();
            $http.post(routesUrls.tenant.create, tenant)
            .success(function (tenant) {
                defer.resolve(tenant);
            })
            .error(function(){
                defer.reject('Imposible crear tenant')
            });

            return defer.promise;
        };
        var activate = function(tenant){
            var defer = $q.defer();
            $http.post(routesUrls.tenant.activate, tenant)
            .success(function (tenant) {
                defer.resolve(tenant);
            })
            .error(function(){
                defer.reject('Imposible ejecutar accion: \'activar\'');
            });

            return defer.promise;
        };
        var deactivate = function(tenant){
            var defer = $q.defer();
            $http.post(routesUrls.tenant.deactivate, tenant)
            .success(function (tenant) {
                defer.resolve(tenant);
            })
            .error(function(){

                defer.reject('Imposible ejecutar accion: \'desactivar\'');
       
            });

            return defer.promise;
        };
        var healt = function(tenant){
        	var defer = $q.defer();
            $http.post(routesUrls.tenant.healt, tenant)
            .success(function (tenant) {
                defer.resolve(tenant);
            })
            .error(function(){
                defer.reject('Tenant no habilitado o con problemas');
            });

            return defer.promise;
    	};
        var deleteTenant = function(tenant){
            var defer = $q.defer();
            $http.post(routesUrls.tenant.delete, tenant)
            .success(function (tenant) {
                defer.resolve(tenant);
            })
            .error(function(){
                defer.reject('Imposible ejecutar accion: \'borrar\'');
            });

            return defer.promise;
        };
        
        return {
        	healt: healt,
        	list: list,
        	create: create,
            activate: activate,
            deactivate: deactivate,
            deletet: deleteTenant
        }

    }

})();