package interfaces;

import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IUsuario {
	
	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant);
	public DataUsuario getUsuario(String idUsuario, DataTenant tenant);
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant);
	public void ModificarUsuario(DataUsuario usuario, DataTenant tenant);
	public void BajaUsuario(String idUsuario, DataTenant tenant);
	public DataEmpleado loginEmpleado(String usuario, String clave, DataTenant tenant);
	public DataEmpleado getEmpleado(String idEmpleado, DataTenant tenant);
	public DataEmpleado AltaEmpleado(DataEmpleado empleado, DataTenant tenant);
	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void ModificarEmpleado(DataEmpleado empleado, DataTenant tenant);
	public void BajaEmpleado(String idEmpleado, DataTenant tenant);
	public void CargarSaldoCuponera(String idUsuario, Float saldo, DataTenant tenant);
	public List<DataNotificacion> listarNotificaciones(String idUsuario, DataTenant tenant);
	public DataPerfil getPerfil(String idPerfil, DataTenant tenant);
	public DataPerfil AltaPerfil(DataPerfil perfil, DataTenant tenant);
	public void EditarPerfil(DataPerfil perfil, DataTenant tenant);
	public void EliminarPerfil(String idPerfil, DataTenant tenant);
	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void AsignarPerfil(String idEmpleado, String perfil, DataTenant tenant);
	public List<DataUsuario> listarUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant);
	
}
