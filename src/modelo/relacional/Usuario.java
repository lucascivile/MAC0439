package modelo.relacional;

import java.util.Date;

public class Usuario {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private Date nascimento;

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String novo) {
        this.cpf = novo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novo) {
        this.nome = novo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String novo) {
        this.email = novo;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String novo) {
        this.senha = novo;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(Date novo) {
        this.nascimento = novo;
    }
}
