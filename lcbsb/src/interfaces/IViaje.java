package interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IViaje {
	
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer cantidadDias, Integer pagina, Integer ElementosPagina, DataTenant tenant) throws ParseException;
	public DataPasaje ComprarPasaje(DataPasaje pasaje, DataTenant tenant);
	public void CambiarHorarioPasaje(String idPasaje, String viaje, DataTenant tenant);
	public DataReserva ReservarPasaje(DataReserva reserva, DataTenant tenant);
	public List<DataTerminal> getTerminales(Integer pagina, Integer elementos, DataTenant tenant);
	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina, DataTenant tenant);
	public List<DataPasajeConvertor> getPasajes(Integer pagina, Integer ElementosPagina, DataTenant tenant);
	public DataPasaje getPasajeXCodigo(Integer codigoPasaje, DataTenant tenant);
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario, DataTenant tenant);//idUsuario a Transferir
	public void CancelarReserva(String idReserva, DataTenant tenant);
	public List<DataReserva> ListarReservas(String idUsuario, DataTenant tenant);
	public void ProcesarPasajes(String idPasaje, DataTenant tenant);
	public DataParada AltaParadas(DataParada parada, DataTenant tenant);
	public DataTerminal AltaTerminal(DataTerminal terminal, DataTenant tenant);
	public void EditarParada(DataParada parada, DataTenant tenant);
	public void EditarTerminal(DataTerminal terminal, DataTenant tenant);
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido, DataTenant tenant);
	public void EditarRecorrido(DataRecorrido recorrido, DataTenant tenant);
	public List<DataRecorrido> listarRecorridos(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataPasaje ComprarPasajeReservado(DataReserva reserva, DataTenant tenant);
	public DataParada obtenerParada(String IdParada, DataTenant tenant);
	public List<DataPasajeConvertor> obtenerHistorialPasajes(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataPasaje verDetallePasaje(String idPasaje, DataTenant tenant);
	public DataTerminal obtenerTerminal(String IdTerminal, DataTenant tenant);

	public DataViaje crearViaje(DataViaje viaje, DataTenant tenant);
	public void editarViaje(DataViaje viaje, DataTenant tenant);
	public void eliminarViaje(String idViaje, DataTenant tenant);
	public DataRecorrido obtenerRecorrido(String idRecorrido, DataTenant tenant);
	public void BajaRecorrido(String idRecorrido, DataTenant tenant);
	public List<DataParada> getParadas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataPuntoRecorrido obtenerPuntoRecorrido(String idPunto, DataTenant tenant);
	public DataPuntoRecorrido obtenerPuntoPorCoordenada(String coord, DataTenant tenant);
	public DataViaje getViaje(String idViaje, DataTenant tenant);
	public List<DataViaje> getViajes(Integer pagina, Integer elementos, DataTenant tenant);
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataReserva obtenerReserva(String idReserva, DataTenant tenant);
	public DataReserva obtenerReservaPorCi(String ciUsuario, DataTenant tenant);
	public void crearHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant);
	public void editarHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant);
	public void borrarHorarioRecorrido(String idRecorrido, String idHorario, DataTenant tenant);
	public DataPrecio getPrecioDePasaje(String codigoOrigen, String codigoDestino, String codigoRecorrido, DataTenant tenant);
	public void crearViajesNuevoRecorrido(String recorridoId, DataTenant tenant);
	public void crearViajesParaRecorridos(DataTenant tenant) throws ParseException;
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(String fecha, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public Integer cantidadAsientosDisponibles(String idViaje, String idOrigen, String idDestino, DataTenant tenant);
	public DataReserva PasajeOnlineAReserva(Integer codigoPasaje, String idUsuario, DataTenant tenant);
	public List<DataViaje> listarViajesCambioHorario(String idPasaje, DataTenant tenant);
	public DataPasaje comprarPasajeStripe(DataPasaje pasaje, DataTenant tenant);
	public DataPasaje comprarPasajeCuponera(DataPasaje pasaje, DataTenant tenant) throws Exception;

}
