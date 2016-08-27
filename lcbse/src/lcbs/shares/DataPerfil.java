package lcbs.shares;

import java.util.List;

public class DataPerfil{
    
	private String id;
    
    private String nombre;
    private Boolean modulo1;
    private Boolean modulo2;
    private Boolean modulo3;
    private Boolean modulo4;
    private Boolean modulo5;
    private Boolean modulo6;
    private Boolean modulo7;
    private Boolean modulo8;
    private List<DataEmpleado> empleados;
    
     

    public DataPerfil() {}
    
    public DataPerfil(String id, String nom, Boolean mod1, Boolean mod2, Boolean mod3, Boolean mod4, Boolean mod5, Boolean mod6, Boolean mod7, Boolean mod8, List<DataEmpleado> emp) {
        this.id = id;
        this.nombre = nom;
        this.modulo1 = mod1;
        this.modulo2 = mod2;
        this.modulo3 = mod3;
        this.modulo4 = mod4;
        this.modulo5 = mod5;
        this.modulo6 = mod6;
        this.modulo7 = mod7;
        this.modulo8 = mod8;
        this.empleados = emp;
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

    public void setModulo1(Boolean val){
        this.modulo1 = val;
    }
    
    public Boolean getModulo1(){
        return this.modulo1;
    }

    public void setModulo2(Boolean val){
        this.modulo2 = val;
    }
    
    public Boolean getModulo2(){
        return this.modulo2;
    }

    public void setModulo3(Boolean val){
        this.modulo3 = val;
    }
    
    public Boolean getModulo3(){
        return this.modulo3;
    }

    public void setModulo4(Boolean val){
        this.modulo4 = val;
    }
    
    public Boolean getModulo4(){
        return this.modulo4;
    }

    public void setModulo5(Boolean val){
        this.modulo5 = val;
    }
    
    public Boolean getModulo5(){
        return this.modulo5;
    }

    public void setModulo6(Boolean val){
        this.modulo6 = val;
    }
    
    public Boolean getModulo6(){
        return this.modulo6;
    }

    public void setModulo7(Boolean val){
        this.modulo7 = val;
    }
    
    public Boolean getModulo7(){
        return this.modulo7;
    }

    public void setModulo8(Boolean val){
        this.modulo8 = val;
    }
    
    public Boolean getModulo8(){
        return this.modulo8;
    }
    
    public void setEmpleados(List<DataEmpleado> val){
        this.empleados = val;
    }
    
    public List<DataEmpleado> getEmpleados(){
        return this.empleados;
    }
}