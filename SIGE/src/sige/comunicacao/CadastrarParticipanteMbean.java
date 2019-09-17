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
	
	  @PostConstruct
      public void inicializar (){
		  participante = new Participante();
    	  
		
	}
	  
	  public void salvar () {
		  try {
			  if (validar() && !verificarSeMatriculaExiste()) {
			      participanteControle.salvarParticipante(participante);
			      util.getMenssagemInfor("Participante salvo com sucesso!");
			  }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			util.getMenssagemErro();
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
			  util.getMenssagemAlerta("O campo matrícula é obrigatorio!");
			  return false;
		  }
		  if (participante.getSenha().equals("")) {
			  util.getMenssagemAlerta("O campo senha é obrigatorio!");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  util.getMenssagemAlerta("O campo setor é obrigatorio!");
			  return false;
		  }
		  if (participante.getNome().equals("")) {
			  util.getMenssagemAlerta("O campo nome é obrigatorio!");
			  return false;
		  }
		  if (participante.getSetor().equals("")) {
			  util.getMenssagemAlerta("O campo setor é obrigatorio!");
			  return false;
		  }
		  if (participante.getCPF().equals("")) {
			  util.getMenssagemAlerta("O campo CPF é obrigatorio!");
			  return false;
		  }
		  if (participante.getTelefone().equals("")) {
			  util.getMenssagemAlerta("O campo telefone é obrigatorio!");
			  return false;
		  }
		  if (participante.getEmail().equals("")) {
			  util.getMenssagemAlerta("O campo email é obrigatorio!");
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
			  util.getMenssagemAlerta("matrícula já cadastrada, informe outra matrícula!");
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

}
