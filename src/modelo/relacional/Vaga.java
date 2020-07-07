package modelo.relacional;

public class Vaga {
    
    private int idVaga;
    private String cpfProprietario;
    private Boolean liberada;
    private Double latitude;
    private Double longitude;
    private Double largura;
    private Double comprimento;
    private Double preco;

    public int getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(int novo) {
        this.idVaga = novo;
    }

    public String getCpfProprietario() {
        return this.cpfProprietario;
    }

    public void setCpfProprietario(String novo) {
        this.cpfProprietario = novo;
    }

    public Boolean getLiberada() {
        return this.liberada;
    }

    public void setLiberada(Boolean novo) {
        this.liberada = novo;
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

    public Double getLargura() {
        return this.largura;
    }

    public void setLargura(Double novo) {
        this.largura = novo;
    }

    public Double getComprimento() {
        return this.comprimento;
    }

    public void setComprimento(Double novo) {
        this.comprimento = novo;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double novo) {
        this.preco = novo;
    }
}