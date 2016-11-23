package lcbs.interfaces;

import javax.ejb.Local;
import lcbs.shares.*;

@Local
public interface ConfiguracionEmpresaLocalApi {
	public DataConfiguracionEmpresa getConfiguracionEmpresa(DataTenant tenant);
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa conf, DataTenant tenant);
	public DataConfiguracionEmpresa crearConfiguracionEmpresa(DataConfiguracionEmpresa conf, DataTenant tenant);
}
