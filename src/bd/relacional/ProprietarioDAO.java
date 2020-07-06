package bd.relacional;

import java.sql.*;

import modelo.relacional.Proprietario;

public class ProprietarioDAO {

	// a conexão com o banco de dados
	private Connection connection;

	public ProprietarioDAO() {
		this.connection = ConnectionFactory.getInstance().getConnection(); 
	}

	public void insert(Proprietario proprietario) {
		String sql = "insert into proprietario "
				+ "(cpfUsuario,logradouro,numero,complemento,cep)" + " values (?,?,?,?,?) on conflict do nothing";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, proprietario.getCpfUsuario());
			stmt.setString(2, proprietario.getLogradouro());
			stmt.setString(3, proprietario.getNumero());
			stmt.setString(4, proprietario.getComplemento());
			stmt.setString(5, proprietario.getCep());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public Proprietario get(String cpfUsuario) {
		try {
			Proprietario proprietario = null;
			
			PreparedStatement stmt = connection.prepareStatement("select * "
					+ "from proprietario where cpfUsuario=?");
			stmt.setString(1, cpfUsuario);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				proprietario = new Proprietario();
				proprietario.setCpfUsuario(rs.getString("cpfUsuario"));
				proprietario.setLogradouro(rs.getString("logradouro"));
				proprietario.setNumero(rs.getString("numero"));
				proprietario.setComplemento(rs.getString("complemento"));
				proprietario.setCep(rs.getString("cep"));
			}
			rs.close();
			stmt.close();
			return proprietario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Proprietario proprietario) {
		String sql = "update proprietario set logradouro=?, numero=?, complemento=?, cep=? "
				+ "where cpfUsuario=?";
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
					+ "from proprietario where cpfUsuario=?");
			stmt.setString(1, proprietario.getCpfUsuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}


