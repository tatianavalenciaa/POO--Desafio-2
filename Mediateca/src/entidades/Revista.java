package entidades;

import java.util.Date;

public class Revista extends MaterialEscrito {
    
    private String periocidad;
    private Date fechaPub;
    
    public Revista() {
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public Date getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(Date fechaPub) {
        this.fechaPub = fechaPub;
    }

}
