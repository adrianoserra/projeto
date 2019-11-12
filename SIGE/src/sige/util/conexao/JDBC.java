package sige.util.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public Connection getConexao () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/sige?useTimezone=true&serverTimezone=UTC","root","");
		} catch (SQLException | ClassNotFoundException e) {
			throw new	RuntimeException(e);
		}
	}
}	
