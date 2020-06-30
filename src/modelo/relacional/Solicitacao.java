package modelo.relacional;

public class Solicitacao {
    
    private int idSolicitacao;
    private String cpfMotorista;
    private Integer idVaga;
    private String inicio;
    private String fim;
    private Boolean resposta;

    public int getIdSolicitacao() {
        return this.idSolicitacao;
    }

    public String getCpfMotorista() {
        return this.cpfMotorista;
    }

    public void setCpfMotorista(String novo) {
        this.cpfMotorista = novo;
    }

    public Integer getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(Integer novo) {
        this.idVaga = novo;
    }

    public String getInicio() {
        return this.inicio;
    }

    public void setInicio(String novo) {
        this.inicio = novo;
    }

    public String getFim() {
        return this.fim;
    }

    public void setFim(String novo) {
        this.fim = novo;
    }

    public Boolean getResposta() {
        return this.resposta;
    }

    public void setResposta(Boolean novo) {
        this.resposta = novo;
    }
}