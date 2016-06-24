package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataReserva;

@Local
public interface ReservaLocalApi {
	public Map<String,DataReserva> obtenerReservas();
	public Map<String,DataReserva> listarReservasPorUsuario(String idUsuario);
	public void modificarReserva(DataReserva rce);
	public DataReserva getReserva(String rce);
	public DataReserva crearReserva(DataReserva rce);
	public void darBajaReserva(DataReserva rce);
}
