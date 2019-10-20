package sige.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sige.modeo.Evento;
import sige.modeo.Participante;
import sige.util.conexao.JDBC;

public class EventoDAO {

	private	Connection	connection;
	
	
public void salvarEvento (Evento evento) throws SQLException, ClassNotFoundException {
		
		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
			
			
		String	sql	=	"INSERT INTO evento"
				+ "(idEvento, tema, data_evento, horario_inicio, horario_fim, carga_horaria, matricula)"
				+ " values (?,?,?,?,?,?,?)";
		stmt = connection.prepareStatement(sql);
		
	    stmt.setInt(1, evento.getIdEvento());
	    stmt.setString(2, evento.getTema());
	    stmt.setString(3, evento.getData_evento());
	    stmt.setString(4, evento.getHorario_inicio());
	    stmt.setString(5, evento.getHorario_fim());
	    stmt.setString(6, evento.getCarga_horaria());
	    stmt.setInt(7, evento.getMatricula());
	    
	  
	 
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		
		
	}

public List<Evento> listarEventos () {
	   PreparedStatement stmt = null;
	   List<Evento> colecaoEvento = new ArrayList<Evento>();
	   try {
		   this.connection = new JDBC().getConexao();
		   ResultSet rs = null;
		   
		   Evento evento; 
		   String	sql	=	"SELECT * FROM evento";
		   stmt = connection.prepareStatement(sql);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   evento = new Evento();
			   evento.setIdEvento(rs.getInt("idEvento"));
			   evento.setTema(rs.getString("tema"));
			   evento.setData_evento(rs.getString("data_evento"));
			   evento.setHorario_inicio(rs.getString("horario_inicio"));
			   evento.setHorario_fim(rs.getString("horario_fim"));
			   evento.setCarga_horaria(rs.getString("carga_horaria"));
			   evento.setMatricula(rs.getInt("matricula"));
			   colecaoEvento.add(evento);
		   }
		   stmt.close();
		   connection.close();
	   } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return colecaoEvento;
}
}
