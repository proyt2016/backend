package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataPerfil;

@Local
public interface PerfilLocalApi {
	public List<DataPerfil> obtenerPerfils(Integer pagina, Integer elementosPagina);
	public void modificarPerfil(DataPerfil prf);
	public DataPerfil getPerfil(String id);
	public DataPerfil crearPerfil(DataPerfil prf);
	public void borrarPerfil(String idPerfil);
}
