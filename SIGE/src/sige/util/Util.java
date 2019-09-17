package sige.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
	
	public void getMenssagemInfor (String menssagem) {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", menssagem));
	}
	
	public void getMenssagemAlerta (String menssagem) {
		   FacesContext.getCurrentInstance().addMessage(
				   null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", menssagem));
	}
	
	public void getMenssagemErro () {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Entre em contato com o administrador"));
	}
	
	

}
