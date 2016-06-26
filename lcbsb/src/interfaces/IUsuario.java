package interfaces;

import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IUsuario {
	
	public DataUsuario AltaUsuario(DataUsuario usuario);
	public void ModificarUsuario(DataUsuario usuario);
	public void BajaUsuario(String idUsuario);
	public DataEmpleado AltaEmpleado(DataEmpleado empleado);
	public void ModificarEmpleado(DataEmpleado empleado);
	public void BajaEmpleado(String idEmpleado);
	public void CargarSaldoCuponera(DataUsuario usuario, Float saldo);
	public List<DataNotificacion> listarNotificaciones(String idUsuario);
	public void AltaPerfil(DataPerfil perfil);
	public void EditarPerfil(DataPerfil perfil);
	public void EliminarPerfil(String idPerfil);
	public void AsignarPerfil(String idEmpleado, DataPerfil perfil);
	
}
