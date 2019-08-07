package isi.died.tp.died.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import isi.died.tp.died.modelo.CargaStock;
import isi.died.tp.died.modelo.Stock;
import isi.died.tp.died.modelo.Insumo;

public class CargaStockDaoH2 implements CargaStockDao {
	
	private InsumoDao insumoDaoH2;
	private StockDao stockDaoH2;

	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS insumo_has_stock(idcargastock INT NOT NULL PRIMARY KEY, cantidadRequerida DOUBLE)";
	private static final String SQL_INSERT = "INSERT INTO insumo_has_stock(insumo_idinsumo, stock_idstock, cantidadRequerida, stockDisponible) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE insumo_has_stock SET insumo_idinsumo=?, stock_idstock=?, cantidadRequerida=?, stockDisponible=? WHERE idcargastock= ?";
	private static final String SQL_DELETE = "DELETE FROM insumo_has_stock WHERE idcargastock= ?";
	private static final String SQL_SELECT = "SELECT * FROM insumo_has_stock";
	
	public CargaStockDaoH2() {
		this.insumoDaoH2 = new InsumoDaoH2();
		this.stockDaoH2 = new StockDaoH2();
		try(Connection conn = DBConnection.get()){
			try(Statement st = conn.createStatement()){
				st.executeUpdate(SQL_CREATE);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public CargaStock crear(CargaStock c) {
			try(Connection conn = DBConnection.get()){
				try(PreparedStatement pst = conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS)){
					pst.setInt(1, c.getInsumo().getId());
					pst.setInt(2, c.getStock().getId());
					pst.setDouble(3, c.getCantidadRequerida());
					pst.setDouble(4, c.getStockDisponible());
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


	public CargaStock actualizar(CargaStock c) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_UPDATE )){
				pst.setInt(1, c.getInsumo().getId());
				pst.setInt(2, c.getStock().getId());
				pst.setDouble(3, c.getCantidadRequerida());
				pst.setDouble(4, c.getStockDisponible());
				pst.setInt(5, c.getId());
				pst.executeUpdate();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public void borrar(Integer idcargastock) {
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_DELETE)){
				pst.setInt(1, idcargastock);
				pst.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public CargaStock buscar(Integer idcargastock) {
		CargaStock resultado = null;
		String sqlById = SQL_SELECT + " WHERE idcargastock = ?";
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(sqlById)){
				pst.setInt(1, idcargastock);
				try(ResultSet rs = pst.executeQuery()){
					if(rs.next()) {
						resultado = new CargaStock();
						resultado.setId(idcargastock);
						resultado.setInsumo(this.insumoDaoH2.buscar(rs.getInt("insumo_idinsumo")));
						resultado.setStock(this.stockDaoH2.buscar(rs.getInt("stock_idstock")));
						resultado.setCantidadRequerida(rs.getDouble("cantidadRequerida"));
						resultado.setStockDisponible(rs.getDouble("stockDisponible"));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<CargaStock> buscarTodos() {
		List<CargaStock> resultado = new ArrayList<CargaStock>();
		try(Connection conn = DBConnection.get()){
			try(PreparedStatement pst = conn.prepareStatement(SQL_SELECT)){
				try(ResultSet rs = pst.executeQuery()){
					while(rs.next()) {
						CargaStock aux = new CargaStock();
						aux.setId(rs.getInt("idcargastock"));
						aux.setInsumo(this.insumoDaoH2.buscar(rs.getInt("insumo_idinsumo")));
						aux.setStock(this.stockDaoH2.buscar(rs.getInt("stock_idstock")));
						aux.setCantidadRequerida(rs.getDouble("cantidadRequerida"));
						aux.setStockDisponible(rs.getDouble("stockDisponible"));
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
