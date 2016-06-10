package lcbs.models;

import java.io.Serializable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Terminal extends PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    private List<Telefono> telefonosContacto;
    private List<Email> mailsDeContacto;
    private Boolean aceptaEncomiendas;

 

    public Terminal() {
        telefonosContacto = new List<Telefono>();
        mailsDeContacto = new List<Email>();
        aceptaEncomiendas = false;
    }
    
    public Terminal(String id, String nom, String uMap, List<Telefono> tels, List<Email> mails, Boolean acEnc) {
        super(id, nom, uMap);
        this.telefonosContacto = tels;
        this.mailsDeContacto = mails;
        this.aceptaEncomiendas = acEnc;
    }
    
    public void setTelefonosContacto(List<Telefono> val){
        this.telefonosContacto = val;
    }
    
    public List<Telefono> getTelefonosContacto(){
        return this.telefonosContacto;
    }

    public void setMailsDeContacto(List<Email> val){
        this.mailsDeContacto = val;
    }
    
    public List<Email> getMailsDeContacto(){
        return this.mailsDeContacto;
    }

    public void setAceptaEncomiendas(Boolean val){
        this.aceptaEncomiendas = val;
    }
    
    public Boolean getAceptaEncomiendas(){
        return this.aceptaEncomiendas;
    }

    
}