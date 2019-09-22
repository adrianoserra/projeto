package sige.comunicacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	private String confirmarSenha;
	
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
			      util.exibirDialog("confirma��o");
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem = "Erro grave, entre em contato com o administrador";
			util.exibirDialog("erro");
			return;
		}
		  
	  }
	  
	  public void logar () throws IOException {
		  if (matricula == null || matricula.equals("")) {
			  mensagem = "O campo matr�cula � obrigatorio para o login!";
			  util.exibirDialog("alerta");
			  return;
		  } 
          if (senha == null || senha.equals("")) {
    			  mensagem = "O campo senha � obrigatorio para o login!";
    			  util.exibirDialog("alerta");
    			  return;
		  }
		  try {
			  Participante participanteAutenticado = participanteControle.autenticarUsuario(matricula, senha);
			  if (participanteAutenticado != null) {
				  if (participanteAutenticado.getTipoUsuario() == 2) {
					  FacesContext.getCurrentInstance().getExternalContext().redirect("contato.html");  
				  } else {
					  FacesContext.getCurrentInstance().getExternalContext().redirect("contato.html");
				  }
				  
			  } else {
				  mensagem = "a senha ou matr�cula informadas est�o erradas";
					util.exibirDialog("alerta");
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem = "Erro grave, entre em contato com o administrador";
			util.exibirDialog("erro");
		}
	  }
	  
	  public boolean validar (){
		  if (participante.getMatricula().equals("")) {
			  mensagem = "O campo matr�cula � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSenha().equals("")) {
			  mensagem = "O campo senha � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (!confirmarSenha.equals(participante.getSenha())) {
			  mensagem = "As senhas informadas est�o diferentes!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  mensagem = "O campo setor � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getNome().equals("")) {
			  mensagem = "O campo nome � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  mensagem = "O campo setor � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getCPF().equals("")) {
			  mensagem = "O campo CPF � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getTelefone().equals("")) {
			  mensagem = "O campo telefone � obrigatorio!";
			  util.exibirDialog("alerta");
			  return false;
		  }
		  if (participante.getEmail().equals("")) {
			  mensagem = "O campo email � obrigatorio!";
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
			  mensagem = "matr�cula j� cadastrada, informe outra matr�cula!";
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

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
