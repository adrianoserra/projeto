package sige.comunicacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sige.controle.EventoControle;
import sige.modeo.Evento;
import sige.util.Util;

@ViewScoped
@ManagedBean
public class CadastrarEventosMbean {

	private EventoControle eventoControle = new EventoControle(); 
	private Util util = new Util();
	private Evento evento;
		
	
	private int idEvento;
	private String tema;
	private String data_evento;
	private String horario_inicio;
	private String horario_fim;
	private String carga_horaria;
	private int matricula;
	
	private String mensagem;
	
	  @PostConstruct
      public void inicializar (){
		  evento = new Evento();
		  mensagem = "";
    	  
		
	}
	  
	  public void salvar () {
		  try {
			  
				  eventoControle.salvarEvento(evento);
			      mensagem = "Evento salvo com sucesso!";
			      util.exibirDialog("confirmação");
			  
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem = "Erro grave, entre em contato com o administrador";
			util.exibirDialog("erro");
			return;
		}
		  
	  }

	public EventoControle getEventoControle() {
		return eventoControle;
	}

	public void setEventoControle(EventoControle eventoControle) {
		this.eventoControle = eventoControle;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getData_evento() {
		return data_evento;
	}

	public void setData_evento(String data_evento) {
		this.data_evento = data_evento;
	}

	public String getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(String horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public String getHorario_fim() {
		return horario_fim;
	}

	public void setHorario_fim(String horario_fim) {
		this.horario_fim = horario_fim;
	}

	public String getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(String carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	  
	
	
	
}
