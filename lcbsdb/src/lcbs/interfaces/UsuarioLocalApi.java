package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataUsuario;

@Local
public interface UsuarioLocalApi {
	public Map<String,DataUsuario> obtenerUsuarios();
	public void modificarUsuario(DataUsuario usu);
	public DataUsuario getUsuario(String usu);
	public DataUsuario crearUsuario(DataUsuario usu);
	public void darBajaUsuario(String idUsuario);
}
