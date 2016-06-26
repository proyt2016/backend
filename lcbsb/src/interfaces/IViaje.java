package interfaces;

import java.util.Map;

import lcbs.shares.*;
public interface IViaje {
	
	public Map<String, DataViaje> BuscarViaje(DataViaje filtro);
	public DataPasaje ComprarPasaje(DataPasaje pasaje);
	public void CambiarHorarioPasaje(String idPasaje, DataViaje viaje);
	public DataReserva ReservarPasaje(DataReserva reserva);
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario);//idUsuario a Transferir
	public void CancelarReserva(String idReserva);
	public Map<String, DataReserva> ListarReservas(String idUsuario);
	public void ProcesarPasajes(String idPasaje);
	public DataParada AltaParadas(DataParada parada);
	public void AltaTerminal(DataTerminal terminal);
	public void EditarParada(DataParada parada);
	public void EditarTerminal(DataTerminal terminal);
	public void CrearRecorrido(DataRecorrido recorrido);
	public void EditarRecorrido(DataRecorrido recorrido);
	
	public DataPasaje ComprarPasajeReservado(DataReserva reserva);
	public DataParada obtenerParada(String IdParada);
	public Map<String, DataPasaje> obtenerHistorialPasajes(String idUsuario);
	public DataPasaje verDetallePasaje(String idPasaje);
	public DataTerminal obtenerTerminal(String IdTerminal);

	public void crearViaje(DataViaje viaje);
	public void editarViaje(DataViaje viaje);
	public void eliminarViaje(String idViaje);
}
