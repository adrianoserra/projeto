package sige.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sige.modeo.Inscricao;
import sige.modeo.Participante;
import sige.util.conexao.JDBC;

public class PresencaDAO {

	private Connection connection;

	public void registrarPresenca(Participante participante, Inscricao inscricao) throws SQLException, ClassNotFoundException {

		this.connection = new JDBC().getConexao();
		PreparedStatement stmt = null;
		try {

			String sql = "INSERT INTO historico_participante" + "(presenca, idInscricao, cpf)"
					+ " values (?,?,?)";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, 1);
			stmt.setString(2, participante.getCPF());
			stmt.setInt(3, inscricao.getIdInscricao());
			

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
