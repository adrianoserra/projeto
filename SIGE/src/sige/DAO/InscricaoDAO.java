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

public class InscricaoDAO {


		private Connection connection;

		public void inscreverParticipante(Evento evento, Participante participante) throws SQLException, ClassNotFoundException {

			this.connection = new JDBC().getConexao();
			PreparedStatement stmt = null;
			try {

				String sql = "INSERT INTO inscricao " + "(cpf,idEvento)"
						+ " values (?,?)";
				stmt = connection.prepareStatement(sql);

				stmt.setString(1, participante.getCPF());
				stmt.setInt(2, evento.getIdEvento());
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
				connection.rollback();
			} finally {
				stmt.close();
				connection.close();
			}

		}
		
		public Boolean verificaSeParticipanteInscrito (Evento evento, Participante participante) {
			this.connection = new JDBC().getConexao();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Boolean participanteInscrito = false;
			try {

				String sql = "Select * FROM inscricao WHERE cpf = ? AND idEvento = ?";
				stmt = connection.prepareStatement(sql);

				stmt.setString(1, participante.getCPF());
				stmt.setInt(2, evento.getIdEvento());
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					participanteInscrito = true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return participanteInscrito;
		}
		
		public List<Participante> participantesInscritosPorEvento (Evento evento) {
			List<Participante> participantes = new ArrayList<Participante>();
			this.connection = new JDBC().getConexao();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {

				String sql = "SELECT p.cpf, p.nome from inscricao "
						+ "join participante p on inscricao.cpf = p.cpf where idEvento = ? AND presenca = 0 ORDER BY p.nome ASC";
				stmt = connection.prepareStatement(sql);

				
				stmt.setInt(1, evento.getIdEvento());
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					Participante participante = new Participante();
					participante.setCPF(rs.getString("p.cpf"));
					participante.setNome(rs.getString("p.nome"));
					participantes.add(participante);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return participantes;
			
		}
		
	public void marcarPresenca(List<Participante> participantes, Evento evento) throws SQLException {
		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {
			for (Participante participante : participantes) {
			String sql = "update inscricao set" + " presenca = ? WHERE cpf = ? AND idEvento = ?";

			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, 1);
			stmt.setString(2, participante.getCPF());
			stmt.setInt(3, evento.getIdEvento());

			stmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			stmt.close();
			connection.close();

		}
	}
		
		

}
