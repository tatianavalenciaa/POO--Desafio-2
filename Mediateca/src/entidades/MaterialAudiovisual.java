package entidades;

public class MaterialAudiovisual extends MaterialPadre {
    
    private String duracion;
    private String genero;

    public MaterialAudiovisual() {
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
