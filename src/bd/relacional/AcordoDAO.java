package bd.relacional;

import java.sql.*;
import java.util.ArrayList;

import modelo.relacional.Acordo;


public class AcordoDAO {
    
    private Connection connection;

    public AcordoDAO() {
        this.connection = ConnectionFactory.getInstance().getConnection();    
    }

    /**
    * Cria a acordo no banco.
    */
    public void insert(Acordo acordo) {
        String sql = "insert into acordo "
                    + "(id_solicitacao)" + " values (?) on conflict do nothing";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, acordo.getIdSolicitacao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
    * Retorna o acordo com o idAcordo fornecido.
    */
    public Acordo get(int idAcordo) {
        try {
            Acordo acordo = null;
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from acordo where id_acordo=?");
            stmt.setInt(1, idAcordo);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                acordo = new Acordo();
                acordo.setIdAcordo(rs.getInt("id_acordo"));
                acordo.setIdSolicitacao(rs.getInt("id_solicitacao"));
            }
            rs.close();
            stmt.close();
            return acordo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Acordo acordo) {
        String sql = "update acordo set id_solicitacao=? "
            + "where id_acordo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, acordo.getIdSolicitacao());
            stmt.setInt(2, acordo.getIdAcordo());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Acordo acordo) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete "
                + "from acordo where id_acordo=?");
            stmt.setInt(1, acordo.getIdAcordo());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Acordo> list() {
        try {
            ArrayList<Acordo> acordos = new ArrayList<>();
            
            PreparedStatement stmt = connection.prepareStatement("select * "
                + "from acordo");
            ResultSet rs = stmt.executeQuery();
        
            while (rs.next()) {
                Acordo acordo = new Acordo();
                acordo.setIdAcordo(rs.getInt("id_acordo"));
                acordo.setIdSolicitacao(rs.getInt("id_solicitacao"));

                acordos.add(acordo);
            }

            rs.close();
            stmt.close();
            return acordos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
