package modelo.relacional;

public class Acordo {
    
    private int idAcordo;
    private int idSolicitacao;

    public int getIdAcordo() {
        return this.idAcordo;
    }

    public int getIdSolicitacao() {
        return this.idSolicitacao;
    }

    public void setIdSolicitacao(int novo) {
        this.idSolicitacao = novo;
    }
}
