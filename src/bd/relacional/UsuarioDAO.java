package bd.relacional;

import java.sql.*;

import modelo.relacional.Usuario;

public class UsuarioDAO {

    // a conexão com o banco de dados
    private Connection connection;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection(); 
    }

    public void insert(Usuario usuario) {
        String sql = "insert into usuario "
                + "(cpf,nome,email,senha,data_nascimento)" + " values (?,?,?,?,?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setDate(5, usuario.getNascimento());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(String email, String senha) {
        try {
            Usuario usuario = null;
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                    + "from usuario where email=? and senha=?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNascimento(rs.getDate("data_nascimento"));
            } else {
                return null;
            }
            
            rs.close();
            stmt.close();
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Usuario usuario) {
        String sql = "update usuario set nome=?, email=?, senha=?, data_nascimento=? "
                + "where cpf=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
      stmt.setDate(4, usuario.getNascimento());
            stmt.setString(5, usuario.getCpf());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String cpf) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete "
                    + "from usuario where cpf=?");
            stmt.setString(1, cpf);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

