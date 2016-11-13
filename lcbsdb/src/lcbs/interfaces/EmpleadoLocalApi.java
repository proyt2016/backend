package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataEmpleado;
import lcbs.shares.DataTenant;

@Local
public interface EmpleadoLocalApi {
	public List<DataEmpleado> obtenerEmpleados(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarEmpleado(DataEmpleado emp, DataTenant tenant);
	public DataEmpleado getEmpleado(String id, DataTenant tenant);
	public DataEmpleado crearEmpleado(DataEmpleado emp, DataTenant tenant);
	public void darBajaEmpleado(String idEmpleado, DataTenant tenant);
	public DataEmpleado loginUsuario(String mailEmpleado, String clave, DataTenant tenant);
	public DataEmpleado empleadoPorIdLdap(String idLdap, DataTenant tenant);
}
