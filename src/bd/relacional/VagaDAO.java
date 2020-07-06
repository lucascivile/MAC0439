package bd.relacional;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import modelo.relacional.Vaga;

public class VagaDAO {
    
    private Connection connection;

    public VagaDAO() {
		  this.connection = ConnectionFactory.getInstance().getConnection(); 
    }

    /**
    * Cria a vaga no banco.
    */
    public void insert(Vaga vaga) {
      String sql = "insert into vaga "
				    + "(cpfProprietario,liberada,latitude,longitude,largura,comprimento,preco)" + " values (?,?,?,?,?,?,?) on conflict do nothing";

      try {
        // prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);

        // seta os valores

        stmt.setString(1, vaga.getCpfProprietario());
        stmt.setBoolean(2, vaga.getLiberada());
        stmt.setDouble(3, vaga.getLatitude());
        stmt.setDouble(4, vaga.getLongitude());
        stmt.setDouble(5, vaga.getLargura());
        stmt.setDouble(6, vaga.getComprimento());
        stmt.setDouble(7, vaga.getPreco());

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
    * Retorna a vaga com o id fornecido.
    */
    public Vaga get(int idVaga) {
      try {
        Vaga vaga = null;
        
        PreparedStatement stmt = connection.prepareStatement("select * "
            + "from vaga where idVaga=?");
        stmt.setInt(1, idVaga);
        ResultSet rs = stmt.executeQuery();
  
        if (rs.next()) {
          vaga = new Vaga();
          vaga.setCpfProprietario(rs.getString("cpfProprietario"));
          vaga.setLiberada(rs.getBoolean("liberada"));
          vaga.setLatitude(rs.getDouble("latitude"));
          vaga.setLongitude(rs.getDouble("longitude"));
          vaga.setLargura(rs.getDouble("largura"));
          vaga.setComprimento(rs.getDouble("comprimento"));
          vaga.setPreco(rs.getDouble("preco"));
        }
        rs.close();
        stmt.close();
        return vaga;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void update(Vaga vaga) {
      String sql = "update vaga set cpfProprietario=?, liberada=?, latitude=?, longitude=?, largura=?, comprimento=?, preco=? "
          + "where idVaga=?";
      try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, vaga.getCpfProprietario());
        stmt.setBoolean(2, vaga.getLiberada());
        stmt.setDouble(3, vaga.getLatitude());
        stmt.setDouble(4, vaga.getLongitude());
        stmt.setDouble(5, vaga.getLargura());
        stmt.setDouble(6, vaga.getComprimento());
        stmt.setDouble(7, vaga.getPreco());
        stmt.setInt(8, vaga.getIdVaga());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void remove(Vaga vaga) {
      try {
        PreparedStatement stmt = connection.prepareStatement("delete "
            + "from vaga where idVaga=?");
        stmt.setInt(1, vaga.getIdVaga());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
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
