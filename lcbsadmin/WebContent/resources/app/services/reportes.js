(function () {
    'use strict';
    angular.module('lacbus').service('reportesService', ["$http", "$q", reportesService]);

    function reportesService($http, $q) {

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