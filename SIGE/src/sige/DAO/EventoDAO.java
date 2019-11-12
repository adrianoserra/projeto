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

	private Connection connection;

	public void salvarEvento(Evento evento) throws SQLException, ClassNotFoundException {

		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {

			String sql = "INSERT INTO evento" + "(tema, data_evento, horario_inicio, horario_fim, carga_horaria, palestrante, descricao)"
					+ " values (?,?,?,?,?,?,?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, evento.getTema());
			stmt.setString(2, evento.getDataEvento());
			stmt.setString(3, evento.getHorarioInicio());
			stmt.setString(4, evento.getHorarioFim());
			stmt.setString(5, evento.getCargaHoraria());
			stmt.setString(6, evento.getPalestrante().getCPF());
			stmt.setString(7, evento.getDescricao());

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}

	}

	public List<Evento> listarEventos() {
		PreparedStatement stmt = null;
		List<Evento> colecaoEvento = new ArrayList<Evento>();
		try {
			this.connection = new JDBC().getConexao();
			ResultSet rs = null;

			Evento evento;
			String sql = "SELECT idEvento, tema, data_evento, horario_inicio, horario_fim, carga_horaria, "
					+ "descricao, p.nome, p.cpf FROM evento join participante p on evento.palestrante = p.cpf";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				evento = new Evento();
				Participante participante = new Participante();
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setTema(rs.getString("tema"));
				evento.setDataEvento(rs.getString("data_evento"));
				evento.setHorarioInicio(rs.getString("horario_inicio"));
				evento.setHorarioFim(rs.getString("horario_fim"));
				evento.setCargaHoraria(rs.getString("carga_horaria"));
				evento.setDescricao(rs.getString("descricao"));
				participante.setCPF(rs.getString("p.cpf"));
				participante.setNome(rs.getString("p.nome"));
				evento.setPalestrante(participante);
				
				colecaoEvento.add(evento);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return colecaoEvento;
	}
	public void alterarEvento (Evento evento) throws ClassNotFoundException, SQLException {
	    this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
		String	sql	=	"update evento set"
				+ " tema = ?, data_evento = ?, horario_inicio = ?, horario_fim = ? "
				+ ", carga_horaria = ?, descricao = ?, palestrante = ? WHERE idEvento = ?";
				
				
		stmt = connection.prepareStatement(sql);
	    stmt.setString(1, evento.getTema());
	    stmt.setString(2, evento.getDataEvento());
	    stmt.setString(3, evento.getHorarioInicio());
	    stmt.setString(4, evento.getHorarioFim());
	    stmt.setString(5, evento.getCargaHoraria());
	    stmt.setString(6, evento.getDescricao());
	    stmt.setString(7, evento.getPalestrante().getCPF());
	    stmt.setInt(8, evento.getIdEvento());
	    
		stmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();
		}
		
	
   }
	public void excluirEvento (Evento evento) throws ClassNotFoundException, SQLException {
		   this.connection = new JDBC().getConexao();
			PreparedStatement stmt = null;
			try {
			String	sql	=	"DELETE FROM evento WHERE idEvento = ?";
					
					
			stmt = connection.prepareStatement(sql);
		    stmt.setInt(1, evento.getIdEvento());
			stmt.execute();
			stmt.close();
			connection.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
				stmt.close();
				connection.close();
			}
	   }
}
