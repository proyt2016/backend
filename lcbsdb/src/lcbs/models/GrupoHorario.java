package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.List;


@Entity
@XmlRootElement
public class GrupoHorario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String nombre;
    @ElementCollection(targetClass = DiasSemana.class)
    @CollectionTable(name = "dias", joinColumns = @JoinColumn(name = "GrupoHorarioId"))
    @Column(name = "diasSemana")
    @Enumerated(EnumType.STRING)
    private List<DiasSemana> diasSemana; //Lista de dias de la semana en los que funciona el grupo
    @ElementCollection
    @CollectionTable(name="DiasGruposHorarios", joinColumns=@JoinColumn(name="GrupoHorarioId"))
    @Column(name="DiasEspecificos")
    @Temporal(TemporalType.TIMESTAMP)
    private List<Date> diasEspecificos; //Lista de dias especificos en los que funciona este grupo, por ejemplo, semana de turismo
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Horario> horarios;
    
 

    public GrupoHorario() {}
    
    public GrupoHorario(String id, String nom, List<DiasSemana> diasSemana, List<Date> diasEspec, List<Horario> hora) {
        this.id = id;
        this.nombre = nom;
        this.diasSemana = diasSemana;
        this.diasEspecificos = diasEspec;
        this.horarios = hora;
    }
    
    public GrupoHorario(DataGrupoHorario dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	/*List<DiasSemana> aux = new ArrayList<DiasSemana>();
    	dt.getDiasSemana().stream().forEach((ds) -> {
    		aux.add((DiasSemana)ds);
        });
    	this.setDiasSemana(aux)*/;
    	this.setDiasEspecificos(dt.getDiasEspecificos());
    	if(dt.getHorarios() != null){
	    	List<Horario> auxHr = new ArrayList<Horario>();
	    	dt.getHorarios().stream().forEach((hr) -> {
	    		auxHr.add(new Horario(hr));
	        });
	    	this.setHorarios(auxHr);
    	}
    }
    
    public DataGrupoHorario getDatatype(){
    	DataGrupoHorario result = new DataGrupoHorario();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	/*List<DataDiasSemana> aux = new ArrayList<DataDiasSemana>();
    	this.getDiasSemana().stream().forEach((ds) -> {
    		aux.add((DataDiasSemana)ds);
        });
    	result.setDiasSemana(aux);*/
    	result.setDiasEspecificos(this.getDiasEspecificos());
    	if(this.getHorarios()!=null){
    	List<DataHorario> auxHr = new ArrayList<DataHorario>();
    	this.getHorarios().stream().forEach((hr) -> {
    		auxHr.add(hr.getDatatype());
        });
    	result.setHorarios(auxHr);
    	}
    	return result;
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

    public void setHorarios(List<Horario> val){
        this.horarios = val;
    }
    
    public List<Horario> getHorarios(){
        return this.horarios;
    }
}