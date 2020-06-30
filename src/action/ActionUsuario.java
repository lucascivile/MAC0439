package action;

import java.util.ArrayList;

public class ActionUsuario {

    /**
    * Cria um usuário e o proprietário correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertProprietario(String nome, String cpf, String email,
            String senha, String nascimento, String logradouro, String numero,
            String complemento, String cep) {
        return true;
    }

    /**
    * Cria um usuário e o motorista correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertMotorista(String nome, String cpf, String email,
            String senha, String nascimento, String cnh) {
        return true;
    }

    /**
    * Cria um usuário e o proprietário e motorista correspondentes no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertProprietarioMotorista(String nome, String cpf, String email,
            String senha, String nascimento, String logradouro, String numero,
            String complemento, String cep, String cnh) {
        return true;
    }

    /**
    * Cria um usuário e o agente correspondente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertAgente(String nome, String cpf, String email,
            String senha, String nascimento, String registroMunicipal, String bairro) {
        return true;        
    }

    /**
    * Se as credenciais fornecidas existem no banco, retorna um array com o cpf e o
    * tipo do usuário ("AGENTE", "PROPRIETARIO, etc.") 
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> login(String email, String senha) {
        return null;
    }
    
    /**
    * Retorna a nota média do usuário ou nulo, se houver erro.
    */
    public static Long getNotaMedia(String cpf) {
        return 0L;
    }

    /**
    * Atualiza o endereço do proprietário no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean updateEnderecoProprietario(String cnpj, String logadrouro,
            String numero, String complemento, String cep) {
        return true;
    }

    /**
    * Atualiza o bairro do agente no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean updateBairroAgente(String cpf, String bairro) {
        return true;
    }
}
