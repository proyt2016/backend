(function () {
    'use strict';
    angular.module('lacbus').service('encomiendasService', ["$http", "$q", encomiendasService]);

    function encomiendasService($http, $q) {

        var getAll = function(){
            var defer = $q.defer();

            defer.resolve();

            // $http.get('api/employee')
	    	// .success(function (employees) {
	    	//     defer.resolve(employees);
	    	// })
	    	// .error(function(){
	    	//     defer.reject('server error')
	    	// });

            return defer.promise;
        };

        return {
            getAll : getAll
        }

    }

})();