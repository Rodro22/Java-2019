package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.Camino;
import isi.died.tp.died.modelo.Planta;
import isi.died.tp.died.modelo.Camion;

public class CaminoDaoH2 implements CaminoDao {
	
	private PlantaDao plantaDaoH2;
	private CamionDao camionDaoH2;

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS camino (idcamino INT NOT NULL PRIMARY KEY, distancia DOUBLE)";
	private static final String SQL_INSERT = "INSERT INTO camino(idplanta_init,idplanta_end,camion_idcamion,distancia,duracion,peso_soportado) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE camino SET idplanta_init=?, idplanta_end=?, camion_idcamion=?, distancia=?, duracion=?, peso_soportado=? WHERE idcamino = ?";
	private static final String SQL_DELETE = "DELETE FROM camino WHERE idcamino = ?";
	private static final String SQL_SELECT = "SELECT * FROM camino";
	
	public CaminoDaoH2() {
		this.plantaDaoH2 = new PlantaDaoH2();
		this.camionDaoH2 = new CamionDaoH2();
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Camino crear(Camino c) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setInt(1, c.getPlantaInit().getId());
					pst.setInt(2, c.getPlantaEnd().getId());
					pst.setInt(3, c.getCamion().getId());
					pst.setDouble(4, c.getDistancia());
					pst.setDouble(5, c.getDuracion());
					pst.setDouble(6, c.getPesoSoportado());
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


	public Camino actualizar(Camino c) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setInt(1, c.getPlantaInit().getId());
				pst.setInt(2, c.getPlantaEnd().getId());
				pst.setInt(3, c.getCamion().getId());
				pst.setDouble(4, c.getDistancia());
				pst.setDouble(5, c.getDuracion());
				pst.setDouble(6, c.getPesoSoportado());
				pst.setInt(7, c.getId());
				pst.executeUpdate();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public void borrar(Integer idcamino) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idcamino);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Camino buscar(Integer idcamino) {
		Camino resultado = null;
		String sqlById = SQL_SELECT + " WHERE idcamino = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idcamino);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Camino();
						resultado.setId(idcamino);
						resultado.setPlantaInit(this.plantaDaoH2.buscar(rs.getInt("idplanta_init")));
						resultado.setPlantaEnd(this.plantaDaoH2.buscar(rs.getInt("idplanta_end")));
						resultado.setCamion(this.camionDaoH2.buscar(rs.getInt("camion_idcamion")));
						resultado.setDistancia(rs.getDouble("distancia"));
						resultado.setDuracion(rs.getDouble("duracion"));
						resultado.setPesoSoportado(rs.getDouble("peso_soportado"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Camino> buscarTodos() {
		List<Camino> resultado = new ArrayList<Camino>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Camino aux = new Camino();
						aux.setId(rs.getInt("idcamino"));
						aux.setPlantaInit(this.plantaDaoH2.buscar(rs.getInt("idplanta_init")));
						aux.setPlantaEnd(this.plantaDaoH2.buscar(rs.getInt("idplanta_end")) );
						aux.setCamion(this.camionDaoH2.buscar(rs.getInt("camion_idcamion")));
						aux.setDistancia(rs.getDouble("distancia"));
						aux.setDuracion(rs.getDouble("duracion"));
						aux.setPesoSoportado(rs.getDouble("peso_soportado"));
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
