package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Parada extends PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    public Parada() {}
    
    public Parada(String id, String nom, String uMap, Boolean elim) {
        super(id, nom, uMap, elim);
    }
    
}