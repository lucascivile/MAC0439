package bd.relacional;

import java.sql.*;
import java.util.ArrayList;

import modelo.relacional.Vaga;

public class VagaDAO {
    
    private Connection connection;

    public VagaDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection(); 
    }

    /**
    * Cria a vaga no banco.
    */
    public int insert(Vaga vaga) {
        String sql = "insert into vaga "
                + "(cpf_proprietario,liberada,latitude,longitude,largura,comprimento,preco_hora) "
                + "values (?,?,?,?,?,?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, vaga.getCpfProprietario());
            stmt.setBoolean(2, vaga.getLiberada());
            stmt.setDouble(3, vaga.getLatitude());
            stmt.setDouble(4, vaga.getLongitude());
            stmt.setDouble(5, vaga.getLargura());
            stmt.setDouble(6, vaga.getComprimento());
            stmt.setDouble(7, vaga.getPreco());

            stmt.execute();
            stmt.close();


            stmt = connection.prepareStatement("select id_vaga from vaga where cpf_proprietario = ? order by id_vaga desc limit 1");
            stmt.setString(1, vaga.getCpfProprietario());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_vaga");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    /**
    * Retorna a vaga com o id fornecido.
    */
    public Vaga get(int idVaga) {
        try {
            Vaga vaga = null;
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from vaga where id_vaga=?");
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vaga = new Vaga();
                vaga.setIdVaga(rs.getInt("id_vaga"));
                vaga.setCpfProprietario(rs.getString("cpf_proprietario"));
                vaga.setLiberada(rs.getBoolean("liberada"));
                vaga.setLatitude(rs.getDouble("latitude"));
                vaga.setLongitude(rs.getDouble("longitude"));
                vaga.setLargura(rs.getDouble("largura"));
                vaga.setComprimento(rs.getDouble("comprimento"));
                vaga.setPreco(rs.getDouble("preco_hora"));
            }
            rs.close();
            stmt.close();
            return vaga;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "update vaga set cpf_proprietario=?, liberada=?, latitude=?, longitude=?, largura=?, comprimento=?, preco_hora=? "
            + "where id_vaga=?";
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
                + "from vaga where id_vaga=?");
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
            Double longitude, Timestamp inicio, Timestamp fim) {
        try {
            ArrayList<Vaga> vagas = new ArrayList<>();
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from vaga "
                + "where cpf_proprietario != ? and ABS(latitude - ?) < 1 and ABS(longitude - ?) < 1 "
                + "and not exists ( "
                + " select * from acordo as a join solicitacao as s "
                + " on a.id_solicitacao = s.id_solicitacao "
                + " where s.id_vaga = vaga.id_vaga and "
                + " (momento_inicio > ? or momento_fim < ?)) "
            );
            stmt.setString(1, userCpf);
            stmt.setDouble(2, latitude);
            stmt.setDouble(3, longitude);
            stmt.setTimestamp(4, inicio);
            stmt.setTimestamp(5, fim);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getInt("id_vaga"));
                vaga.setCpfProprietario(rs.getString("cpf_proprietario"));
                vaga.setLiberada(rs.getBoolean("liberada"));
                vaga.setLatitude(rs.getDouble("latitude"));
                vaga.setLongitude(rs.getDouble("longitude"));
                vaga.setLargura(rs.getDouble("largura"));
                vaga.setComprimento(rs.getDouble("comprimento"));
                vaga.setPreco(rs.getDouble("preco_hora"));

                vagas.add(vaga);
            }
            rs.close();
            stmt.close();
            return vagas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
