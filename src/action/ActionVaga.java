package action;

import java.util.ArrayList;

public class ActionVaga {
    
    /**
    * Cria uma vaga no banco.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insert(String cpf, String bairro, long latitude, long longitude,
            long largura, long comprimento, long preco) {
        return true;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas às vagas próximas das coordenadas dadas
    * e disponíveis no momento fornecido caso haja vagas disponíveis. Senão, retorna os estacionamentos
    * próximos.
    * Retorna nulo se der erro.
    */
    public static ArrayList<String> listByLocationAndTime(String userCpf,
            String inicio, String fim, long latitude, long longitude) {
        return null;
    }

    /**
    * Se teve sucesso, um array de strings relativas às vagas presentes no bairro do agente
    * fornecido.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String> listByAgenteBairro(String cpf) {
        return null;
    }

    /**
    * Cria uma avaliação de um agente para a vaga fornecida.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertAvaliacao(int idVaga, String cpfAgente, boolean avaliacao, String comentario) {
        return true;
    }
}
