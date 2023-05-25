package it.prova.pokeronline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;

import java.time.LocalDate;

@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner{

	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private TavoloService tavoloServiceInstance;

	
	public static void main(String[] args)  {
		SpringApplication.run(PokeronlineApplication.class, args);
	}
	
		// RUOLO
		public void run(String... args) throws Exception {
			if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
				ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
			}

			if (ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER) == null) {
				ruoloServiceInstance.inserisciNuovo(new Ruolo("Player", Ruolo.ROLE_PLAYER));
			}
	 
			if (ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER) == null) {
				ruoloServiceInstance.inserisciNuovo(new Ruolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
			}
			
			// a differenza degli altri progetti cerco solo per username perche' se vado
			// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
			// password non lo
			// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
			if (utenteServiceInstance.findByUsername("admin") == null) {
				Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now());
				
				
				admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("ROLE_ADMIN", Ruolo.ROLE_ADMIN));
				utenteServiceInstance.inserisciNuovo(admin);
				// l'inserimento avviene come created ma io voglio attivarlo
				utenteServiceInstance.changeUserAbilitation(admin.getId());
			}

			if (utenteServiceInstance.findByUsername("player") == null) {
				Utente classicUser = new Utente("player", "player", "Antonio", "Verdi", LocalDate.now());
				
				classicUser.getRuoli()
						.add(ruoloServiceInstance.cercaPerDescrizioneECodice("ROLE_PLAYER", Ruolo.ROLE_PLAYER));
				utenteServiceInstance.inserisciNuovo(classicUser);
				// l'inserimento avviene come created ma io voglio attivarlo
				utenteServiceInstance.changeUserAbilitation(classicUser.getId());
			}

			if (utenteServiceInstance.findByUsername("specialplayer") == null) {
				Utente classicUser1 = new Utente("specialplayer", "specialplayer", "Antonioo", "Verdii", LocalDate.now());
				
				classicUser1.getRuoli()
						.add(ruoloServiceInstance.cercaPerDescrizioneECodice("ROLE_SPECIAL_PLAYER", Ruolo.ROLE_SPECIAL_PLAYER));
				utenteServiceInstance.inserisciNuovo(classicUser1);
				// l'inserimento avviene come created ma io voglio attivarlo
				utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
			}

			if (utenteServiceInstance.findByUsername("admin2") == null) {
				Utente classicUser2 = new Utente("admin2", "admin2", "Antoniooo", "Verdiii", LocalDate.now());
		
				classicUser2.getRuoli()
						.add(ruoloServiceInstance.cercaPerDescrizioneECodice("ROLE_ADMIN", Ruolo.ROLE_ADMIN));
				utenteServiceInstance.inserisciNuovo(classicUser2);
				// l'inserimento avviene come created ma io voglio attivarlo
				utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
			}

			// TAVOLO

			if (tavoloServiceInstance.findByDenominazione("tavolo1") == null) {
				Tavolo tavolo1 = new Tavolo(0, 100, "tavolo1", LocalDate.now(),
						utenteServiceInstance.findByUsername("admin"));
				tavoloServiceInstance.inserisciNuovo(tavolo1);
			}

			if (tavoloServiceInstance.findByDenominazione("tavolo2") == null) {
				Tavolo tavolo2 = new Tavolo(0, 200, "tavolo2", LocalDate.now(),
						utenteServiceInstance.findByUsername("player"));
				tavoloServiceInstance.inserisciNuovo(tavolo2);
			}

			if (tavoloServiceInstance.findByDenominazione("tavolo3") == null) {
				Tavolo tavolo3 = new Tavolo(0, 300, "tavolo3", LocalDate.now(),
						utenteServiceInstance.findByUsername("specialplayer"));
				tavoloServiceInstance.inserisciNuovo(tavolo3);
			}

	}

}
