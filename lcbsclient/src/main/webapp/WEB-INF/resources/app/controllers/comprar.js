(function () {
    'use strict';
    angular.module('lacbus').controller('comprarCtrl', ['$scope', '$routeParams', comprarCtrl]);

    function comprarCtrl($scope, $routeParams) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
    	console.log('id', id);
    }

})();