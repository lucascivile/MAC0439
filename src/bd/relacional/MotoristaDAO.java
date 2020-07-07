package bd.relacional;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.relacional.Motorista;



public class MotoristaDAO {
    
    private Connection connection;

    public MotoristaDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();     
    }

    /**
    * Cria a motorista no banco.
    */
    public void insert(Motorista motorista) {
        String sql = "insert into motorista "
                        + "(numero_cnh,cpf_usuario)" + " values (?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, motorista.getCpfUsuario());
            stmt.setString(2, motorista.getCnh());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    * Retorna a motorista com a cnh fornecida.
    */
    public Motorista get(String cnh) {
        try {
            Motorista motorista = null;
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from motorista where numero_cnh=?");
            stmt.setString(1, cnh);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motorista = new Motorista();
                motorista.setCpfUsuario(rs.getString("cpf_usuario"));
                motorista.setCnh(rs.getString("numero_cnh"));
            }
            rs.close();
            stmt.close();
            return motorista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Motorista motorista) {
        String sql = "update motorista set cpf_usuario=? "
            + "where numero_cnh=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, motorista.getCpfUsuario());
            stmt.setString(2, motorista.getCnh());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Motorista motorista) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete "
                + "from motorista where numero_cnh=?");
            stmt.setString(1, motorista.getCnh());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

