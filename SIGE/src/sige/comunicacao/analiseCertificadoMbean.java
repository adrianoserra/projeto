package sige.comunicacao;

import java.io.IOException;
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
public class analiseCertificadoMbean {
	private EventoControle eventoControle = new EventoControle(); 
	private InscricaoControle inscricaoControle = new InscricaoControle();
	private List<Evento> colecaoEvento;
	private Participante participante;

	@PostConstruct
	public void inicializar() {
		colecaoEvento = new ArrayList<Evento>();
		
		CadastrarParticipanteMbean cadastrarParticipanteMbean = Faces.getSessionAttribute("cadastrarParticipanteMbean");
		participante = cadastrarParticipanteMbean.getParticipanteAutenticado();
		colecaoEvento = inscricaoControle.listarEventosInscritosPariticipante(participante);
	}
	
	public void escolherEvento() {
       try {
    	   if (participante.getEventoAtual() != null) {
    		   FacesContext.getCurrentInstance().getExternalContext().redirect("certificado.xhtml");
    	   }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public List<Evento> getColecaoEvento() {
		return colecaoEvento;
	}

	public void setColecaoEvento(List<Evento> colecaoEvento) {
		this.colecaoEvento = colecaoEvento;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	
	

}
