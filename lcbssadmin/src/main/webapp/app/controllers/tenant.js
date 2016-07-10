(function () {
    'use strict';
    angular.module('lacbus').controller('tenantCtrl', ['$scope','$timeout','tenantSrv', tenantCtrl]);
    function tenantCtrl($scope,$timeout, service) {
    	$scope.tenants = [];
    	var factory = function(name, domain, id, isActive, isDelete){
	       	return{
	       		"name": name,
	 		  	"domain": domain,
	 		  	"id": id,
	 		  	"isActive": isActive,
	 		  	"isDelete": isDelete
	       	};		  
	   	 };
	   	 
    	$scope.tenantTmp = new factory();
    	 
    	$scope.headers = Object.keys($scope.tenantTmp);
    	$scope.create = function(){
    		service.create($scope.tenantTmp).then(function(data){
    			console.info(data);
    		});
    	};
    	$scope.list= function(){
    		service.list().then(function(list){
    			$scope.tenants = list;
    		});
    	};
    	$scope.toggleActiation = function(tenant){
    		if(tenant.isActive){
    			deactivate(tenant);
    		}else{
    			activate(tenant);
    		}
    	};
    	var activate= function(tenant){
    		service.activate(tenant).then(function(status){
    			$timeout(function(){

        			tenant.isActive = status;
    			},0); 
    		});
    	};
    	var deactivate= function(tenant){
    		service.deactivate(tenant).then(function(status){
    			$timeout(function(){

        			tenant.isActive = status;
    			},0); 
    		});
    	};
    	$scope.deleteT= function(tenant){
    		service.deletet(tenant).then(function(status){
    			$timeout(function(){

        			tenant.isActive = status;
    			},0); 
    		});
    	};
    	$scope.list();
    } 
})();

 