package sige.modeo;

public class Presenca {
	private Integer id;
	private Boolean presente;
	private Participante participante;
	private Inscricao incricao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getPresente() {
		return presente;
	}
	public void setPresente(Boolean presente) {
		this.presente = presente;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public Inscricao getIncricao() {
		return incricao;
	}
	public void setIncricao(Inscricao incricao) {
		this.incricao = incricao;
	}
	
	

}
