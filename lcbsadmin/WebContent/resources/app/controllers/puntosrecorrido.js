(function () {
    'use strict';
    angular.module('lacbus').controller('puntosrecorridoCtrl', ['$scope', '$routeParams', 'puntosrecorridoService', puntosrecorridoCtrl]);

    function puntosrecorridoCtrl($scope, $routeParams, puntosrecorridoService) {
        $scope.puntosrecorridoLst     = [];
        $scope.puntosrecorrido     = null;
        $scope.tipodepunto = "Parada";
        $scope.showAlert    = false;
        $scope.ubicacionInicial = null;
        $scope.emailsTerminal = [];
        $scope.telsTerminal = [];

        
        puntosrecorridoService.getTerminales().then(function (data) {
            $scope.puntosrecorridoLst = data;
            puntosrecorridoService.getParadas().then(function (data) {
                var newArray = $scope.puntosrecorridoLst.concat(data);
                $scope.puntosrecorridoLst = newArray;
                initMap();
            });
        });

        

        $scope.addEdit = function(){
            $scope.saving   = true;
            var puntosrecorrido     = this.puntosrecorrido;
            var tipodepunto = this.tipodepunto;
            if(tipodepunto === 'Terminal'){
                puntosrecorrido.mailsDeContacto = $scope.emailsTerminal;
                puntosrecorrido.telefonosContacto = $scope.telsTerminal;
            }
            puntosrecorridoService.addEdit(puntosrecorrido, tipodepunto).then(
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

        $scope.addRelation = function (type) {
            if(type == 'email'){
                var nuevoEmail = {'descripcion':'','email':''};
                $scope.emailsTerminal.push(nuevoEmail);
            }
            if(type == 'tels'){
                var nuevoTel = {'descripcion':'','telefono':''};
                $scope.telsTerminal.push(nuevoTel);
            }
        }

        $scope.removeRelation = function (type, index) {
            if(type == 'email'){
                $scope.emailsTerminal.splice(index, 1);
            }
            if(type == 'tels'){
                $scope.telsTerminal.splice(index, 1);
            }
        }

        var initMap = function () {

            //usamos la API para geolocalizar el usuario
            navigator.geolocation.getCurrentPosition(
              function (position){
                var coords =  {
                  lng: position.coords.longitude,
                  lat: position.coords.latitude
                };
                setMapa(coords);  //pasamos las coordenadas al metodo para crear el mapa
                
               
              },function(error){console.log(error);});
            
        }


        var setMapa = function (coords) {
          //Se crea una nueva instancia del objeto mapa
          var map = new google.maps.Map(document.getElementById('map'),
          {
            zoom: 13,
            center:new google.maps.LatLng(coords.lat,coords.lng),

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

          $('#ubicacionMapa').val('');

          map.addListener('click', function(e) {
            placeMarkerAndPanTo(e.latLng, map, 'Nuevo punto');
          });
        }

        var placeMarkerAndPanTo = function (latLng, map, title, icon) {
          var marker;
          if(icon != null){
              marker = new google.maps.Marker({
                position: latLng,
                draggable: true,
                map: map,
                title: title,
                icon: icon
              });
          }else{
              marker = new google.maps.Marker({
                position: latLng,
                draggable: true,
                map: map,
                title: title
              });
          }
          
          map.panTo(latLng);
          $scope.puntosrecorrido = null;
          $scope.tipodepunto = "Parada";

          $('#ubicacionMapa').val(marker.position.lat()+","+marker.position.lng()).trigger('input');
              
          marker.addListener( 'click', function (event){
            $('#ubicacionMapa').val(this.position.lat()+","+this.position.lng()).trigger('input');
            puntosrecorridoService.getPorCoord(this.position.lat()+","+this.position.lng()).then(function (data) {
                $scope.puntosrecorrido = data;
                $scope.tipodepunto = data.tipo;
                if($scope.tipodepunto == 'Terminal'){
                    $scope.emailsTerminal = $scope.puntosrecorrido.mailsDeContacto;
                    $scope.telsTerminal = $scope.puntosrecorrido.telefonosContacto;
                }
            });
          })

          ubicacionMapaOriginal
          marker.addListener( 'dragstart', function (event)
          {
            $('#ubicacionMapaOriginal').val(this.getPosition().lat()+","+ this.getPosition().lng()).trigger('input');
          });    


          marker.addListener( 'dragend', function (event)
          {
            this.position = new google.maps.LatLng(this.getPosition().lat(),this.getPosition().lng())
            var position = this.getPosition().lat()+","+ this.getPosition().lng();
            puntosrecorridoService.getPorCoord($scope.ubicacionInicial).then(function (data) {
                $scope.puntosrecorrido = data;
                $scope.tipodepunto = data.tipo;
                $('#ubicacionMapa').val(position).trigger('input');
            });
          });
        }

        

    }

})();