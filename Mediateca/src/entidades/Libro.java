package entidades;

public class Libro extends MaterialEscrito {
    
    private String autor;
    private Integer numPag;
    private String isbn;
    private Integer anioPub;

    public Libro() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumPag() {
        return numPag;
    }

    public void setNumPag(Integer numPag) {
        this.numPag = numPag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnioPub() {
        return anioPub;
    }

    public void setAnioPub(Integer anioPub) {
        this.anioPub = anioPub;
    }   

}
