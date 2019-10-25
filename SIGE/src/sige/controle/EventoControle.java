package sige.controle;

import java.sql.SQLException;
import java.util.List;

import sige.DAO.EventoDAO;
import sige.modeo.Evento;

public class EventoControle {

	
	private EventoDAO eventoDAO = new EventoDAO();

	public void salvarEvento (Evento evento) throws ClassNotFoundException, SQLException {
		eventoDAO.salvarEvento(evento);
	}

	public List<Evento> listarEventos (){
		return eventoDAO.listarEventos();
	}
	
	public void alterarEvento (Evento evento) throws ClassNotFoundException, SQLException {
		eventoDAO.alterarEvento(evento);
	}
	public void excluirEvento (Evento evento) throws ClassNotFoundException, SQLException {
		eventoDAO.excluirEvento(evento);
	}
}



