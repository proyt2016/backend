package lcbs.shares;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataGrupoHorario{
    
	private String id;
    
    private String nombre;
    private String tipo;
    private List<DataDiasSemana> diasSemana; //Lista de dias de la semana en los que funciona el grupo
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private List<Date> diasEspecificos; //Lista de dias especificos en los que funciona este grupo, por ejemplo, semana de turismo
    private List<DataHorario> horarios;
    
 

    public DataGrupoHorario() {}
    
    public DataGrupoHorario(String id, String nom, List<DataDiasSemana> diasSemana, List<Date> diasEspec, List<DataHorario> hora, String tipo) {
        this.id = id;
        this.nombre = nom;
        this.diasSemana = diasSemana;
        this.diasEspecificos = diasEspec;
        this.horarios = hora;
        this.tipo = tipo;
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

    public void setDiasSemana(List<DataDiasSemana> val){
        this.diasSemana = val;
    }
    
    public List<DataDiasSemana> getDiasSemana(){
        return this.diasSemana;
    }

    public void setDiasEspecificos(List<Date> val){
        this.diasEspecificos = val;
    }
    
    public List<Date> getDiasEspecificos(){
        return this.diasEspecificos;
    }

    public void setHorarios(List<DataHorario> val){
        this.horarios = val;
    }
    
    public List<DataHorario> getHorarios(){
        return this.horarios;
    }
    
    public void setTipo(String val){
        this.tipo = val;
    }
    
    public String getTipo(){
        return this.tipo;
    }
}