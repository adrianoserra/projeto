package sige.comunicacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sige.controle.ParticipanteControle;
import sige.modeo.Participante;
import sige.util.Util;

@ViewScoped
@ManagedBean
public class ManterParticipanteMbean {
	private ParticipanteControle participanteControle = new ParticipanteControle();
	private List<Participante> colecaoParticipante;
	private Participante participanteSelecionado;
	private Util util = new Util();
	
	@PostConstruct
	public void inicializar () {
		colecaoParticipante = new ArrayList<Participante>();
		setParticipanteSelecionado(new Participante());
		Participante participanteTeste = new Participante();
		participanteTeste.setMatricula("111111");
		colecaoParticipante.add(participanteTeste);
		}
	
	public void excluirParticipante (Participante participante) {
		colecaoParticipante.remove(participante);
	}
	
	public void incluirParticipante () {
		setParticipanteSelecionado(new Participante());
		util.exibirDialogPF("DialogAdicionar");
	}
	
	public void alterarParticipante (Participante participante) {
		setParticipanteSelecionado(new Participante());
		colecaoParticipante.remove(participante);
		util.exibirDialogPF("DialogAlterar");
		
	}
	
	public void salvar () {
		colecaoParticipante.add(participanteSelecionado);
	}
	
	public void alterar () {
		colecaoParticipante.add(participanteSelecionado);
	}
	
	public void alterarUsuario (Participante participante) {
		setParticipanteSelecionado(participante);
	}

	public List<Participante> getColecaoParticipante() {
		return colecaoParticipante;
	}

	public void setColecaoParticipante(List<Participante> colecaoParticipante) {
		this.colecaoParticipante = colecaoParticipante;
	}

	public Participante getParticipanteSelecionado() {
		return participanteSelecionado;
	}

	public void setParticipanteSelecionado(Participante participanteSelecionado) {
		this.participanteSelecionado = participanteSelecionado;
	}

}
