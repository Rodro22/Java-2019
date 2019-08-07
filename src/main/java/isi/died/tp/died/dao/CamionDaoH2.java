package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.Camion;

public class CamionDaoH2 implements CamionDao {

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS camion (idcamion INT NOT NULL PRIMARY KEY, marca VARCHAR(80))";
	private static final String SQL_INSERT = "INSERT INTO camion(marca,modelo,dominio,anio,costoXKm,transportaLiquido,capacidad) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE camion SET marca =?, modelo=?, dominio=?, anio=?, costoXKm=?, transportaLiquido=?, capacidad=? WHERE idcamion = ?";
	private static final String SQL_DELETE = "DELETE FROM camion WHERE idcamion = ?";
	private static final String SQL_SELECT = "SELECT * FROM camion";
	
	public CamionDaoH2() {
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Camion crear(Camion c) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setString(1, c.getMarca());
					pst.setString(2, c.getModelo());
					pst.setString(3, c.getDominio());
					pst.setInt(4, c.getAnio());
					pst.setDouble(5, c.getCostoXKm());
					pst.setBoolean(6, c.getTransportaLiquido());
					pst.setDouble(7, c.getCapacidad());
					pst.executeUpdate();
					try(ResultSet rs = pst.getGeneratedKeys()){
						if(rs.next()) {
							c.setId(rs.getInt(1));
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return c;
	}


	public Camion actualizar(Camion c) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setString(1, c.getMarca());
				pst.setString(2, c.getModelo());
				pst.setString(3, c.getDominio());
				pst.setInt(4, c.getAnio());
				pst.setDouble(5, c.getCostoXKm());
				pst.setBoolean(6, c.getTransportaLiquido());
				pst.setDouble(7, c.getCapacidad());
				pst.setInt(8, c.getId());
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public void borrar(Integer idcamion) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idcamion);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Camion buscar(Integer idcamion) {
		Camion resultado = null;
		String sqlById = SQL_SELECT + " WHERE idcamion = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idcamion);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Camion();
						resultado.setId(idcamion);
						resultado.setMarca(rs.getString("marca"));
						resultado.setModelo(rs.getString("modelo"));
						resultado.setDominio(rs.getString("dominio"));
						resultado.setAnio(rs.getInt("anio"));
						resultado.setCostoXKm(rs.getDouble("costoXKm"));
						resultado.setTransportaLiquido(rs.getBoolean("transportaLiquido"));
						resultado.setCapacidad(rs.getDouble("capacidad"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Camion> buscarTodos() {
		List<Camion> resultado = new ArrayList<Camion>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Camion aux = new Camion();
						aux.setId(rs.getInt("idcamion"));
						aux.setMarca(rs.getString("marca"));
						aux.setModelo(rs.getString("modelo"));
						aux.setDominio(rs.getString("dominio"));
						aux.setAnio(rs.getInt("anio"));
						aux.setCostoXKm(rs.getDouble("costoXKm"));
						aux.setTransportaLiquido(rs.getBoolean("transportaLiquido"));
						aux.setCapacidad(rs.getDouble("capacidad"));
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
