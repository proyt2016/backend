package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.models.Usuario;

@Local
public interface UsuarioLocalApi {
	public Map<String,Usuario> obtenerUsuarios();
	public void modificarUsuario(Usuario usu);
	public Usuario getUsuario(String usu);
	public void crearUsuario(Usuario usu);
	public void darBajaUsuario(Usuario usu);
}
