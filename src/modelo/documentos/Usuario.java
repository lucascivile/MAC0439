package modelo.documentos;

import java.util.ArrayList;

public class Usuario {
    
    private String cpf;
    private ArrayList<Acordo> acordos;

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String novo) {
        this.cpf = novo;
    }

    public ArrayList<Acordo> getAcordos() {
        return this.acordos;
    }

    public void setAcordos(ArrayList<Acordo> novo) {
        this.acordos = novo;
    }
}
