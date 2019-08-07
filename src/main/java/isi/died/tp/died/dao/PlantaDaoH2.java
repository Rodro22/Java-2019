package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.Planta;

public class PlantaDaoH2 implements PlantaDao {

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS planta (idplanta INT NOT NULL PRIMARY KEY, nombre_planta VARCHAR(80), BOOLEAN esAcopio)";
	private static final String SQL_INSERT = "INSERT INTO planta(nombre_planta,esAcopio) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE planta SET nombre_planta =?, esAcopio=? WHERE idplanta = ?";
	private static final String SQL_DELETE = "DELETE FROM planta WHERE idplanta = ?";
	private static final String SQL_SELECT = "SELECT * FROM planta";
	
	public PlantaDaoH2() {
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Planta crear(Planta p) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setString(1, p.getNombre());
					pst.setBoolean(2, p.getEsAcopio());
					pst.executeUpdate();
					try(ResultSet rs = pst.getGeneratedKeys()){
						if(rs.next()) {
							p.setId(rs.getInt(1));
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return p;
	}


	public Planta actualizar(Planta p) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setString(1, p.getNombre());
				pst.setBoolean(2, p.getEsAcopio());
				pst.setInt(3, p.getId());
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return p;
	}

	@Override
	public void borrar(Integer idplanta) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idplanta);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Planta buscar(Integer idplanta) {
		Planta resultado = null;
		String sqlById = SQL_SELECT + " WHERE idplanta = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idplanta);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Planta();
						resultado.setId(idplanta);
						resultado.setNombre(rs.getString("nombre_planta"));
						//resultado.setEsAcopio(rs.getBoolean("esAcopio"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Planta> buscarTodos() {
		List<Planta> resultado = new ArrayList<Planta>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Planta aux = new Planta();
						aux.setId(rs.getInt("idplanta"));
						aux.setNombre(rs.getString("nombre_planta"));
						aux.setEsAcopio(rs.getBoolean("esAcopio"));
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
