package bd.documentos;

import java.sql.*;

import modelo.documentos.Usuario;

public class UsuarioDAO {

	// a conexão com o banco de dados
	private Connection connection;

	public UsuarioDAO() {
		this.connection = ConnectionFactory.getInstance().getConnection(); 
	}

	public void insert(Usuario usuario) {
		String sql = "insert into usuario "
				+ "(cpf,nome,email,senha,nascimento)" + " values (?,?,?,?,?) on conflict do nothing";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.setDate(5, usuario.getNascimento());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public Usuario get(String cpf) {
		try {
			Usuario usuario = null;
			
			PreparedStatement stmt = connection.prepareStatement("select * "
					+ "from usuario where cpf=?");
			stmt.setString(1, cpf);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setNascimento(rs.getDate("nascimento"));
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Usuario usuario) {
		String sql = "update usuario set nome=?, email=?, senha=?, nascimento=? "
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

	public void remove(Usuario usuario) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete "
					+ "from usuario where cpf=?");
			stmt.setString(1, usuario.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

