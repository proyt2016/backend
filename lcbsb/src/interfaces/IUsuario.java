package interfaces;

import java.util.List;
import java.util.Map;

import lcbs.shares.*;
public interface IUsuario {
	
	public DataUsuario AltaUsuario(DataUsuario usuario);
	public void ModificarUsuario(DataUsuario usuario);
	public void BajaUsuario(DataUsuario usuario);
	public DataEmpleado AltaEmpleado(DataEmpleado empleado);
	public void ModificarEmpleado(DataEmpleado empleado);
	public void BajaEmpleado(DataEmpleado empleado);
	public void CargarSaldoCuponera(DataUsuario usuario, Float saldo);
	public List<DataNotificacion> listarNotificaciones(String idUsuario);
	
}
