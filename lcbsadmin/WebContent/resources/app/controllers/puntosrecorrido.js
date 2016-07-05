(function () {
    'use strict';
    angular.module('lacbus').controller('puntosrecorridoCtrl', ['$scope', '$routeParams', 'puntosrecorridoService', puntosrecorridoCtrl]);

    function puntosrecorridoCtrl($scope, $routeParams, puntosrecorridoService) {
        $scope.puntosrecorridoLst     = [];
        $scope.puntosrecorrido     = null;
        $scope.tipodepunto = null;
        $scope.showAlert    = false;

        
        puntosrecorridoService.getAll().then(function (data) {
            $scope.puntosrecorridoLst = data;
        });

        $scope.add = function(){
            $scope.saving   = true;
            var puntosrecorrido     = this.puntosrecorrido;
            var tipodepunto = this.tipodepunto;
            console.info(puntosrecorrido);
            puntosrecorridoService.add(puntosrecorrido, tipodepunto).then(
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
            var puntosrecorrido     = this.puntosrecorrido;
            puntosrecorridoService.edit(puntosrecorrido).then(
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
            console.log($scope.puntosrecorridoLst);
            $scope.saving = true;
            var puntosrecorrido = this.puntosrecorrido;

            var r = confirm("Seguro que quiere borrar?");
            if (r == true) {
                puntosrecorridoService.borrar(recorrido.id).then(
                 function () {
                     $scope.puntosrecorridoLst.splice(index, 1);
                     $scope.saving = false;

                 }, function () {
                     $scope.saving = false;

                 }
             );
            }
        }

        $scope.initMap = function () {

            //usamos la API para geolocalizar el usuario
            navigator.geolocation.getCurrentPosition(
              function (position){
                var coords =  {
                  lng: position.coords.longitude,
                  lat: position.coords.latitude
                };
                $scope.setMapa(coords);  //pasamos las coordenadas al metodo para crear el mapa
                
               
              },function(error){console.log(error);});
            
        }


        $scope.setMapa = function (coords) {
          //Se crea una nueva instancia del objeto mapa
          var map = new google.maps.Map(document.getElementById('map'),
          {
            zoom: 13,
            center:new google.maps.LatLng(coords.lat,coords.lng),

          });

          map.addListener('click', function(e) {
            $scope.placeMarkerAndPanTo(e.latLng, map);
          });
        }

        $scope.placeMarkerAndPanTo = function (latLng, map) {
          var marker = new google.maps.Marker({
            position: latLng,
            draggable: true,
            map: map
          });

          map.panTo(latLng);

          $('#ubicacionMapa').val(marker.position.lat()+","+marker.position.lng()).trigger('input');
              
          marker.addListener( 'click', function (event){
            $('#ubicacionMapa').val(this.position.lat()+","+this.position.lng()).trigger('input');
          })
          

          marker.addListener( 'dragstart', function (event)
              {
                //escribimos las coordenadas de la posicion actual del marcador dentro del input #coords
                document.getElementById("coordsOrig").value = this.getPosition().lat()+","+ this.getPosition().lng();
              });

          marker.addListener( 'dragend', function (event)
              {
                this.position = new google.maps.LatLng(this.getPosition().lat(),this.getPosition().lng())
                //escribimos las coordenadas de la posicion actual del marcador dentro del input #coords
                document.getElementById("ubicacionMapa").value = this.getPosition().lat()+","+ this.getPosition().lng();
                $('#ubicacionMapa').val(this.getPosition().lat()+","+ this.getPosition().lng()).trigger('input');
              });
        }

    }

})();