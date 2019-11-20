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
	
	public void salvarParticipante (Participante participante) throws SQLException {
		
		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"INSERT INTO participante"
				+ "(nome, setor, cpf, email, telefone, senha, tipoUsuario)"
				+ " values (?,?,?,?,?,?,?)";
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getNome());
	    stmt.setString(2, participante.getSetor());
	    stmt.setString(3, participante.getCPF());
	    stmt.setString(4, participante.getEmail());
	    stmt.setString(5, participante.getTelefone());
	    stmt.setString(6, participante.getSenha());
	    participante.setTipoUsuario(1);
	    stmt.setInt   (7, participante.getTipoUsuario());
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		
		
	}

   public Participante autenticarUsuario (String CPF, String senha) throws SQLException {
	   this.connection = new JDBC().getConexao();
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
	   Participante particianteAtual = null; 
	   
	   try {
		   String	sql	=	"SELECT nome, setor, cpf, email, telefone, senha, tipoUsuario "
		   		+ "FROM participante WHERE cpf = ? AND senha = ?";
		   stmt = connection.prepareStatement(sql);
		   stmt.setString(1, CPF);
		   stmt.setString(2, senha);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   particianteAtual = new Participante();
			   particianteAtual.setNome(rs.getString("nome"));
			   particianteAtual.setSetor(rs.getString("setor"));
			   particianteAtual.setCPF(rs.getString("cpf"));
			   particianteAtual.setEmail(rs.getString("email"));
			   particianteAtual.setTelefone(rs.getString("telefone"));
			   particianteAtual.setTipoUsuario(rs.getInt("tipoUsuario"));
			   particianteAtual.setSenha(rs.getString("senha"));
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
			   particiante.setNome(rs.getString("nome"));
			   particiante.setSetor(rs.getString("setor"));
			   particiante.setCPF(rs.getString("cpf"));
			   particiante.setEmail(rs.getString("email"));
			   particiante.setTelefone(rs.getString("telefone"));
			   particiante.setTipoUsuario(rs.getInt("tipoUsuario"));
			   colecaoParticipante.add(particiante);
		   }
		   stmt.close();
		   connection.close();
	   } catch (SQLException e) {
			e.printStackTrace();
		}
		return colecaoParticipante;
   }
   public boolean verificarSeCPFExiste (String cpf) {
	   boolean existe = false;
	   try {
	   this.connection = new JDBC().getConexao();
	   PreparedStatement stmt = null;
	   ResultSet rs = null;
		   String	sql	=	"SELECT cpf "
		   		+ "FROM participante WHERE cpf = ?";
		   stmt = connection.prepareStatement(sql);
		   stmt.setString(1,cpf);
		   rs = stmt.executeQuery();
		   
		   while(rs.next()){
			   existe = true;
			}
	   } catch (SQLException e) {
			e.printStackTrace();
	}
	   
		return existe;
   }
   
   public void alterarParticipante (Participante participante) throws ClassNotFoundException, SQLException {
	    this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"update participante set"
				+ " nome = ? , setor = ? , email = ? , telefone = ? WHERE cpf = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getNome());
	    stmt.setString(2, participante.getSetor());
	    stmt.setString(3, participante.getEmail());
	    stmt.setString(4, participante.getTelefone());
	    stmt.setString(5, participante.getCPF());
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
		String	sql	=	"DELETE FROM participante WHERE cpf = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getCPF());
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
   }
   
   public void alterarSenha (Participante participante) throws ClassNotFoundException, SQLException {
	    this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"update participante set"
				+ " senha = ? WHERE cpf = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, participante.getSenha());
	    stmt.setString(2, participante.getCPF());
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
