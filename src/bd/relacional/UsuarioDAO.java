package bd.relacional;

import java.sql.Connection;
import modelo.relacional.Usuario;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
		    this.connection = ConnectionFactory.getInstance().getConnection();
    }

    /**
    * Cria o usuário no banco.
    */
    public void insert(Usuario usuario) {
    }

    /**
    * Remove do banco o usuário com o cpf fornecido.
    */
    public void remove(String cpf) {
    }

    /**
    * Retorna o usuário com o email fornecido ou nulo, se não houver.
    */
    public Usuario get(String email, String senha) {
        return null;
    }
}
