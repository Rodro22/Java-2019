package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.Stock;
import isi.died.tp.died.modelo.Planta;

public class StockDaoH2 implements StockDao {
	
	private PlantaDao plantaDaoH2;

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS stock (idcamino INT NOT NULL PRIMARY KEY, planta_idplanta INT)";
	private static final String SQL_INSERT = "INSERT INTO stock(planta_idplanta) VALUES (?)";
	private static final String SQL_UPDATE = "UPDATE stock SET planta_idplanta=? WHERE idstock = ?";
	private static final String SQL_DELETE = "DELETE FROM stock WHERE idstock = ?";
	private static final String SQL_SELECT = "SELECT * FROM stock";
	
	public StockDaoH2() {
		this.plantaDaoH2 = new PlantaDaoH2();
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Stock crear(Stock s) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setInt(1, s.getPlanta().getId());
					pst.executeUpdate();
					try(ResultSet rs = pst.getGeneratedKeys()){
						if(rs.next()) {
							s.setId(rs.getInt(1));
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return s;
	}


	public Stock actualizar(Stock s) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setInt(1, s.getPlanta().getId());
				pst.setInt(2, s.getId());
				pst.executeUpdate();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return s;
	}

	@Override
	public void borrar(Integer idstock) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idstock);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Stock buscar(Integer idstock) {
		Stock resultado = null;
		String sqlById = SQL_SELECT + " WHERE idstock = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idstock);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Stock();
						resultado.setId(idstock);
						resultado.setPlanta(this.plantaDaoH2.buscar(rs.getInt("planta_idplanta")));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Stock> buscarTodos() {
		List<Stock> resultado = new ArrayList<Stock>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Stock aux = new Stock();
						aux.setId(rs.getInt("idstock"));
						aux.setPlanta(this.plantaDaoH2.buscar(rs.getInt("planta_idplanta")));
						resultado.add(aux);
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;	
	}
	
}
