package sige.controle;

import java.sql.SQLException;
import java.util.List;

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
	public List<Participante> listarParticipantesPorEvento (Evento evento){
		return incricaoDAO.participantesInscritosPorEvento(evento);
	}
	
	public void marcarPresenca(List<Participante> participantes, Evento evento) throws SQLException {
		incricaoDAO.marcarPresenca(participantes, evento);
	}
	
	public List<Evento> listarEventosInscritosPariticipante (Participante participante) {
		return incricaoDAO.listarEventosPorParticipante(participante);
	}

}
