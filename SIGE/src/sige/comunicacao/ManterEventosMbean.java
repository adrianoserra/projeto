package sige.comunicacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sige.controle.EventoControle;
import sige.controle.ParticipanteControle;
import sige.modeo.Evento;
import sige.modeo.Participante;
import sige.util.Util;

@ViewScoped
@ManagedBean
public class ManterEventosMbean {
	private EventoControle eventoControle = new EventoControle(); 
	private ParticipanteControle participanteControle = new ParticipanteControle(); 
	private List<Evento> colecaoEvento;
	private Evento eventoNovo;
	private Evento eventoSelecionado;
	private Util util = new Util();
	private Boolean desabilitarBotaoIncluir;
	private List<Participante> colecaoPalestrante;

	@PostConstruct
	public void inicializar() {
		setDesabilitarBotaoIncluir(true);
		colecaoEvento = new ArrayList<Evento>();
		colecaoEvento = eventoControle.listarEventos();
		eventoSelecionado = new Evento();
		setEventoNovo(new Evento());
		colecaoPalestrante = participanteControle.listarParticipante();
		
	}

	public void excluirEvento(Evento evento) {
		try {
			eventoControle.excluirEvento(evento);
			colecaoEvento.remove(evento);
			util.getMenssagemInfor("Evento excluido com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
	}

	public void incluirEvento() {
		setEventoNovo(new Evento());
		util.exibirDialogPF("DialogAdicionar");
	}

	public void alterarEvento(Evento evento) {
		try {
			eventoControle.alterarEvento(evento);
			util.getMenssagemInfor("Evento alterado com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {
			eventoControle.salvarEvento(eventoNovo);
			colecaoEvento.add(eventoNovo);
			util.getMenssagemInfor("Evento incluido com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void validarInclusao() {
	}

	public List<Evento> getColecaoEvento() {
		return colecaoEvento;
	}

	public void setColecaoEvento(List<Evento> colecaoEvento) {
		this.colecaoEvento = colecaoEvento;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public Evento getEventoNovo() {
		return eventoNovo;
	}

	public void setEventoNovo(Evento eventoNovo) {
		this.eventoNovo = eventoNovo;
	}

	public Boolean getDesabilitarBotaoIncluir() {
		return desabilitarBotaoIncluir;
	}

	public void setDesabilitarBotaoIncluir(Boolean desabilitarBotaoIncluir) {
		this.desabilitarBotaoIncluir = desabilitarBotaoIncluir;
	}

	public List<Participante> getColecaoPalestrante() {
		return colecaoPalestrante;
	}

	public void setColecaoPalestrante(List<Participante> colecaoPalestrante) {
		this.colecaoPalestrante = colecaoPalestrante;
	}


}
