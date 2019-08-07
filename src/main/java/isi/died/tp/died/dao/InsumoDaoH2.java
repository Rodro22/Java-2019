package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.Insumo;

public class InsumoDaoH2 implements InsumoDao {

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS insumo (idinsumo INT NOT NULL PRIMARY KEY, nombreInsumo VARCHAR(80))";
	private static final String SQL_INSERT = "INSERT INTO insumo(nombreInsumo,descripcion,unidadDeMedida,peso,costo,esRefrigerado,esLiquido) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE insumo SET nombreInsumo =?, descripcion=?, unidadDeMedida=?, peso=?, costo=?, esRefrigerado=?, esLiquido=? WHERE idinsumo = ?";
	private static final String SQL_DELETE = "DELETE FROM insumo WHERE idinsumo = ?";
	private static final String SQL_SELECT = "SELECT * FROM insumo";
	
	public InsumoDaoH2() {
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Insumo crear(Insumo i) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setString(1, i.getNombre());
					pst.setString(2, i.getDescripcion());
					pst.setString(3, i.getUnidadDeMedida());
					pst.setDouble(4, i.getPeso());
					pst.setDouble(5, i.getCosto());
					pst.setBoolean(6, i.getEsRefrigerado());
					pst.setBoolean(7, i.getEsLiquido());
					pst.executeUpdate();
					try(ResultSet rs = pst.getGeneratedKeys()){
						if(rs.next()) {
							i.setId(rs.getInt(1));
						}
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return i;
	}


	public Insumo actualizar(Insumo i) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setString(1, i.getNombre());
				pst.setString(2, i.getDescripcion());
				pst.setString(3, i.getUnidadDeMedida());
				pst.setDouble(4, i.getPeso());
				pst.setDouble(5, i.getCosto());
				pst.setBoolean(6, i.getEsRefrigerado());
				pst.setBoolean(7, i.getEsLiquido());
				pst.setInt(8, i.getId());
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public void borrar(Integer idinsumo) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idinsumo);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Insumo buscar(Integer idinsumo) {
		Insumo resultado = null;
		String sqlById = SQL_SELECT + " WHERE idinsumo = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idinsumo);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new Insumo();
						resultado.setId(idinsumo);
						resultado.setNombre(rs.getString("nombreInsumo"));
						resultado.setDescripcion(rs.getString("descripcion"));
						resultado.setUnidadDeMedida(rs.getString("unidadDeMedida"));
						resultado.setPeso(rs.getDouble("peso"));
						resultado.setCosto(rs.getDouble("costo"));
						resultado.setEsRefrigerado(rs.getBoolean("esRefrigerado"));
						resultado.setEsLiquido(rs.getBoolean("esLiquido"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Insumo> buscarTodos() {
		List<Insumo> resultado = new ArrayList<Insumo>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						Insumo aux = new Insumo();
						aux.setId(rs.getInt("idinsumo"));
						aux.setNombre(rs.getString("nombreInsumo"));
						aux.setDescripcion(rs.getString("descripcion"));
						aux.setUnidadDeMedida(rs.getString("unidadDeMedida"));
						aux.setPeso(rs.getDouble("peso"));
						aux.setCosto(rs.getDouble("costo"));
						aux.setEsRefrigerado(rs.getBoolean("esRefrigerado"));
						aux.setEsLiquido(rs.getBoolean("esLiquido"));
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
