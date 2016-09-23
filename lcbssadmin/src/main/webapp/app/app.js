(function () {

    angular.module('lacbus', ['ngRoute', 'ngAnimate', 'toastr']);

    angular.module('lacbus').config(['$routeProvider', 'toastrConfig', configFunction]);

    /*@ngInject*/
    function configFunction($routeProvider, toastrConfig) {
        angular.extend(toastrConfig, {
            positionClass: 'toast-top-center',
            preventDuplicates: true,
            preventOpenDuplicates: true,
            timeOut: 2500
        });

        // Routes
        $routeProvider.when('/tenant', {
            templateUrl : 'app/views/tenant/list.html',
            controller  : 'tenantCtrl'
        }).otherwise({
            redirectTo  : '/tenant'
        });

        $routeProvider.when('/tenant/create', {
            templateUrl: 'app/views/tenant/create.html',
            controller: 'tenantCtrl'
        });
    }

})();