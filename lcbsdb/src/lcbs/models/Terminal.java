package lcbs.models;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Terminal extends PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    @Embedded
    private List<Telefono> telefonosContacto;
    @Embedded
    private List<Email> mailsDeContacto;
    private Boolean aceptaEncomiendas;

 

    public Terminal() {}
    
    public Terminal(String id, String nom, String uMap, List<Telefono> tels, List<Email> mails, Boolean acEnc, Boolean elim) {
        super(id, nom, uMap, elim);
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