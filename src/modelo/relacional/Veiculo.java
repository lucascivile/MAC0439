package modelo.relacional;

public class Veiculo {
    
    String cpfMotorista;
    private String modelo;
    private Integer ano;
    private String cor;
    private String placa;

    public String getCpfMotorista() {
        return this.cpfMotorista;
    }

    public void setCpfMotorista(String novo) {
        this.cpfMotorista = novo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String novo) {
        this.modelo = novo;
    }

    public Integer getAno() {
        return this.ano;
    }

    public void setAno(Integer novo) {
        this.ano = novo;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String novo) {
        this.cor = novo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String novo) {
        this.placa = novo;
    }
}
