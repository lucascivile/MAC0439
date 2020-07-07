package bd.relacional;

import java.sql.*;

import modelo.relacional.Proprietario;

public class ProprietarioDAO {

    private Connection connection;

    public ProprietarioDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection(); 
    }

    public void insert(Proprietario proprietario) {
        String sql = "insert into proprietario "
                + "(cpf_usuario,endereco_logradouro,endereco_numero,endereco_complemento,endereco_cep) "
                + "values (?,?,?,?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, proprietario.getCpfUsuario());
            stmt.setString(2, proprietario.getLogradouro());
            stmt.setString(3, proprietario.getNumero());
            stmt.setString(4, proprietario.getComplemento());
            stmt.setString(5, proprietario.getCep());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Proprietario get(String cpfUsuario) {
        try {
            Proprietario proprietario = null;
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                    + "from proprietario where cpf_usuario=?");
            stmt.setString(1, cpfUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                proprietario = new Proprietario();
                proprietario.setCpfUsuario(rs.getString("cpf_usuario"));
                proprietario.setLogradouro(rs.getString("endereco_logradouro"));
                proprietario.setNumero(rs.getString("endereco_numero"));
                proprietario.setComplemento(rs.getString("endereco_complemento"));
                proprietario.setCep(rs.getString("endereco_cep"));
            }
            rs.close();
            stmt.close();
            return proprietario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Proprietario proprietario) {
        String sql = "update proprietario set endereco_logradouro=?, endereco_numero=?, endereco_complemento=?, endereco_cep=? "
                + "where cpf_usuario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, proprietario.getLogradouro());
            stmt.setString(2, proprietario.getNumero());
            stmt.setString(3, proprietario.getComplemento());
            stmt.setString(4, proprietario.getCep());
            stmt.setString(5, proprietario.getCpfUsuario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Proprietario proprietario) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete "
                    + "from proprietario where cpf_usuario=?");
            stmt.setString(1, proprietario.getCpfUsuario());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


