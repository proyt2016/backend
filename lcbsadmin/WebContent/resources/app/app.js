(function () {

    angular.module('atlas2', ['ngRoute']);

    angular.module('atlas2').config(['$routeProvider', configFunction]);

    /*@ngInject*/
    function configFunction($routeProvider) {
        // Routes
        $routeProvider.when("/", {
		    templateUrl : "/app/views/home.html",
		    controller  : 'homeCtrl'
		}).otherwise({
            redirectTo 	: '/'
        });

		// ruta de recursos
		$routeProvider.when("/recurso", {
		    templateUrl : "/app/views/recurso/list.html",
		    controller  : 'recursoCtrl'
		}).when("/recurso/add", {
		    templateUrl : "/app/views/recurso/add.html",
		    controller  : 'recursoCtrl'
		}).when("/recurso/edit/:id", {
		    templateUrl : "/app/views/recurso/edit.html",
		    controller  : 'recursoCtrl'
		});
    }

})();