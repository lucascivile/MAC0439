package bd.grafos;

import java.sql.*;

import modelo.grafos.Agente;

public class AgenteDAO {
    
    private Connection connection;

    public AgenteDAO() {
      this.connection = ConnectionFactory.getInstance().getConnection();     
    }

    /**
    * Cria a agente no banco.
    */
    public void insert(Agente agente) {
      String sql = "insert into agente "
				    + "(cpfUsuario,registroMunicipal)" + " values (?,?) on conflict do nothing";

      try {
        // prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);

        // seta os valores

        stmt.setString(1, agente.getCpfUsuario());
        stmt.setString(2, agente.getRegistroMunicipal());

        // executa
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        // A SQLException é "encapsulada" em uma RuntimeException
        // para desacoplar o código da API de JDBC
        throw new RuntimeException(e);
      }

    }

    /**
    * Retorna o agente com o cpf fornecido.
    */
    public Agente get(String cpfUsuario) {
      try {
        Agente agente = null;
        
        PreparedStatement stmt = connection.prepareStatement("select * "
            + "from agente where cpfUsuario=?");
        stmt.setString(1, cpfUsuario);
        ResultSet rs = stmt.executeQuery();
  
        if (rs.next()) {
          agente = new Agente();
          agente.setCpfUsuario(rs.getString("cpfUsuario"));
          agente.setRegistroMunicipal(rs.getString("registroMunicipal"));
        }
        rs.close();
        stmt.close();
        return agente;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void update(Agente agente) {
      String sql = "update agente set registroMunicipal=? "
          + "where cpfUsuario=?";
      try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, agente.getRegistroMunicipal());
        stmt.setString(2, agente.getCpfUsuario());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void remove(Agente agente) {
      try {
        PreparedStatement stmt = connection.prepareStatement("delete "
            + "from agente where cpfUsuario=?");
        stmt.setString(1, agente.getCpfUsuario());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
}

