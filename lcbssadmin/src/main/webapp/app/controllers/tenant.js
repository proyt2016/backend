(function () {
    'use strict';
    angular.module('lacbus').controller('tenantCtrl', ['$scope','$timeout','tenantSrv', '$pusher', 'toastr', tenantCtrl]);
   
    function tenantCtrl($scope,$timeout, service, $pusher, toastr) {
    	$scope.tenants = [];
    	var client = new Pusher('e782ddf887a873098d22');
		var pusher = $pusher(client);
		pusher.subscribe("sadmin");
		var events = [ "creado"];
		for (var i = 0; i < events.length; i++) {
			pusher.bind(events[i], function(data) {
				toastr.success(data.message, events[i]);
			});
		}
    	var factory = function(name, domain, id, isActive, isDelete, email){
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
	   	 
    	$scope.tenantTmp = new factory();
    	 
    	$scope.headers = Object.keys($scope.tenantTmp);
    	$scope.create = function(){
    		service.create($scope.tenantTmp).then(function(data){
    			$scope.tenantTmp = new factory();
    			location.href = "#/tenant";
    		}, function(){
    			toastr.error("Error intente más tarde", "Error");
    		});
    	};
    	$scope.list= function(){
    		service.list().then(function(list){
    			$scope.tenants = list;
    		}, function(){
    			toastr.error("Error intente más tarde", "Error");
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
    		}, function(){
    			toastr.error("Error intente más tarde", "Error");
    		});
    	};
    	var deactivate= function(tenant){
    		service.deactivate(tenant).then(function(status){
    			$timeout(function(){

        			tenant.isActive = status;
    			},0); 
    		}, function(){
    			toastr.error("Error intente más tarde", "Error");
    		});
    	};
    	$scope.deleteT= function(tenant){
    		service.deletet(tenant).then(function(status){
    			$timeout(function(){

        			tenant.isActive = status;
    			},0); 
    		}, function(){
    			toastr.error("Error intente más tarde", "Error");
    		});
    	};
    	$scope.list();
    } 
})();

 