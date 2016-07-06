(function () {
	'use strict';
	angular.module('lacbus').constant("routesUrls",
		{ 
			"tenant":{
				"list": "/lcbsapi/rest/tenant/list",
				"create": "/lcbsapi/rest/tenant/create",
				"activate": "/lcbsapi/rest/tenant/activate",
				"deactivate": "/lcbsapi/rest/tenant/deactivate",
				"delete": "/lcbsapi/rest/tenant/delete"
			},
		    "user":{
		        "create": "/lcbsapi/rest/usuarios/altausuario",
		        "edit": "/lcbsapi/rest/usuarios/editarusuario",
		        "get": "/lcbsapi/rest/usuarios/getusuario",
		        "login": "/lcbsapi/rest/usuarios/loginusuario"
		    }
  });
	 
})();