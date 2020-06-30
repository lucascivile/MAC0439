package action;

import java.util.ArrayList;

public class ActionAcordo {

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos futuros do motorista.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listFutureByMotorista(String cpf) {
        return null;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos passados do motorista.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listPastByMotorista(String cpf) {
        return null;
    }

    /**
    * Se teve sucesso, retorna  um array de strings relativas aos acordos futuros do proprietário.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listFutureByProprietario(String cpf) {
        return null;
    }

    /**
    * Se teve sucesso, retorna um array de strings relativas aos acordos passados do proprietário.
    * Retorna nulo caso contrário.
    */
    public static ArrayList<String>  listPastByProprietario(String cpf) {
        return null;
    }

    /**
    * Insere a nota do motorista para o proprietário no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertNotaToProprietario(int idAcordo, int nota) {
        return true;
    }

    /**
    * Insere a denúncia do motorista para o proprietário no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertDenunciaToProprietario(int idAcordo, String denuncia) {
        return true;
    }

    /**
    * Insere a nota do proprietário para o motorista no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertNotaToMotorista(int idAcordo, int nota) {
        return true;
    }

    /**
    * Insere a denúncia do proprietário para o motorista no acordo fornecido.
    * Retorna verdadeiro se teve sucesso, falso caso contrário.
    */
    public static boolean insertDenunciaToMotorista(int idAcordo, String denuncia) {
        return true;
    }
}
