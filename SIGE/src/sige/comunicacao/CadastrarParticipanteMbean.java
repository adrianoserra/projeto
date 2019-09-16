package sige.comunicacao;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sige.controle.ParticipanteControle;
import sige.modeo.Participante;
import sigo.util.Util;




@ViewScoped
@ManagedBean
public class CadastrarParticipanteMbean {

	private ParticipanteControle participanteControle = new ParticipanteControle(); 
	private Util util = new Util();
	private Participante participante;
	private String matricula;
	private String senha;
	
	  @PostConstruct
      public void inicializar (){
		  participante = new Participante();
    	  
		
	}
	  
	  public void salvar () {
		  try {
			participanteControle.salvarParticipante(participante);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			util.getMenssagemErro();
			return;
		}
		  util.getMenssagemInfor("Participante salvo com sucesso!");
	  }
	  
	  public void logar () {
		  try {
			  Participante participanteAutenticado = participanteControle.autenticarUsuario(matricula, senha);
			  if (participanteAutenticado != null) {
				  
			  } else {
				  
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
