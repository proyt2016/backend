package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataReserva;
import lcbs.shares.DataTenant;

@Local
public interface ReservaLocalApi {
	public List<DataReserva> obtenerReservas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataReserva> listarReservasPorUsuario(String idUsuario, DataTenant tenant);
	public void modificarReserva(DataReserva rce, DataTenant tenant);
	public DataReserva getReserva(String rce, DataTenant tenant);
	public DataReserva crearReserva(DataReserva rce, DataTenant tenant);
	public void darBajaReserva(String idReserva, DataTenant tenant);
}
