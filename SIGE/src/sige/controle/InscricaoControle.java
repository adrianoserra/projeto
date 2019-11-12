package sige.controle;

import java.sql.SQLException;

import sige.DAO.InscricaoDAO;
import sige.modeo.Evento;
import sige.modeo.Participante;

public class InscricaoControle {
	private InscricaoDAO incricaoDAO = new InscricaoDAO();
	
	public void inscreverParticipante(Evento evento, Participante participante) throws SQLException, ClassNotFoundException  {
		incricaoDAO.inscreverParticipante(evento, participante);
	}
	
	public Boolean verificaSeParticipanteInscrito (Evento evento, Participante participante){
		return incricaoDAO.verificaSeParticipanteInscrito(evento, participante);
	}

}
