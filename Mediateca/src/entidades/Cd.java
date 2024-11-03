package entidades;

public class Cd extends MaterialAudiovisual {
    
    private String artista;
    private Integer numCanciones;

    public Cd() {
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Integer getNumCanciones() {
        return numCanciones;
    }

    public void setNumCanciones(Integer numCanciones) {
        this.numCanciones = numCanciones;
    }

}
