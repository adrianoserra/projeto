package sige.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
