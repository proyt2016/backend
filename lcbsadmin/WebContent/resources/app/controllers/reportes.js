(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', '$localStorage', '$location', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	
        var dataPorNuevo = [];
        var ArrayPasajes = [];
        $scope.dataNuevoFilter = [];
        var dataPorSesion = [];
        $scope.dataSesionFilter = [];

        var startDateNuevos = moment().subtract(29, 'days');
        var endDateNuevos = moment();
        var startDateSesion = moment().subtract(29, 'days');
        var endDateSesion = moment();

        var chartPorNuevos = null;
        var chartPorSesion = null;      
       // var fechaHoy = moment();
       // $scope.dia = moment(fechaHoy,'DD/MM/YYYY').format('YYYY-MM-DD');
            
         
        var dateOption = {
            startDate: moment().subtract(29, 'days'),
            endDate: moment(),
            showDropdowns: true,
            timePicker: false,
            ranges: {
                'Ultimos 7 dias': [moment().subtract(6, 'days'), moment()],
                'Ultimos 30 dias': [moment().subtract(29, 'days'), moment()],
                'Este Mes': [moment().startOf('month'), moment().endOf('month')],
                'Mes anterior': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'DD/MM/YY',
            separator: ' a ',
            locale: {
                applyLabel: 'Enviar',
                cancelLabel: 'Borrar',
                fromLabel: 'Desde',
                toLabel: 'a',
                customRangeLabel: 'Rango',
                daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
                monthNames: ['Enero', 'Febero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            }
        };


if($location.path() == '/reportes/reportesPasajes'){
        reportesService.getPasajesVendidos().then(function (data) {
                    dataPorNuevo = data;
                       
                   dibujarGraficaNuevos();
                 //  arrayPasajes();

            });

       /* var arrayPasajes = function () { 

            var grupo = [];
            var canti = 1;

            for(var dp in dataPorNuevo){
                var nodo = dataPorNuevo[dp];
                
                array[nodo.fechaCompra] = canti;

                console.log(array);



                
            }



        };*/

       /* var ordenarArray = function () {

                var arrayOrdenado = [];
                var cant = 0;
                //ITERO SOBRE TODOS LOS PASAJES
                for(var p in dataPorNuevo){
                    var nodo = dataPorNuevo[p];
                    var fechaNodo = moment(nodo['fechaCompra'], 'DD-MM-YY')
                    //SI EL ARRAY ORDENADO NO TIENE ELEMENTOS LE AGREGO EL PRIMER PASAJE
                    if(arrayOrdenado.length == 0){
                        arrayOrdenado.push({'cantidad' : 1, 'fecha' : fechaNodo});
                    }else
                        {   
                            //ITERO SOBRE LA LISTA ORDENADA 
                            for(var p2 in arrayOrdenado){
                                var nodo2 = arrayOrdenado[p2];
                                var fechaNodo2 = moment(nodo2['fechaCompra'], 'DD-MM-YY');
                                    //SI LA FECHA DEL PASAJE EN ARRAY ORDENADO ES LA MISMA QUE LA FECHA DEL PASAJE SIGUIENTE 
                                    //TENGO QUE MODIFICAR LA CANTIDAD DEL ELEMENTO YA AGREGADO YA QUE LAS FECHAS SON IGUALES
                                    if(fechaNodo2 == fechaNodo){
                                        cant++;
                                        nodo2['cantidad' : cant, 'fecha' : fechaNodo2];//ESTO ESTA MAL LO SE COMO MODIFICO EL NODO TENGO Q AGREGARLE 1 A CANTIDAD

                                        console.log("--------------> NODOOOOOOOO"nodo);
                                    }else{
                                            //SI LA FECHA DEL PASAJE DEL ARRAY ODENADO ES DISTINTA A LA FECHA DEL PASAJE SIGUIENTE
                                            //AGREGO UN NUEVO ELEMNTEO A EL ARRAY ORDENADO
                                            arrayOrdenado.push({'cantidad' : 1, 'fecha' : fechaNodo2});
                                        }
                            }
                        }
                }
                console.log("arrayyyyy ordenado------------->"arrayOrdenado);

        };*/
       
        var dataByRangoNuevos = function () {

            var dataArray = [];
                    for (var d in dataPorNuevo) {
                        var item = dataPorNuevo[d];
                        var date = moment(item['fechaCompra'], 'YYYY-MM-DD');
                        if (date.isBetween(startDateNuevos, endDateNuevos) || date.isSame(startDateNuevos) || date.isSame(endDateNuevos)) {
                            dataArray.push({ 'monto': item.precio.monto, 'fechaCompra': moment(item.fechaCompra).format('DD/MM/YY') });
                        }
                    }
                    if (dataArray.length == 0) {
                        dataArray.push({ 'monto' : 0, 'fechaCompra' : 'No data' });
                    }
                    $scope.dataNuevoFilter = dataArray;
                    return dataArray;
        };

        var dibujarGraficaNuevos = function () {
            var data = dataByRangoNuevos();
            console.log(data);
            chartPorNuevos = Morris.Bar({
                element: 'porNuevos',
                data: data,
                xkey: 'fechaCompra',
                hideHover: 'auto',
                barColors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
                ykeys: ['monto'],
                labels: ['Precio'],
                xLabelAngle: 60,
                resize: true
            });

            $('#dateRangeNuevos').daterangepicker(dateOption);
            $('#dateRangeNuevos span').html(startDateNuevos.format('DD/MM/YY') + ' - ' + endDateNuevos.format('DD/MM/YY'));
            $('#dateRangeNuevos').on('apply.daterangepicker', function (ev, picker) {
                startDateNuevos = picker.startDate;
                endDateNuevos = picker.endDate;

                cambiarRangoNuevos();
            });
        };

        var cambiarRangoNuevos = function () {
            $('#dateRangeNuevos span').html(startDateNuevos.format('DD/MM/YY') + ' - ' + endDateNuevos.format('DD/MM/YY'));
          
            var data = dataByRangoNuevos();

            chartPorNuevos.setData(data);

            $scope.$apply();
        }
}
if($location.path() == '/reportes/reportesEncomiendas'){
    reportesService.getEncomiendasPagas().then(function (data) {
                    dataPorNuevo = data;
                       dibujarGraficaNuevos();


            });

        var dataByRangoNuevos = function () {

            var dataArray = [];
                    for (var d in dataPorNuevo) {
                        var item = dataPorNuevo[d];
                        var date = moment(item['fechaEntrega'], 'YYYY-MM-DD');
                        if (date.isBetween(startDateNuevos, endDateNuevos) || date.isSame(startDateNuevos) || date.isSame(endDateNuevos)) {
                            
                            dataArray.push({ 'monto': item.monto, 'fechaEntrega': moment(item.fechaEntrega).format('DD/MM/YY') });
                            console.log(item.fechaEntrega);
                        }   
                    }
                    console.log(item);
                    if (dataArray.length == 0) {
                        dataArray.push({ 'monto' : 0, 'fechaEntrega' : 'No data' });
                    }
                    $scope.dataNuevoFilter = dataArray;
                    return dataArray;
        };

        var dibujarGraficaNuevos = function () {
            var data = dataByRangoNuevos();
            console.log(data);
            chartPorNuevos = Morris.Bar({
                element: 'porNuevos',
                data: data,
                xkey: 'fechaEntrega',
                hideHover: 'auto',
                barColors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
                ykeys: ['monto'],
                labels: ['Monto'],
                xLabelAngle: 60,
                resize: true
            });

            $('#dateRangeNuevos').daterangepicker(dateOption);
            $('#dateRangeNuevos span').html(startDateNuevos.format('DD/MM/YY') + ' - ' + endDateNuevos.format('DD/MM/YY'));
            $('#dateRangeNuevos').on('apply.daterangepicker', function (ev, picker) {
                startDateNuevos = picker.startDate;
                endDateNuevos = picker.endDate;

                cambiarRangoNuevos();
            });
        };

        var cambiarRangoNuevos = function () {
            $('#dateRangeNuevos span').html(startDateNuevos.format('DD/MM/YY') + ' - ' + endDateNuevos.format('DD/MM/YY'));
          
            var data = dataByRangoNuevos();

            chartPorNuevos.setData(data);

            $scope.$apply();
        }
}


    }   

})();

