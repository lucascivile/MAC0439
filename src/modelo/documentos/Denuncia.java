package modelo.documentos;

public class Denuncia {

    private String comentario;
    private Boolean pM;

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String novo) {
        this.comentario = novo;
    }

    public Boolean getPM() {
        return this.pM;
    }

    public void setPM(Boolean novo) {
        this.pM = novo;
    }
}
