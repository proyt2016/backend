package interfaces;

import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IViaje {
	
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina);
	public DataPasaje ComprarPasaje(DataPasaje pasaje);
	public void CambiarHorarioPasaje(String idPasaje, String viaje);
	public DataReserva ReservarPasaje(DataReserva reserva);
	public List<DataTerminal> getTerminales(int pagina, int elementos);
	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina);
	public List<DataPasajeConvertor> getPasajes(Integer pagina, Integer ElementosPagina);
	public DataPasaje getPasajeXCodigo(int codigoPasaje);
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario);//idUsuario a Transferir
	public void CancelarReserva(String idReserva);
	public List<DataReserva> ListarReservas(String idUsuario);
	public void ProcesarPasajes(String idPasaje);
	public DataParada AltaParadas(DataParada parada);
	public DataTerminal AltaTerminal(DataTerminal terminal);
	public void EditarParada(DataParada parada);
	public void EditarTerminal(DataTerminal terminal);
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido);
	public void EditarRecorrido(DataRecorrido recorrido);
	public List<DataRecorrido> listarRecorridos(Integer pagina, Integer elementosPagina);
	public DataPasaje ComprarPasajeReservado(DataReserva reserva);
	public DataParada obtenerParada(String IdParada);
	public List<DataPasaje> obtenerHistorialPasajes(String idUsuario, Integer pagina, Integer elementosPagina);
	public DataPasaje verDetallePasaje(String idPasaje);
	public DataTerminal obtenerTerminal(String IdTerminal);

	public DataViaje crearViaje(DataViaje viaje);
	public void editarViaje(DataViaje viaje);
	public void eliminarViaje(String idViaje);
	public DataRecorrido obtenerRecorrido(String idRecorrido);
	public void BajaRecorrido(String idRecorrido);
	public List<DataParada> getParadas(Integer pagina, Integer elementosPagina);
	public DataPuntoRecorrido obtenerPuntoRecorrido(String idPunto);
	public DataPuntoRecorrido obtenerPuntoPorCoordenada(String coord);
	public DataViaje getViaje(String idViaje);
	public List<DataViaje> getViajes(int pagina, int elementos);
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina);
	
}
