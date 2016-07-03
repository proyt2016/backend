(function () {
    'use strict';
    angular.module('lacbus').controller('encomiendaCtrl', ['$scope', 'toastr', encomiendaCtrl]);

    function encomiendaCtrl($scope, toastr) {
    	toastr.success('Hello world!', 'Toastr fun!');
    }

})();