package modelo.relacional;

public class Motorista {
    
    private String cpfUsuario;
    private String cnh;

    public String getCpfUsuario() {
        return this.cpfUsuario;
    }

    public void setCpfUsuario(String novo) {
        this.cpfUsuario = novo;
    }

    public String getCnh() {
        return this.cnh;
    }

    public void setCnh(String novo) {
        this.cnh = novo;
    }
}
