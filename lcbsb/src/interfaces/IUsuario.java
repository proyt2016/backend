package interfaces;

import java.util.List;

import lcbs.exceptions.UserException;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;
public interface IUsuario {
	
 
	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant)throws UserException;
	public DataUsuario getUsuario(String idUsuario, DataTenant tenant)throws UserException;
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant)throws UserException;
	public void ModificarUsuario(DataUsuario usuario, DataTenant tenant)throws UserException;
	public void BajaUsuario(String idUsuario, DataTenant tenant)throws UserException;
	public DataEmpleado loginEmpleado(String usuario, String clave, DataTenant tenant)throws UserException;
	public DataEmpleado getEmpleado(String idEmpleado, DataTenant tenant)throws UserException;
	public DataEmpleado AltaEmpleado(DataEmpleado empleado, DataTenant tenant)throws UserException;
	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina, DataTenant tenant)throws UserException;
	public void ModificarEmpleado(DataEmpleado empleado, DataTenant tenant)throws UserException;
	public void BajaEmpleado(String idEmpleado, DataTenant tenant)throws UserException;
	public void CargarSaldoCuponera(String idUsuario, Float saldo, DataTenant tenant)throws UserException;
	public List<DataNotificacion> listarNotificaciones(String idUsuario, DataTenant tenant)throws UserException;
	public DataPerfil getPerfil(String idPerfil, DataTenant tenant)throws UserException;
	public DataPerfil AltaPerfil(DataPerfil perfil, DataTenant tenant)throws UserException;
	public void EditarPerfil(DataPerfil perfil, DataTenant tenant)throws UserException;
	public void EliminarPerfil(String idPerfil, DataTenant tenant)throws UserException;
	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina, DataTenant tenant)throws UserException;
	public void AsignarPerfil(String idEmpleado, String perfil, DataTenant tenant)throws UserException;
	public List<DataUsuario> listarUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant)throws UserException;
	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant)throws UserException;
	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant)throws UserException;
	public void cargarTarjeta(String idUsuario, Float cargo, DataTenant tenant)throws UserException;
 
	public void CargarSaldoCuponeraStripe(String idUsuario, Float saldo, DataTenant tenant) throws UserException;
 
	
}
