package modelo.documentos;

import java.util.ArrayList;

public class Vaga {
    
    private int idVaga;
    private ArrayList<Acordo> acordos;
    private ArrayList<Avaliacao> avaliacoes;

    public int getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(int novo) {
        this.idVaga = novo;
    }

    public ArrayList<Acordo> getAcordos() {
        return this.acordos;
    }

    public void setAcordos(ArrayList<Acordo> novo) {
        this.acordos = novo;
    }

    public ArrayList<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    public void setAvaliacoes(ArrayList<Avaliacao> novo) {
        this.avaliacoes = novo;
    }
}
