package interfaces;

import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IUsuario {
	
	public boolean loginUsuario(String usuario, String clave);
	public DataUsuario getUsuario(String idUsuario);
	public DataUsuario AltaUsuario(DataUsuario usuario);
	public void ModificarUsuario(DataUsuario usuario);
	public void BajaUsuario(String idUsuario);
	public boolean loginEmpleado(String usuario, String clave);
	public DataEmpleado getEmpleado(String idEmpleado);
	public DataEmpleado AltaEmpleado(DataEmpleado empleado);
	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina);
	public void ModificarEmpleado(DataEmpleado empleado);
	public void BajaEmpleado(String idEmpleado);
	public void CargarSaldoCuponera(String idUsuario, Float saldo);
	public List<DataNotificacion> listarNotificaciones(String idUsuario);
	public DataPerfil getPerfil(String idPerfil);
	public DataPerfil AltaPerfil(DataPerfil perfil);
	public void EditarPerfil(DataPerfil perfil);
	public void EliminarPerfil(String idPerfil);
	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina);
	public void AsignarPerfil(String idEmpleado, String perfil);
	
}
