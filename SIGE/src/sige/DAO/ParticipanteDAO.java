package sige.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sige.modeo.Participante;
import sige.util.conexao.JDBC;

public class ParticipanteDAO {
	private	Connection	connection;
	
	public void salvarParticipante (Participante participante) throws SQLException, ClassNotFoundException {
		
		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"INSERT INTO participante"
				+ "(matricula, nome, setor, CPF, email, telefone, senha, tipoUsuario)"
				+ " values (?,?,?,?,?,?,?,?)";
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getMatricula());
	    stmt.setString(2, participante.getNome());
	    stmt.setString(3, participante.getSetor());
	    stmt.setString(4, participante.getCPF());
	    stmt.setString(5, participante.getEmail());
	    stmt.setString(6, participante.getTelefone());
	    stmt.setString(7, participante.getSenha());
	    if (participante.getMatricula().subSequence(0, 1).equals("2")) {
	    	participante.setTipoUsuario(2);
	    } else {
	    	participante.setTipoUsuario(1);  	
	    }
	    stmt.setInt   (8, participante.getTipoUsuario());
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		
		
	}

   public Participante autenticarUsuario (String matricula, String senha) throws SQLException, ClassNotFoundException {
	   this.connection = new JDBC().getConexao();
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   Participante particianteAtual = null; 
	   
	   try {
		   String	sql	=	"SELECT matricula, nome, setor, CPF, email, telefone, senha, tipoUsuario "
		   		+ "FROM participante WHERE matricula = ? AND senha = ?";
		   stmt = connection.prepareStatement(sql);
		   stmt.setString(1, matricula);
		   stmt.setString(2, senha);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   particianteAtual = new Participante();
			   particianteAtual.setMatricula(rs.getString("matricula"));
			   particianteAtual.setNome(rs.getString("nome"));
			   particianteAtual.setSetor(rs.getString("setor"));
			   particianteAtual.setCPF(rs.getString("CPF"));
			   particianteAtual.setEmail(rs.getString("email"));
			   particianteAtual.setTelefone(rs.getString("telefone"));
			   particianteAtual.setTipoUsuario(rs.getInt("tipoUsuario"));
		   }
	   } catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		return particianteAtual;
   }
   
   public List<Participante> listarParticipantes () {
	   PreparedStatement stmt = null;
	   List<Participante> colecaoParticipante = new ArrayList<Participante>();
	   try {
		   this.connection = new JDBC().getConexao();
		   ResultSet rs = null;
		   
		   Participante particiante; 
		   String	sql	=	"SELECT * FROM participante";
		   stmt = connection.prepareStatement(sql);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   particiante = new Participante();
			   particiante.setMatricula(rs.getString("matricula"));
			   particiante.setNome(rs.getString("nome"));
			   particiante.setSetor(rs.getString("setor"));
			   particiante.setCPF(rs.getString("CPF"));
			   particiante.setEmail(rs.getString("email"));
			   particiante.setTelefone(rs.getString("telefone"));
			   particiante.setTipoUsuario(rs.getInt("tipoUsuario"));
			   colecaoParticipante.add(particiante);
		   }
		   stmt.close();
		   connection.close();
	   } catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return colecaoParticipante;
   }
   public boolean verificarSeMatriculaExiste (String matricula) throws SQLException, ClassNotFoundException {
	   this.connection = new JDBC().getConexao();
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   boolean existe = false; 
	   
	   try {
		   String	sql	=	"SELECT matricula "
		   		+ "FROM participante WHERE matricula = ?";
		   stmt = connection.prepareStatement(sql);
		   stmt.setString(1, matricula);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   existe = true;
			}
	   } catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		return existe;
   }
   
   public void alterarParticipante (Participante participante) throws ClassNotFoundException, SQLException {
	    this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"update participante set"
				+ " nome = ? , setor = ? , CPF = ? , email = ? , telefone = ? , senha = ? WHERE matricula = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getNome());
	    stmt.setString(2, participante.getSetor());
	    stmt.setString(3, participante.getCPF());
	    stmt.setString(4, participante.getEmail());
	    stmt.setString(5, participante.getTelefone());
	    stmt.setString(6, participante.getSenha());
	    stmt.setString(7, participante.getMatricula());
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		
	
   }
   public void excluirParticipante (Participante participante) throws ClassNotFoundException, SQLException {
	   this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"DELETE FROM participante WHERE matricula = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getMatricula());
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
   }
}   
