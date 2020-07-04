package modelo.documentos;

public class Avaliacao {
    
    private String cpfAgente;
    private Boolean resultado;
    private String comentario;

    public String getCpfAgente() {
        return this.cpfAgente;
    }

    public void setCpfAgente(String novo) {
        this.cpfAgente = novo;
    }

    public Boolean getResultado() {
        return this.resultado;
    }

    public void setResultado(Boolean novo) {
        this.resultado = novo;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String novo) {
        this.comentario = novo;
    }
}
