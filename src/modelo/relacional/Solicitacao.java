package modelo.relacional;

import java.util.Date;

public class Solicitacao {
    
    private int idSolicitacao;
    private String cpfMotorista;
    private Integer idVaga;
    private Date inicio;
    private Date fim;
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

    public Date getInicio() {
        return this.inicio;
    }

    public void setInicio(Date novo) {
        this.inicio = novo;
    }

    public Date getFim() {
        return this.fim;
    }

    public void setFim(Date novo) {
        this.fim = novo;
    }

    public Boolean getResposta() {
        return this.resposta;
    }

    public void setResposta(Boolean novo) {
        this.resposta = novo;
    }
}
