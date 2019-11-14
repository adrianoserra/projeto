package sige.comunicacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sige.controle.EventoControle;
import sige.controle.InscricaoControle;
import sige.modeo.Evento;
import sige.modeo.Participante;
import sige.util.Util;

@SessionScoped
@ManagedBean
public class MarcarPresencaMbean {

	private EventoControle eventoControle = new EventoControle();
	private InscricaoControle inscricaoControle = new InscricaoControle();
	private List<Evento> eventos;
	private Evento eventoSelecionado = null;
	private List<Participante> participantesInscritos;
	private List<Participante> participantesSelecionados;
	private Util util = new Util();


	@PostConstruct
	public void inicializar() {
		eventos = eventoControle.listarEventos();

	}
	
	
	public void marcarPresenca () {
		try {
			inscricaoControle.marcarPresenca(participantesSelecionados, eventoSelecionado);
			participantesInscritos.removeAll(participantesSelecionados);
			participantesSelecionados.clear();
			util.getMenssagemInfor("Todos os paticipantes selecionados podem emitir um certificado!");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listarParticipantesInscritos () {
		if (eventoSelecionado != null) {
		participantesInscritos = inscricaoControle.listarParticipantesPorEvento(eventoSelecionado); 
		} else {
			participantesInscritos = new ArrayList<Participante>();
		}
	}

	public List<Participante> getParticipantesInscritos() {
		return participantesInscritos;
	}

	public void setParticipantesInscritos(List<Participante> participantesInscritos) {
		this.participantesInscritos = participantesInscritos;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public List<Participante> getParticipantesSelecionados() {
		return participantesSelecionados;
	}

	public void setParticipantesSelecionados(List<Participante> participantesSelecionados) {
		this.participantesSelecionados = participantesSelecionados;
	}

}
