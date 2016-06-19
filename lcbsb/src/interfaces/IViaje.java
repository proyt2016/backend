package interfaces;

import java.util.List;

import lcbs.shares.*;
public interface IViaje {
	
	public List<DataViaje> BuscarViaje(DataViaje viaje);
	public void ComprarPasaje(DataPasaje pasaje);
	public void CambiarHorarioPasaje(String idPasaje, DataViaje viaje);
	public void ReservarPasaje(DataReserva reserva);
	
	public void TransferirPasajeComprado(String idPasaje, String idUsuario);//idUsuario a Transferir
	public boolean CancelarReserva(String idReserva);
	public List<DataReserva> ListarReservas();
	public boolean ProcesarPasajes(String idPasaje);
	public void AltaParadas(DataParada parada);
	public void AltaTerminal(DataTerminal terminal);
	public void EditarParada(DataParada parada);
	public void EditarTerminal(DataTerminal terminal);
	public void CrearRecorrido(DataRecorrido recorrido);
	public void EditarRecorrido(DataRecorrido recorrido);
	
	
	public List<DataReserva> BuscarReserva(String CiUsr);
	public boolean ComprarPasajeReservado(DataReserva reserva);
	
	

	
	
	
	
	
	
	

}
