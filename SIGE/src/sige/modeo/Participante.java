package sige.modeo;

import java.util.List;

public class Participante {
	private String nome;
	private String setor;
	private String CPF;
	private String email;
	private String telefone;
	private String senha;
	private String senhaCriptrografada;
	private int tipoUsuario;
	private Evento eventoAtual;
	private List<Evento> colecaoEventoAtual;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenhaCriptrografada() {
		return senhaCriptrografada;
	}
	public void setSenhaCriptrografada(String senhaCriptrografada) {
		this.senhaCriptrografada = senhaCriptrografada;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Evento getEventoAtual() {
		return eventoAtual;
	}
	public void setEventoAtual(Evento eventoAtual) {
		this.eventoAtual = eventoAtual;
	}
	public List<Evento> getColecaoEventoAtual() {
		return colecaoEventoAtual;
	}
	public void setColecaoEventoAtual(List<Evento> colecaoEventoAtual) {
		this.colecaoEventoAtual = colecaoEventoAtual;
	}


}
