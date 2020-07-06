package bd.relacional;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import modelo.relacional.Solicitacao;



public class SolicitacaoDAO {
    
    private Connection connection;

    public SolicitacaoDAO() {
      this.connection = ConnectionFactory.getInstance().getConnection();     
    }

    /**
    * Cria a solicitacao no banco.
    */
    public void insert(Solicitacao solicitacao) {
      String sql = "insert into solicitacao "
				    + "(cpfMotorista,idVaga,inicio,fim,resposta)" + " values (?,?,?,?,?) on conflict do nothing";

      try {
        // prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);

        // seta os valores

        stmt.setString(1, solicitacao.getCpfMotorista());
        stmt.setInt(2, solicitacao.getIdVaga());
        stmt.setDate(3, solicitacao.getInicio());
        stmt.setDate(4, solicitacao.getFim());
        stmt.setBoolean(5, solicitacao.getResposta());

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
    * Retorna a solicitacao com o id fornecido.
    */
    public Solicitacao get(int idSolicitacao) {
      try {
        Solicitacao solicitacao = null;
        
        PreparedStatement stmt = connection.prepareStatement("select * "
            + "from solicitacao where idSolicitacao=?");
        stmt.setInt(1, idSolicitacao);
        ResultSet rs = stmt.executeQuery();
  
        if (rs.next()) {
          solicitacao = new Solicitacao();
          solicitacao.setCpfMotorista(rs.getString("cpfMotorista"));
          solicitacao.setIdVaga(rs.getInt("idVaga"));
          solicitacao.setInicio(rs.getDate("inicio"));
          solicitacao.setFim(rs.getDate("fim"));
          solicitacao.setResposta(rs.getBoolean("resposta"));
        }
        rs.close();
        stmt.close();
        return solicitacao;
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void update(Solicitacao solicitacao) {
      String sql = "update solicitacao set cpfMotorista=?, idVaga=?, inicio=?, fim=?, resposta=? "
          + "where idSolicitacao=?";
      try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, solicitacao.getCpfMotorista());
        stmt.setInt(2, solicitacao.getIdVaga());
        stmt.setDate(3, solicitacao.getInicio());
        stmt.setDate(4, solicitacao.getFim());
        stmt.setBoolean(5, solicitacao.getResposta());
        stmt.setInt(8, solicitacao.getIdSolicitacao());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public void remove(Solicitacao solicitacao) {
      try {
        PreparedStatement stmt = connection.prepareStatement("delete "
            + "from solicitacao where idSolicitacao=?");
        stmt.setInt(1, solicitacao.getIdSolicitacao());
        stmt.execute();
        stmt.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    /**
    * Retorna um ArrayList de todas as solicitacaos que:
    *  1) estão disponíveis no intervalo de tempo fornecido;
    *  2) são próximas às coordenadas fornecidas;
    *  3) não pertencem ao proprietário do cpf fornecido.
    */
    public ArrayList<Solicitacao> listFreeByLocationAndTime(String userCpf, Double latitude,
            Double longitude, Date inicio, Date fim) {
        return null;
    }

    /**
    * Retorna um ArrayList de todas as solicitações realizadas pelo motorista
    * com o cpf fornecido.
    */
    public ArrayList<Solicitacao> listByCpfMotorista(String cpf) {
        return null;
    }

    /**
    * Retorna um ArrayList de todas as solicitações sem resposta do proprietário
    * cujo cpf é fornecido.
    */
    public ArrayList<Solicitacao> listUnansweredByCpfProprietario(String cpf) {
        return null;
    }
}
