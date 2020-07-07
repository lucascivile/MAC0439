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
                        + "(cpf_motorista,id_vaga,inicio,fim,resposta)" + " values (?,?,?,?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, solicitacao.getCpfMotorista());
            stmt.setInt(2, solicitacao.getIdVaga());
            stmt.setTimestamp(3, solicitacao.getInicio());
            stmt.setTimestamp(4, solicitacao.getFim());
            stmt.setBoolean(5, solicitacao.getResposta());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
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
                + "from solicitacao where id_solicitacao=?");
            stmt.setInt(1, idSolicitacao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                solicitacao = new Solicitacao();
                solicitacao.setCpfMotorista(rs.getString("cpf_motorista"));
                solicitacao.setIdVaga(rs.getInt("id_vaga"));
                solicitacao.setInicio(rs.getTimestamp("inicio"));
                solicitacao.setFim(rs.getTimestamp("inicio"));
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
        String sql = "update solicitacao set cpf_motorista=?, id_vaga=?, inicio=?, fim=?, resposta=? "
            + "where id_solicitacao=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, solicitacao.getCpfMotorista());
            stmt.setInt(2, solicitacao.getIdVaga());
            stmt.setDate(3, solicitacao.getInicio());
            stmt.setDate(4, solicitacao.getFim());
            stmt.setBoolean(5, solicitacao.getResposta());
            stmt.setInt(6, solicitacao.getIdSolicitacao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Solicitacao solicitacao) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete "
                + "from solicitacao where id_solicitacao=?");
            stmt.setInt(1, solicitacao.getIdSolicitacao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Retorna um ArrayList de todas as solicitações realizadas pelo motorista
    * com o cpf fornecido.
    */
    public ArrayList<Solicitacao> listByCpfMotorista(String cpf) {
        try {
            ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from solicitacao where cpf_motorista = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setIdSolicitacao(rs.getInt("id_solicitacao"));
                solicitacao.setCpfMotorista(rs.getString("cpf_motorista"));
                solicitacao.setIdVaga(rs.getInt("id_vaga"));
                solicitacao.setInicio(rs.getTimestamp("inicio"));
                solicitacao.setFim(rs.getTimestamp("inicio"));
                solicitacao.setResposta(rs.getBoolean("resposta"));

                solicitacoes.add(solicitacao);
            }
            rs.close();
            stmt.close();
            return solicitacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//     select * from solicitacao AS s JOIN vaga AS v on s.id_vaga = v.id_vaga
// where resposta is null and cpf_proprietario = ?

    /**
    * Retorna um ArrayList de todas as solicitações sem resposta do proprietário
    * cujo cpf é fornecido.
    */
    public ArrayList<Solicitacao> listUnansweredByCpfProprietario(String cpf) {
        try {
            ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
            
            PreparedStatement stmt = connection.prepareStatement(
                    "select * from solicitacao AS s JOIN vaga AS v on s.id_vaga = v.id_vaga "
                    + "where resposta is null and cpf_proprietario = ?"
            );
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.setIdSolicitacao(rs.getInt("id_solicitacao"));
                solicitacao.setCpfMotorista(rs.getString("cpf_motorista"));
                solicitacao.setIdVaga(rs.getInt("id_vaga"));
                solicitacao.setInicio(rs.getTimestamp("inicio"));
                solicitacao.setFim(rs.getTimestamp("inicio"));
                solicitacao.setResposta(rs.getBoolean("resposta"));

                solicitacoes.add(solicitacao);
            }
            rs.close();
            stmt.close();
            return solicitacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
