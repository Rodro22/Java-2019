package isi.died.tp.died.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdominguez
 */
public class DBConnection {
    
	/**
	 * En caso de habilitar el almacenamiento en archivos
	 * http://www.h2database.com/html/features.html#database_url 
	 */
	// private static final String url ="jdbc:h2:~/ejemplo;AUTO_SERVER=TRUE;FILE_LOCK=SOCKET";
	
	/**
	 * Necesidad del uso del parametro DB_CLOSE_DELAY
	 * http://www.h2database.com/html/features.html#in_memory_databases
	 */
    private static final String url ="jdbc:mysql://localhost:3306/tp_died";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="died";
    
    
    private DBConnection(){
        // no se pueden crear instancias de esta clase
    }
    
   public static Connection get(){
        Connection conn=null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            if(conn != null) {
            	System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    } 
    
       

}