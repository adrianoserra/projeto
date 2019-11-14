package sige.comunicacao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;

import sige.controle.EventoControle;
import sige.controle.InscricaoControle;
import sige.modeo.Evento;
import sige.modeo.Participante;
import sige.util.conexao.JDBC;

@ViewScoped
@ManagedBean
public class InscricaoEventosMbean {
	private EventoControle eventoControle = new EventoControle(); 
	private InscricaoControle inscricaoControle = new InscricaoControle();
	private List<Evento> colecaoEvento;
	private Participante participante;

	@PostConstruct
	public void inicializar() {
		colecaoEvento = new ArrayList<Evento>();
		colecaoEvento = eventoControle.listarEventos();
		CadastrarParticipanteMbean cadastrarParticipanteMbean = Faces.getSessionAttribute("cadastrarParticipanteMbean");
		participante = cadastrarParticipanteMbean.getParticipanteAutenticado();
		for (Evento evento: colecaoEvento) {
			evento.setParticipanteInscrito(inscricaoControle.verificaSeParticipanteInscrito(evento, participante));
		}
	}
	
	public void inscreverParticipanteEvento (Evento evento) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			
			inscricaoControle.inscreverParticipante(evento, participante);
			context.addMessage(null, new FacesMessage("Inscrição realizada com sucesso!", " ") );
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Evento> getColecaoEvento() {
		return colecaoEvento;
	}

	public void setColecaoEvento(List<Evento> colecaoEvento) {
		this.colecaoEvento = colecaoEvento;
	}

}
