package sige.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class Util {
	
	public void getMenssagemInfor (String menssagem) {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", menssagem));
	}
	
	public void getMenssagemAlerta (String menssagem) {
		   FacesContext.getCurrentInstance().addMessage(
				   null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o!", menssagem));
	}
	
	public void getMenssagemErro () {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Entre em contato com o administrador"));
	}
	public void exibirDialog (String tipo) {
		PrimeFaces current = PrimeFaces.current();
		if (tipo.equals("alerta")) {
		current.executeScript("PF('DialogVarAlerta').show();");
		}
		if (tipo.equals("confirma��o")) {
			current.executeScript("PF('DialogVarConfirmacao').show();");
		}
		if (tipo.equals("erro")) {
			current.executeScript("PF('DialogVarErro').show();");
		}
	}
	
	

}
