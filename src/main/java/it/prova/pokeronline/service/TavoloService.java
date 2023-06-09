package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

public interface TavoloService {
	
	//CRUD
	
	List<Tavolo> listAllElements(boolean eager);

	Tavolo caricaSingoloElemento(Long id);

	Tavolo caricaSingoloElementoEager(Long id);

	Tavolo aggiorna(Tavolo tavoloInstance);

	Tavolo inserisciNuovo(Tavolo tavoloInstance);

	void rimuovi(Long id);
	
	// FIND

	List<Tavolo> findAllSpecialPlayer(String name);

	Tavolo caricaSingoloElementoSpecialPlayer(Long idTavolo, Long idUtente);

	Tavolo findByDenominazione(String string);

	List<Tavolo> findEsperienzaMinima(Integer esperienzaAccumulata);

	Tavolo findLastGame(Utente utenteLoggato);

}
