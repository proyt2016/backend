package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataGrupoHorario{
    
	private String id;
    
    private String nombre;
    private List<DiasSemana> diasSemana; //Lista de dias de la semana en los que funciona el grupo
    private List<Date> diasEspecificos; //Lista de dias especificos en los que funciona este grupo, por ejemplo, semana de turismo
    private List<DataHorario> horarios;
    
 

    public DataGrupoHorario() {}
    
    public DataGrupoHorario(String id, String nom, List<DiasSemana> diasSemana, List<Date> diasEspec, List<DataHorario> hora) {
        this.id = id;
        this.nombre = nom;
        this.diasSemana = diasSemana;
        this.diasEspecificos = diasEspec;
        this.horarios = hora;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setDiasSemana(List<DiasSemana> val){
        this.diasSemana = val;
    }
    
    public List<DiasSemana> getDiasSemana(){
        return this.diasSemana;
    }

    public void setDiasEspecificos(List<Date> val){
        this.diasEspecificos = val;
    }
    
    public List<Date> getDiasEspecificos(){
        return this.diasEspecificos;
    }

    public void setDataHorarios(List<DataHorario> val){
        this.horarios = val;
    }
    
    public List<DataHorario> getDataHorarios(){
        return this.horarios;
    }
}