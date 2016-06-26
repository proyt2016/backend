package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataGrupoHorario;

@Local
public interface GrupoHorarioLocalApi {
	public Map<String,DataGrupoHorario> obtenerGrupoHorario();
	public void modificarGrupoHorario(DataGrupoHorario grHor);
	public DataGrupoHorario getGrupoHorario(String id);
	public DataGrupoHorario crearGrupoHorario(DataGrupoHorario grp);
	public void borrarGrupoHorario(String idGrp);
}
