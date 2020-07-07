package bd.grafos;

import java.sql.*;
import java.util.ArrayList;

import modelo.relacional.Estacionamento;

public class EstacionamentoDAO {

	// a conexão com o banco de dados
	private Connection connection;

	public estacionamentoDAO() {
		this.connection = ConnectionFactory.getInstance().getConnection(); 
	}

	public void insert(Estacionamento estacionamento) {
		String sql = "insert into estacionamento "
				+ "(nome,latitude,longitude)" + " values (?,?,?) on conflict do nothing";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, estacionamento.getNome());
			stmt.setDouble(2, estacionamento.getLatitude());
			stmt.setDouble(3, estacionamento.getLongitude());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}

	public Estacionamento get(Double latitude, Double longitude) {
		try {
			Estacionamento estacionamento = null;
			
			PreparedStatement stmt = connection.prepareStatement("select * "
					+ "from estacionamento where latitude=? and longitude=?");
      stmt.setDouble(1, latitude);
      stmt.setDouble(2, longitude);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				estacionamento = new estacionamento();
				estacionamento.setNome(rs.getString("nome"));
				estacionamento.setLatitude(rs.getDouble("latitude"));
				estacionamento.setLongitude(rs.getDouble("longitude"));
			}
			rs.close();
			stmt.close();
			return estacionamento;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Estacionamento estacionamento) {
		String sql = "update estacionamento set nome=? "
				+ "where latitude=? and longitude=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, estacionamento.getNome());
			stmt.setDouble(2, estacionamento.getLatitude());
			stmt.setDouble(3, estacionamento.getLongitude());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Estacionamento estacionamento) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete "
					+ "from estacionamento where latitude=? and longitude");
      stmt.setDouble(1, estacionamento.getLatitude());
      stmt.setDouble(2, estacionamento.getLongitude());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
  	}

	public ArrayList<Estacionamento> listByCoordinates(Double latitude, Double longitude) {
		return null;
	}
}


