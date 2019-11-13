package sige.comunicacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import sige.controle.EventoControle;
import sige.controle.InscricaoControle;
import sige.controle.ParticipanteControle;
import sige.modeo.Evento;
import sige.modeo.Participante;

@SessionScoped
@ManagedBean
public class MarcarPresencaMbean {

	private ParticipanteControle participanteControle = new ParticipanteControle();
	private EventoControle eventoControle = new EventoControle();
	private InscricaoControle inscricaoControle = new InscricaoControle();
	private List<Evento> eventos;
	private Evento eventoSelecionado = null;
	private List<Participante> participantesInscritos;
	private List<Participante> participantesSelecionados;


	@PostConstruct
	public void inicializar() {
		eventos = eventoControle.listarEventos();

	}
	
	
	public void marcarPresenca () {
		
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
