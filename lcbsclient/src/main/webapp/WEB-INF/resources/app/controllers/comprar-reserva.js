(function () {
    'use strict';
    angular.module('lacbus').controller('comprarReservaCtrl', ['$scope', '$routeParams', comprarReservaCtrl]);

    function comprarReservaCtrl($scope, $routeParams) {
        var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
    	console.log('id', id);
    }

})();