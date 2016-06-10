package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class GrupoHorario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private List<EnumDias> diasSemana; //Lista de dias de la semana en los que funciona el grupo
    private List<Date> diasEspecificos; //Lista de dias especificos en los que funciona este grupo, por ejemplo, semana de turismo
    private List<Horario> horarios;
    
 

    public GrupoHorario() {
        id = "";
        nombre = "";
        diasSemana = List<EnumDias>();
        diasEspecificos = new List<Date>();
        horarios = new List<Horario>();
    }
    
    public GrupoHorario(String id, String nom, List<EnumDias> diasSemana, List<Date> diasEspec, List<Horario> hora) {
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

    public void setDiasSemana(List<EnumDias> val){
        this.diasSemana = val;
    }
    
    public List<EnumDias> getDiasSemana(){
        return this.diasSemana;
    }

    public void setDiasEspecificos(List<Date> val){
        this.diasEspecificos = val;
    }
    
    public List<Date> getDiasEspecificos(){
        return this.diasEspecificos;
    }

    public void setHorarios(List<Horario> val){
        this.horarios = val;
    }
    
    public List<Horario> getHorarios(){
        return this.horarios;
    }
}