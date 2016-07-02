package interfaces;

import java.util.Map;

import lcbs.shares.*;
public interface IEmpresa {
	public void altaConfiguracionEmpresa(DataConfiguracionEmpresa empresa);
	public DataConfiguracionEmpresa obtenerConfiguracionEmpresa();
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa);
}
