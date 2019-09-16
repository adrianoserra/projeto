package sige.controle;

import java.sql.SQLException;

import sige.DAO.ParticipanteDAO;
import sige.modeo.Participante;

public class ParticipanteControle {
	private ParticipanteDAO participanteDAO = new ParticipanteDAO();

	public void salvarParticipante (Participante participante) throws ClassNotFoundException, SQLException {
		participanteDAO.salvarParticipante(participante);
	}
	public Participante autenticarUsuario (String matricula, String senha) throws ClassNotFoundException, SQLException {
		return participanteDAO.autenticarUsuario(matricula, senha);
	}
}
