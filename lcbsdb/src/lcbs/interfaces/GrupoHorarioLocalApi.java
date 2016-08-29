package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataGrupoHorario;
import lcbs.shares.DataTenant;

@Local
public interface GrupoHorarioLocalApi {
	public List<DataGrupoHorario> obtenerGrupoHorario(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarGrupoHorario(DataGrupoHorario grHor, DataTenant tenant);
	public DataGrupoHorario getGrupoHorario(String id, DataTenant tenant);
	public DataGrupoHorario crearGrupoHorario(DataGrupoHorario grp, DataTenant tenant);
	public void borrarGrupoHorario(String idGrp, DataTenant tenant);
}
