(function () {
    'use strict';
    angular.module('lacbus').controller('recorridosCtrl', ['$scope', '$routeParams', 'recorridosService', 'puntosrecorridoService', '$localStorage', '$location', recorridosCtrl]);

    function recorridosCtrl($scope, $routeParams, recorridosService, puntosrecorridoService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        $scope.recorridos = [];
        $scope.recorrido = null;
        $scope.showAlert = false;
        $scope.puntosrecorridoLst = [];
        $scope.puntosDelRecorrido = [];
        $scope.tipodepunto = "Parada";

        var initialize = function(){
            var id = $routeParams && $routeParams['id'] ? $routeParams['id'] : null
            var idRec = $routeParams && $routeParams['idRec'] ? $routeParams['idRec'] : null
            if(id){
                recorridosService.getId(id).then(function (data) {
                    $scope.recorrido = data;
                    $scope.puntosDelRecorrido = data.puntosDeRecorrido;
                });
            }else{
                recorridosService.getAll().then(function (data) {
                    $scope.recorridos = data;
                });
            }
        }

        puntosrecorridoService.getTerminales().then(function (data) {
            $scope.puntosrecorridoLst = data;
            puntosrecorridoService.getParadas().then(function (data) {
                var newArray = $scope.puntosrecorridoLst.concat(data);
                $scope.puntosrecorridoLst = newArray;
                initMap();
            });
        });

        $scope.add = function(){
            $scope.saving   = true;
            var recorrido     = this.recorrido;
            recorrido.puntosDeRecorrido = JSON.parse(angular.toJson($scope.puntosDelRecorrido)); //para sacar los $$hashKey
            recorridosService.add(recorrido).then(
                function (data) {
                    $scope.saving = false;
                
                    //mostrarNotificacion('success');
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                    //mostrarNotificacion('error');
                }
            );
        }

        $scope.edit = function(){
            $scope.saving   = true;
            var recorrido     = this.recorrido;
            recorrido.puntosDeRecorrido = JSON.parse(angular.toJson($scope.puntosDelRecorrido)); //para sacar los $$hashKey
            recorridosService.edit(recorrido).then(
                function (data) {
                    $scope.saving = false;
                
                    //mostrarNotificacion('success');
                    window.history.back();
                }, function() {
                    $scope.saving = false;

                    //mostrarNotificacion('error');
                }
            );
        }

        $scope.borrar = function (index) {
            console.log($scope.recorridos);
            $scope.saving = true;
            var recorrido = this.recorrido;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                recorridosService.borrar(recorrido.id).then(
                 function () {
                     $scope.recorridos.splice(index, 1);
                     $scope.saving = false;

                 }, function () {
                     $scope.saving = false;

                 }
             );
            }
        }

        $scope.removeRelation = function (index) {
            $scope.puntosDelRecorrido.splice(index, 1);
        }

        var initMap = function () {
        	if(document.getElementById('map')){
        		setMapa(new google.maps.LatLng(parseFloat("-34.8940096615171"),parseFloat("-56.16642236709595")));
        	}
        }

        var setMapa = function (coords) {
          //Se crea una nueva instancia del objeto mapa
          var map = new google.maps.Map(document.getElementById('map'),
          {
            zoom: 13,
            center:coords
          });

          for(var i in $scope.puntosrecorridoLst){
            var lats = $scope.puntosrecorridoLst[i].ubicacionMapa.split(",");
            var icon;
            if($scope.puntosrecorridoLst[i].tipo == 'Terminal'){
                icon = 'https://maps.gstatic.com/mapfiles/ms2/micons/bus.png';
            }else{
                icon = 'https://maps.gstatic.com/mapfiles/ms2/micons/flag.png';
            }
            placeMarkerAndPanTo(new google.maps.LatLng(lats[0],lats[1]), map, $scope.puntosrecorridoLst[i].nombre,icon);
          }

          var flightPlanCoordinates = [];
          var inicioRecorrido = null;
          for(var i in $scope.puntosDelRecorrido){
            var lats = $scope.puntosDelRecorrido[i].ubicacionMapa.split(",");
            var ltgLngReco = new google.maps.LatLng(lats[0],lats[1]);
            if(inicioRecorrido == null)
            	inicioRecorrido = ltgLngReco;
            flightPlanCoordinates.push(ltgLngReco);
          }
          if(inicioRecorrido == null)
          	inicioRecorrido = coords;

          var flightPath = new google.maps.Polyline({
            path: flightPlanCoordinates,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
          });

          flightPath.setMap(map);
          map.panTo(inicioRecorrido);
        }

        var placeMarkerAndPanTo = function (latLng, map, title, icon) {
          var marker;
          if(icon != null){
              marker = new google.maps.Marker({
                position: latLng,
                map: map,
                title: title,
                icon: icon
              });
          }else{
              marker = new google.maps.Marker({
                position: latLng,
                map: map,
                title: title
              });
          }
          
          map.panTo(latLng);
              
          marker.addListener( 'click', function (event){
            puntosrecorridoService.getPorCoord(this.position.lat()+","+this.position.lng()).then(function (data) {
                $scope.puntosDelRecorrido.push(data);
            });
          })
        }

        initialize();
    }

})();