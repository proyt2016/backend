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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import java.util.List;


@Entity
@XmlRootElement
public class GrupoHorario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String tipo;
    @ElementCollection(targetClass = DiasSemana.class)
    @CollectionTable(name = "dias", joinColumns = @JoinColumn(name = "GrupoHorarioId"))
    @Column(name = "diasSemana")
    @Enumerated(EnumType.STRING)
    @IndexColumn(name="LIST_INDEX")
    private List<DiasSemana> diasSemana; //Lista de dias de la semana en los que funciona el grupo
    @ElementCollection
    @CollectionTable(name="DiasGruposHorarios", joinColumns=@JoinColumn(name="GrupoHorarioId"))
    @Column(name="DiasEspecificos")
    @Temporal(TemporalType.TIMESTAMP)
    @IndexColumn(name="LIST_INDEX")
    private List<Date> diasEspecificos; //Lista de dias especificos en los que funciona este grupo, por ejemplo, semana de turismo
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @IndexColumn(name="LIST_INDEX")
    private List<Horario> horarios;
    
 

    public GrupoHorario() {}
    
    public GrupoHorario(String id, String nom, List<DiasSemana> diasSemana, List<Date> diasEspec, List<Horario> hora, String tipo) {
        this.id = id;
        this.nombre = nom;
        this.diasSemana = diasSemana;
        this.diasEspecificos = diasEspec;
        this.horarios = hora;
        this.tipo = tipo;
    }
    
    public GrupoHorario(DataGrupoHorario dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setTipo(dt.getTipo());
    	List<DiasSemana> aux = new ArrayList<DiasSemana>();
    	dt.getDiasSemana().stream().forEach((ds) -> {
    		if(ds.toString() == "Lunes")
    			aux.add(DiasSemana.Lunes);
    		if(ds.toString() == "Martes")
    			aux.add(DiasSemana.Martes);
    		if(ds.toString() == "Miercoles")
    			aux.add(DiasSemana.Miercoles);
    		if(ds.toString() == "Jueves")
    			aux.add(DiasSemana.Jueves);
    		if(ds.toString() == "Viernes")
    			aux.add(DiasSemana.Viernes);
    		if(ds.toString() == "Sabado")
    			aux.add(DiasSemana.Sabado);
    		if(ds.toString() == "Lunes")
    			aux.add(DiasSemana.Domingo);
        });
    	this.setDiasSemana(aux);
    	this.setDiasEspecificos(dt.getDiasEspecificos());
    	if(dt.getHorarios() != null){
	    	List<Horario> auxHr = new ArrayList<Horario>();
	    	dt.getHorarios().stream().forEach((hr) -> {
	    		auxHr.add(new Horario(hr));
	        });
	    	this.setHorarios(auxHr);
    	}
    }
    
    public DataGrupoHorario getDatatype(Boolean conHijos){
    	DataGrupoHorario result = new DataGrupoHorario();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setTipo(this.getTipo());
    	List<DataDiasSemana> aux = new ArrayList<DataDiasSemana>();
    	this.getDiasSemana().stream().forEach((ds) -> {
    		if(ds.toString() == "Lunes")
    			aux.add(DataDiasSemana.Lunes);
    		if(ds.toString() == "Martes")
    			aux.add(DataDiasSemana.Martes);
    		if(ds.toString() == "Miercoles")
    			aux.add(DataDiasSemana.Miercoles);
    		if(ds.toString() == "Jueves")
    			aux.add(DataDiasSemana.Jueves);
    		if(ds.toString() == "Viernes")
    			aux.add(DataDiasSemana.Viernes);
    		if(ds.toString() == "Sabado")
    			aux.add(DataDiasSemana.Sabado);
    		if(ds.toString() == "Lunes")
    			aux.add(DataDiasSemana.Domingo);
        });
    	result.setDiasSemana(aux);
    	result.setDiasEspecificos(HibernateUtils.initializeAndUnproxy(this.getDiasEspecificos()));
    	if(this.getHorarios()!=null && conHijos){
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
    
    public void setTipo(String val){
        this.tipo = val;
    }
    
    public String getTipo(){
        return this.tipo;
    }
}