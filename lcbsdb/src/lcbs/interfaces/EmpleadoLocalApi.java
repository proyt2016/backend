package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataEmpleado;

@Local
public interface EmpleadoLocalApi {
	public Map<String,DataEmpleado> obtenerEmpleados(Integer pagina, Integer elementosPagina);
	public void modificarEmpleado(DataEmpleado emp);
	public DataEmpleado getEmpleado(String id);
	public DataEmpleado crearEmpleado(DataEmpleado emp);
	public void darBajaEmpleado(String idEmpleado);
}
