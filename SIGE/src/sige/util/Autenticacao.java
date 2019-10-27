package sige.util;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import sige.comunicacao.CadastrarParticipanteMbean;
import sige.modeo.Participante;

public class Autenticacao implements PhaseListener {
	private static final long serialVersionUID = 2671096626940408913L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		String paginaAtual = Faces.getViewId();
		Boolean paginaPublica = false;
		if (paginaAtual.contains(Constantes.cadastrarParticipante)) {
			paginaPublica = true;
		} else if (paginaAtual.contains(Constantes.contato)) {
			paginaPublica = true;
		} else if (paginaAtual.contains(Constantes.index)) {
			paginaPublica = true;
		} else if (paginaAtual.contains(Constantes.sobre)) {
			paginaPublica = true;
		}
		
		try {
		
		if (!paginaPublica) {
			CadastrarParticipanteMbean cadastrarParticipanteMbean = Faces.getSessionAttribute("cadastrarParticipanteMbean");
		   if (cadastrarParticipanteMbean == null) {
			       FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarParticipante.xhtml");
			       return;
			
		   } else {
			   Participante participante = cadastrarParticipanteMbean.getParticipanteAutenticado();
			   if (participante == null) {
				   FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarParticipante.xhtml");
				   return;
			   }
		   }
		  }
	     } catch (IOException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
