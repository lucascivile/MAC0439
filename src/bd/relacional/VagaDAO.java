package bd.relacional;

import java.util.ArrayList;
import java.util.Date;

import modelo.relacional.Vaga;

public class VagaDAO {
    
    // private Connection connection;

    public VagaDAO() {
		// this.connection = ConnectionFactory.getInstance().getConnection();
    }

    /**
    * Cria a vaga no banco.
    * Retorna o id da vaga inserida.
    */
    public int insert(Vaga vaga) {
      return 0;
    }

    /**
    * Retorna a vaga com o id fornecido.
    */
    public Vaga get(int idVaga) {
        return null;
    }

    /**
    * Retorna um ArrayList de todas as vagas que:
    *  1) estão disponíveis no intervalo de tempo fornecido;
    *  2) são próximas às coordenadas fornecidas;
    *  3) não pertencem ao proprietário do cpf fornecido.
    */
    public ArrayList<Vaga> listFreeByLocationAndTime(String userCpf, Double latitude,
            Double longitude, Date inicio, Date fim) {
        return null;
    }
}
