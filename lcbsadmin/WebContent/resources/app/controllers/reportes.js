(function () {
    'use strict';
    angular.module('lacbus').controller('reportesCtrl', ['$scope', '$routeParams', 'reportesService', '$localStorage', '$location', reportesCtrl]);

    function reportesCtrl($scope, $routeParams, reportesService, $localStorage, $location) {
    	if(!$localStorage.empleadoLogueado){
			$location.url('/login');
		}
    	$scope.monto = 0;
        var dataPorNuevo = [];
        var ArrayPasajes = [];
          var arrayOrdenado = [];
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
    arrayOrdenado = [];
        reportesService.getPasajesVendidos().then(function (data) {
            dataPorNuevo = data;
            var montoTotal = 0;
            for(var p in dataPorNuevo){
                var nodo = dataPorNuevo[p];
                if(nodo.pago){
                	montoTotal+=nodo.precio.monto;	
                }
                var fecha = moment(nodo['fechaCompra']);

                var key = fecha.format('DDMMYYYY');

                if(arrayOrdenado[key]){
                    arrayOrdenado[key]['cantidad'] += 1;
                }else{
                    arrayOrdenado[key] = {
                        fechaCompra : fecha,
                        cantidad : 1
                    };
                }
            }
           $scope.monto = montoTotal;
           dibujarGraficaNuevos();
        });
       
        var dataByRangoNuevos = function () {

            var dataArray = [];
                    for (var d in arrayOrdenado) {
                        var item = arrayOrdenado[d];
                        var date = item['fechaCompra'];
                        if (date.isBetween(startDateNuevos, endDateNuevos) || date.isSame(startDateNuevos) || date.isSame(endDateNuevos)) {
                            dataArray.push({ 'cantidad': item.cantidad, 'fechaCompra': item.fechaCompra.format('DD/MM/YY') });
                        }
                    }
                    if (dataArray.length == 0) {
                        dataArray.push({ 'cantidad' : 0, 'fechaCompra' : 'No data' });
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
                ykeys: ['cantidad'],
                labels: ['Cantidad'],
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
     arrayOrdenado = [];
    reportesService.getEncomiendasPagas().then(function (data) {
                dataPorNuevo = data;
                var montoTotal = 0;
                for(var p in dataPorNuevo){
                var nodo = dataPorNuevo[p];
                if(nodo.paga){
                	montoTotal+=nodo.precio;
                }
                var fecha = moment(nodo['fechaEntrega']);
                var key = fecha.format('DDMMYYYY');
                if(arrayOrdenado[key]){
                    arrayOrdenado[key]['cantidad'] += 1;
                }else{
                    arrayOrdenado[key] = {
                        fechaEntrega : fecha,
                        cantidad : 1
                    };
                }
            }

                $scope.monto = montoTotal;
                 dibujarGraficaNuevos();


            });

    

       
        var dataByRangoNuevos = function () {

            var dataArray = [];
                    for (var d in arrayOrdenado) {
                        var item = arrayOrdenado[d];
                        var date = item['fechaEntrega'];
                        if (date.isBetween(startDateNuevos, endDateNuevos) || date.isSame(startDateNuevos) || date.isSame(endDateNuevos)) {
                            dataArray.push({ 'cantidad': item.cantidad, 'fechaEntrega': item.fechaEntrega.format('DD/MM/YY') });
                        }
                    }
                    if (dataArray.length == 0) {
                        dataArray.push({ 'cantidad' : 0, 'fechaEntrega' : 'No data' });
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
                ykeys: ['cantidad'],
                labels: ['Cantidad'],
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

