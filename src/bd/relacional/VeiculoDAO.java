package bd.relacional;

import java.sql.Connection;
import modelo.relacional.Veiculo;

public class VeiculoDAO {
    
    private Connection connection;

    public VeiculoDAO() {
		    this.connection = ConnectionFactory.getInstance().getConnection();
    }

    /**
    * Cria o veículo no banco.
    */
    public void insert(Veiculo Veiculo) {
    }
}
