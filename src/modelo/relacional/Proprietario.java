package modelo.relacional;

public class Proprietario {

    private String cpfUsuario;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;

    public String getCpfUsuario() {
        return this.cpfUsuario;
    }

    public void setCpfUsuario(String novo) {
        this.cpfUsuario = novo;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String novo) {
        this.logradouro = novo;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String novo) {
        this.numero = novo;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String novo) {
        this.complemento = novo;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String novo) {
        this.cep = novo;
    }
}
