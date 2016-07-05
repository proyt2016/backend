package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataReserva;

@Local
public interface ReservaLocalApi {
	public List<DataReserva> obtenerReservas(Integer pagina, Integer elementosPagina);
	public List<DataReserva> listarReservasPorUsuario(String idUsuario);
	public void modificarReserva(DataReserva rce);
	public DataReserva getReserva(String rce);
	public DataReserva crearReserva(DataReserva rce);
	public void darBajaReserva(String idReserva);
}
