package bd.relacional;

import java.sql.*;

import modelo.relacional.Agente;



public class AgenteDAO {
    
    private Connection connection;

    public AgenteDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();     
    }

    /**
    * Cria a agente no banco.
    */
    public void insert(Agente agente) {
        String sql = "insert into agente_municipal "
                        + "(cpf_usuario,registro_municipal)" + " values (?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, agente.getCpfUsuario());
            stmt.setString(2, agente.getRegistroMunicipal());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
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
                + "from agente_municipal where cpf_usuario=?");
            stmt.setString(1, cpfUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                agente = new Agente();
                agente.setCpfUsuario(rs.getString("cpf_usuario"));
                agente.setRegistroMunicipal(rs.getString("registro_municipal"));
            }
            rs.close();
            stmt.close();
            return agente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Agente agente) {
        String sql = "update agente_municipal set registro_municipal=? "
            + "where cpf_usuario=?";
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
                + "from agente_municipal where cpf_usuario=?");
            stmt.setString(1, agente.getCpfUsuario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

