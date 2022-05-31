package it.prova.gestionebiglietti.dao;

import java.util.List;

import it.prova.gestionebiglietti.model.Biglietto;

public interface BigliettoDAO extends IBaseDAO<Biglietto> {
	
	List<Biglietto> findByExample(Biglietto example) throws Exception;

}
