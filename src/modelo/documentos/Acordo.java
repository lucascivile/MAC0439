package modelo.documentos;

import java.util.ArrayList;

public class Acordo {

    private int idAcordo;
    private int notaPM;
    private int notaMP;
    private ArrayList<Denuncia> denuncias;

    public int getIdAcordo() {
        return this.idAcordo;
    }

    public void setIdAcordo(int novo) {
        this.idAcordo = novo;
    }

    public int getNotaPM() {
        return this.notaPM;
    }

    public void setNotaPM(int novo) {
        this.notaPM = novo;
    }

    public int getNotaMP() {
        return this.notaMP;
    }

    public void setNotaMP(int novo) {
        this.notaMP = novo;
    }

    public ArrayList<Denuncia> getDenuncias() {
        return this.denuncias;
    }

    public void setDenuncias(ArrayList<Denuncia> novo) {
        this.denuncias = novo;
    }
}
