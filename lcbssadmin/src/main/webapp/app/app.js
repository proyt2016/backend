(function () {

    angular.module('lacbus', ['ngRoute', 'ngAnimate', 'toastr', "pusher-angular", 'ngStorage']);

    angular.module('lacbus').config(['$routeProvider', 'toastrConfig', configFunction]);
    
    angular.module('lacbus').controller('appCtrl', ['$scope', '$location', '$localStorage', appCtrl]);

    /*@ngInject*/
    function configFunction($routeProvider, toastrConfig) {
        angular.extend(toastrConfig, {
            positionClass: 'toast-top-center',
            preventDuplicates: true,
            preventOpenDuplicates: true,
            timeOut: 2500
        });
        $routeProvider.when('/tenant/create', {
            templateUrl: 'app/views/tenant/create.html',
            controller: 'tenantCtrl'
        });
        
        $routeProvider.when('/login', {
            templateUrl: 'app/views/login.html',
            controller: 'loginCtrl'
        });
        
        // Routes
        $routeProvider.when('/tenant', {
            templateUrl : 'app/views/tenant/list.html',
            controller  : 'tenantCtrl'
        }).otherwise({
            redirectTo  : '/login'
        });

       
        
    };
    
    function appCtrl($scope, $location, $localStorage) {
        $scope.usuarioLogueado = $localStorage.usuarioLogueado;
        
        $scope.logout = function(){
        	$localStorage.$reset();
        	$scope.usuarioLogueado = null;
        	$location.url('/login');
        }
        
		$scope.$on('localStorage:changed', function(event, data) {
			$scope.usuarioLogueado = $localStorage.usuarioLogueado;
		});

    }
    
    angular.element(document).ready(function() {
        angular.bootstrap(document, ['lacbus']);
    });

})();