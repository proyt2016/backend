package interfaces;

import lcbs.shares.DataConfiguracionEmpresa;
import lcbs.shares.DataTenant;
public interface IEmpresa {
	public void altaConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant);
	public DataConfiguracionEmpresa obtenerConfiguracionEmpresa(DataTenant tenant);
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant);

}
