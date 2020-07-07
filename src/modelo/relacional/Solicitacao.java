package modelo.relacional;

import java.sql.Timestamp;

public class Solicitacao {
    
    private int idSolicitacao;
    private String cpfMotorista;
    private Integer idVaga;
    private Timestamp inicio;
    private Timestamp fim;
    private Boolean resposta;

    public int getIdSolicitacao() {
        return this.idSolicitacao;
    }

    public void setIdSolicitacao(int novo) {
        this.idSolicitacao = novo;
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

    public Timestamp getInicio() {
        return this.inicio;
    }

    public void setInicio(Timestamp novo) {
        this.inicio = novo;
    }

    public Timestamp getFim() {
        return this.fim;
    }

    public void setFim(Timestamp novo) {
        this.fim = novo;
    }

    public Boolean getResposta() {
        return this.resposta;
    }

    public void setResposta(Boolean novo) {
        this.resposta = novo;
    }
}
