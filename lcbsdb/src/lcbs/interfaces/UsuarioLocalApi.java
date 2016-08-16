package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataUsuario;

@Local
public interface UsuarioLocalApi {
	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina);
	public DataUsuario loginUsuario(String mailUsuario, String clave);
	public void modificarUsuario(DataUsuario usu);
	public DataUsuario getUsuario(String usu);
	public DataUsuario crearUsuario(DataUsuario usu);
	public void darBajaUsuario(String idUsuario);
	public DataUsuario buscarUsuarioPorMail(String mailUsuario);
}
