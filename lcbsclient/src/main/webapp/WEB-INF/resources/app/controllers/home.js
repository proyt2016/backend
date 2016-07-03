(function () {
    'use strict';
    angular.module('lacbus').controller('homeCtrl', ['$scope', homeCtrl]);

    function homeCtrl($scope) {
        $scope.reservar = function (info) {
            // $scope.info = info;
            $('#modal-reservar').modal('show');
        }

        $('#modal-reservar').on('hidden.bs.modal', function (e) {

        });
    }

})();