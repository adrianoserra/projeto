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
	private Participante participanteNovo;
	private Participante participanteSelecionado;
	private Util util = new Util();
	
	@PostConstruct
	public void inicializar () {
		colecaoParticipante = new ArrayList<Participante>();
		colecaoParticipante = participanteControle.listarParticipante();
		participanteSelecionado = new Participante();
		setParticipanteNovo(new Participante());
		}
	
	public void excluirParticipante (Participante participante) {
		try {
			participanteControle.excluirParticipante(participante);
			colecaoParticipante.remove(participante);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void incluirParticipante () {
		setParticipanteNovo(new Participante());
		util.exibirDialogPF("DialogAdicionar");
	}
	
	public void alterarParticipante (Participante participante) {
		participanteSelecionado = participante;
		util.exibirDialogPF("DialogAlterar");
		
	}
	
	public void salvar () {
		try {
			participanteControle.salvarParticipante(participanteNovo);
			colecaoParticipante.add(participanteNovo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void alterar () {
		try {
			participanteControle.alterarParticipante(participanteSelecionado);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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

	public Participante getParticipanteNovo() {
		return participanteNovo;
	}

	public void setParticipanteNovo(Participante participanteNovo) {
		this.participanteNovo = participanteNovo;
	}

}
