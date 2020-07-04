package modelo.relacional;

public class Agente {
    
    private String cpfUsuario;
    private String registroMunicipal;

    public String getCpfUsuario() {
        return this.cpfUsuario;
    }

    public void setCpfUsuario(String novo) {
        this.cpfUsuario = novo;
    }

    public String getRegistroMunicipal() {
        return this.registroMunicipal;
    }

    public void setRegistroMunicipal(String novo) {
        this.registroMunicipal = novo;
    }
}
