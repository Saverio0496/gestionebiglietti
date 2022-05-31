package it.prova.gestionebiglietti.service;

import java.util.List;

import it.prova.gestionebiglietti.dao.BigliettoDAO;
import it.prova.gestionebiglietti.model.Biglietto;

public interface BigliettoService {

	public void setBigliettoDao(BigliettoDAO bigliettoDao);

	public List<Biglietto> listAll() throws Exception;

	public Biglietto caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Biglietto input) throws Exception;

	public void inserisciNuovo(Biglietto input) throws Exception;

	public void rimuovi(Biglietto input) throws Exception;

	public List<Biglietto> cercaByExample(Biglietto input) throws Exception;

}
