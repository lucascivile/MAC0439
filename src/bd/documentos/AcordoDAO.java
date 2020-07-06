package bd.documentos;

import java.sql.*;
import java.util.ArrayList;

import modelo.documentos.Acordo;


public class AcordoDAO {
    
    private Connection connection;

    public AcordoDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();    
    }

    /**
    * Cria a acordo no banco.
    */
    public void insert(Acordo acordo) {
      String sql = "insert into acordo "
                    + "(idSolicitacao)" + " values (?) on conflict do nothing";

      try {
        // prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);

        // seta os valores

        stmt.setInt(1, acordo.getIdSolicitacao());

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
    * Retorna o acordo com o idAcordo fornecido.
    */
    public Acordo get(int idAcordo) {
      try {
        Acordo acordo = null;
        
        PreparedStatement stmt = connection.prepareStatement("select * "
            + "from acordo where idAcordo=?");
        stmt.setInt(1, idAcordo);
        ResultSet rs = stmt.executeQuery();
  
        if (rs.next()) {
          acordo = new Acordo();
          acordo.setIdSolicitacao(rs.getInt("idSolicitacao"));
        }
        rs.close();
        stmt.close();
        return acordo;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void update(Acordo acordo) {
      String sql = "update acordo set idSolicitacao=? "
          + "where idAcordo=?";
      try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, acordo.getIdSolicitacao());
        stmt.setInt(2, acordo.getIdAcordo());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void remove(Acordo acordo) {
      try {
        PreparedStatement stmt = connection.prepareStatement("delete "
            + "from acordo where idAcordo=?");
        stmt.setInt(1, acordo.getIdAcordo());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public ArrayList<Acordo> list() {
      return null;
    }
}

