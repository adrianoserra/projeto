package sige.comunicacao;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sige.controle.ParticipanteControle;
import sige.modeo.Participante;
import sige.util.Util;




@ViewScoped
@ManagedBean
public class CadastrarParticipanteMbean {

	private ParticipanteControle participanteControle = new ParticipanteControle(); 
	private Util util = new Util();
	private Participante participante;
	private String matricula;
	private String senha;
	private String mensagem;
	
	  @PostConstruct
      public void inicializar (){
		  participante = new Participante();
		  mensagem = "";
    	  
		
	}
	  
	  public void salvar () {
		  try {
			  if (validar() && !verificarSeMatriculaExiste()) {
			      participanteControle.salvarParticipante(participante);
			      mensagem = "Participante salvo com sucesso!";
			      util.exibirDialog("confirmação");
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem = "Erro grave, entre em contato com o administrador";
			util.exibirDialog("erro");
			return;
		}
		  
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
	  
	  public boolean validar (){
		  if (participante.getMatricula().equals("")) {
			  mensagem = "O campo matrícula é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSenha().equals("")) {
			  mensagem = "O campo senha é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  mensagem = "O campo setor é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getNome().equals("")) {
			  mensagem = "O campo nome é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  mensagem = "O campo setor é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getCPF().equals("")) {
			  mensagem = "O campo CPF é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getTelefone().equals("")) {
			  mensagem = "O campo telefone é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getEmail().equals("")) {
			  mensagem = "O campo email é obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  return true;
		  
	  }
	  
	  public boolean verificarSeMatriculaExiste () {
		  boolean existe = false;
		  try {
			existe = participanteControle.verificarSeMatriculaExiste(participante.getMatricula());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		  if (existe) {
			  mensagem = "matrícula já cadastrada, informe outra matrícula!";
			  util.exibirDialog("alerta");
		  }
		  return existe;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
