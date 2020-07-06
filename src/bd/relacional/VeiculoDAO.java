package bd.relacional;

import java.sql.*;

import modelo.relacional.Veiculo;

public class VeiculoDAO {

	// a conexão com o banco de dados
	private Connection connection;

	public VeiculoDAO() {
		this.connection = ConnectionFactory.getInstance().getConnection(); 
	}

	public void insert(Veiculo veiculo) {
		String sql = "insert into veiculo "
				+ "(cpfMotorista,modelo,ano,cor,placa)" + " values (?,?,?,?,?) on conflict do nothing";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, veiculo.getCpfMotorista());
			stmt.setString(2, veiculo.getModelo());
			stmt.setInt(3, veiculo.getAno());
			stmt.setString(4, veiculo.getCor());
			stmt.setString(5, veiculo.getPlaca());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public Veiculo get(String placa) {
		try {
			Veiculo veiculo = null;
			
			PreparedStatement stmt = connection.prepareStatement("select * "
					+ "from veiculo where placa=?");
			stmt.setString(1, placa);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				veiculo = new Veiculo();
				veiculo.setCpfMotorista(rs.getString("cpfMotorista"));
				veiculo.setModelo(rs.getString("modelo"));
				veiculo.setAno(rs.getInt("ano"));
				veiculo.setCor(rs.getString("cor"));
				veiculo.setPlaca(rs.getString("placa"));
			}
			rs.close();
			stmt.close();
			return veiculo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Veiculo veiculo) {
		String sql = "update veiculo set nomeAluno=?, formacao=?, nivel=?, idade=? "
				+ "where placa=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, veiculo.getCpfMotorista());
			stmt.setString(2, veiculo.getModelo());
			stmt.setInt(3, veiculo.getAno());
			stmt.setString(4, veiculo.getCor());
			stmt.setString(5, veiculo.getPlaca());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Veiculo veiculo) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete "
					+ "from veiculo where placa=?");
			stmt.setString(1, veiculo.getPlaca());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

