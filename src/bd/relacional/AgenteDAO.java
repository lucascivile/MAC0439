package bd.relacional;

import java.sql.Connection;
import modelo.relacional.Agente;

public class AgenteDAO {
    
    private Connection connection;

    public AgenteDAO() {
		    this.connection = ConnectionFactory.getInstance().getConnection();
    }

    /**
    * Cria o acordo no banco.
    */
    public void insert(Agente agente) {
    }

    /**
    * Retorna o Agente com o cpf fornecido.
    */
    public Agente get(String cpf) {
        return null;
    }
}
