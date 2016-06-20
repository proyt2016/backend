package lcbs.interfaces;

import javax.ejb.Local;
import lcbs.shares.*;

@Local
public interface ConfiguracionEmpresaLocalApi {
	public DataConfiguracionEmpresa getConfiguracionEmpresa();
	public void modificarCuponera(DataConfiguracionEmpresa conf);
	public DataConfiguracionEmpresa crearConfiguracionEmpresa(DataConfiguracionEmpresa conf);
}
