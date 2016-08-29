package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;

@Local
public interface PerfilLocalApi {
	public List<DataPerfil> obtenerPerfils(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarPerfil(DataPerfil prf, DataTenant tenant);
	public DataPerfil getPerfil(String id, DataTenant tenant);
	public DataPerfil crearPerfil(DataPerfil prf, DataTenant tenant);
	public void borrarPerfil(String idPerfil, DataTenant tenant);
}
