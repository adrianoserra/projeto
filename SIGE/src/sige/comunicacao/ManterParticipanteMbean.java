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
	private Boolean desabilitarBotaoIncluir;
	private Boolean erroMatricula = false;

	@PostConstruct
	public void inicializar() {
		setDesabilitarBotaoIncluir(true);
		colecaoParticipante = new ArrayList<Participante>();
		colecaoParticipante = participanteControle.listarParticipante();
		participanteSelecionado = new Participante();
		setParticipanteNovo(new Participante());
	}

	public void excluirParticipante(Participante participante) {
		try {
			participanteControle.excluirParticipante(participante);
			colecaoParticipante.remove(participante);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void incluirParticipante() {
		setParticipanteNovo(new Participante());
		erroMatricula = false;
		util.exibirDialogPF("DialogAdicionar");
	}

	public void alterarParticipante(Participante participante) {
		try {
			participanteControle.alterarParticipante(participante);
			util.getMenssagemInfor("participante alterado com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {
			participanteControle.salvarParticipante(participanteNovo);
			colecaoParticipante.add(participanteNovo);
			util.getMenssagemInfor("Participante incluido com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar() {
		try {
			participanteControle.alterarParticipante(participanteSelecionado);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void validarInclusao() {
		if (participanteNovo.getMatricula() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getMatricula().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			} else {
				erroMatricula = participanteControle.verificarSeMatriculaExiste(participanteNovo.getMatricula());
				if (erroMatricula) {
					desabilitarBotaoIncluir = true;
					return;
				}
			}
		}
		if (participanteNovo.getSenha() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getSenha().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		if (participanteNovo.getNome() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getNome().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		if (participanteNovo.getSetor() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getSetor().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		if (participanteNovo.getCPF() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getCPF().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		if (participanteNovo.getTelefone() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getTelefone().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		if (participanteNovo.getEmail() == null) {
			desabilitarBotaoIncluir = true;
			return;
		} else {
			if (participanteNovo.getEmail().equals("")) {
				desabilitarBotaoIncluir = true;
				return;
			}
		}
		desabilitarBotaoIncluir = false;

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

	public Boolean getDesabilitarBotaoIncluir() {
		return desabilitarBotaoIncluir;
	}

	public void setDesabilitarBotaoIncluir(Boolean desabilitarBotaoIncluir) {
		this.desabilitarBotaoIncluir = desabilitarBotaoIncluir;
	}

	public Boolean getErroMatricula() {
		return erroMatricula;
	}

	public void setErroMatricula(Boolean erroMatricula) {
		this.erroMatricula = erroMatricula;
	}

}
