package modelo.grafos;

public class Vaga {
    
    private int idVaga;
    private String bairro;
    private Double latitude;
    private Double longitude;

    public int getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(int novo) {
        this.idVaga = novo;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double novo) {
        this.latitude = novo;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double novo) {
        this.longitude = novo;
    }
}
