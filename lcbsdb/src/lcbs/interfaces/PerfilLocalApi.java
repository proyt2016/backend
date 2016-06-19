package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataPerfil;

@Local
public interface PerfilLocalApi {
	public Map<String,DataPerfil> obtenerPerfils();
	public void modificarPerfil(DataPerfil prf);
	public DataPerfil getPerfil(String id);
	public void crearPerfil(DataPerfil prf);
	public void borrarPerfil(DataPerfil prf);
}
