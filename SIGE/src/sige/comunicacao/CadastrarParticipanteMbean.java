package sige.comunicacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sige.controle.ParticipanteControle;
import sige.modeo.Participante;
import sige.modeo.Usuario;
import sige.util.Util;

@SessionScoped
@ManagedBean
public class CadastrarParticipanteMbean {

	private ParticipanteControle participanteControle = new ParticipanteControle();
	private Util util = new Util();
	private Participante participante;
	private String CPF;
	private String senha;
	private String mensagem;
	private String senhaAtual;
	private String confirmarSenha;
	private String senhaNova;
	private Participante participanteAutenticado;

	@PostConstruct
	public void inicializar() {
		participante = new Participante();
		mensagem = "";

	}

	public void salvar() {
		try {
			if (validar() && !verificarSeCPFExiste()) {
				participanteControle.salvarParticipante(participante);
				mensagem = "Cadastro realizado com sucesso!";
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

	public void deslogar() {
		participanteAutenticado = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarParticipante.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logar() throws IOException {
		if (CPF == null || CPF.equals("")) {
			mensagem = "O campo CPF é obrigatório para o login!";
			util.exibirDialog("alerta");
			return;
		}
		if (senha == null || senha.equals("")) {
			mensagem = "O campo senha é obrigatório para o login!";
			util.exibirDialog("alerta");
			return;
		}
		try {
			participanteAutenticado = participanteControle.autenticarUsuario(CPF, senha);
			if (participanteAutenticado != null) {
				if (participanteAutenticado.getTipoUsuario() == 0) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("inicioADM.xhtml");
				} else {
					FacesContext.getCurrentInstance().getExternalContext().redirect("eventos.xhtml");
				}

			} else {
				mensagem = "A senha ou CPF informado está incorreto!";
				util.exibirDialog("alerta");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensagem = "Erro grave, entre em contato com o administrador";
			util.exibirDialog("erro");
		}
	}

	public boolean validar() {
		if (participante.getSenha().equals("")) {
			mensagem = "O campo senha é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (!confirmarSenha.equals(participante.getSenha())) {
			mensagem = "As senhas informadas estão diferentes!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getSetor().equals("")) {
			mensagem = "O campo setor é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getNome().equals("")) {
			mensagem = "O campo nome é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getSetor().equals("")) {
			mensagem = "O campo setor é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getCPF().equals("")) {
			mensagem = "O campo CPF é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getTelefone().equals("")) {
			mensagem = "O campo telefone é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		if (participante.getEmail().equals("")) {
			mensagem = "O campo email é obrigatório!";
			util.exibirDialog("alerta");
			return false;
		}
		return true;

	}

	public boolean verificarSeCPFExiste() {
		boolean existe = false;
		existe = participanteControle.verificarSeCPFExiste(participante.getCPF());
		if (existe) {
			mensagem = "CPF já cadastrado!";
			util.exibirDialog("alerta");
		}
		return existe;
	}

	public Boolean mostrarTelaCadastro() {
		if (participanteAutenticado == null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean mostrarSair() {
		if (participanteAutenticado != null) {
			return true;
		} else {
			return false;
		}
	}

	public void paginaCadastro() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarParticipante.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterarParticipante () {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			participanteControle.alterarParticipante(participanteAutenticado);
			context.addMessage(null, new FacesMessage("Dados alterados com sucesso!", " ") );
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarSenha () {
	
		if (!senhaAtual.equals(participanteAutenticado.getSenha())) {
			util.getMenssagemAlerta("Senha atual incorreta!");
			return;
		}
		if (senhaNova.isEmpty()) {
			util.getMenssagemAlerta("O campo senha nova é obrigatorio!");
			return;
		}
		if (confirmarSenha.isEmpty()) {
			util.getMenssagemAlerta("O campo confirmar senha é obrigatorio!");
			return;
		}
		
		if (!senhaNova.equals(confirmarSenha)) {
			util.getMenssagemAlerta("As senhas informadas estão diferentes!");
			return;
		}
		participanteAutenticado.setSenha(senhaNova);
		try {
			participanteControle.alterarSenha(participanteAutenticado);
			util.getMenssagemInfor("Senha alterada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			util.getMenssagemErro();
			e.printStackTrace();
		}
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
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

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Participante getParticipanteAutenticado() {
		return participanteAutenticado;
	}

	public void setParticipanteAutenticado(Participante participanteAutenticado) {
		this.participanteAutenticado = participanteAutenticado;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

}
