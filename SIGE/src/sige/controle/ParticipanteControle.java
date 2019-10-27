package sige.controle;

import java.sql.SQLException;
import java.util.List;

import sige.DAO.ParticipanteDAO;
import sige.modeo.Participante;

public class ParticipanteControle {
	private ParticipanteDAO participanteDAO = new ParticipanteDAO();

	public void salvarParticipante (Participante participante) throws ClassNotFoundException, SQLException {
		participanteDAO.salvarParticipante(participante);
	}
	public Participante autenticarUsuario (String CPF, String senha) throws ClassNotFoundException, SQLException {
		return participanteDAO.autenticarUsuario(CPF, senha);
	}
	public boolean verificarSeCPFExiste (String cpf) {
		return participanteDAO.verificarSeCPFExiste(cpf);
		
	}
	public void alterarParticipante (Participante participante) throws ClassNotFoundException, SQLException {
		participanteDAO.alterarParticipante(participante);
	}
	public void excluirParticipante (Participante participante) throws ClassNotFoundException, SQLException {
		participanteDAO.excluirParticipante(participante);
	}
	public List<Participante> listarParticipante (){
		return participanteDAO.listarParticipantes();
	}
}
