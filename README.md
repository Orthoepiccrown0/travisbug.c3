# travisbug.c3


## Breve Introduzione:

  	Il progetto è un’applicazione Web per un Centro Commerciale, che permette ad
  	ogni suo negozio di registrarsi sulla piattaforma per vendere i propri prodotti
  	online che possono essere comprati da un cliente e spediti da un corriere in
  	un locker o ritirati direttamente di persona in negozio.
  
  
## Attori che compaiono nel progetto:

	• Cliente:
		Il cliente è colui che può comprare i prodotti dalla piattaforma.
		Può gestire il suo carrello, procedere all'aquisto di prodotti,
		ritirare la merce ordinata in negozio oppure nei punti di ritiro.
		Inoltre può visualizzare e filtrare le promozioni del sito.	

	• Corriere:
		Il corriere si registra sulla piattaforma e visualizza tutte le spedizioni disponibili. 
		Può sceglierle ed effettuare il trasporto della merce. 
		Preleva la merce dai negozi e la rilascia presso i punti di prelievo. 

	• Commerciante:
		Coincide con titolare del negozio. 
		Può aggiungere o rimuovere prodotti, aggiungere scorte o applicare uno sconto e lanciare vendite promozionali. 
		Accetta i suoi impiegati dall'area notifiche. 

	• Impiegato:
		L'impiegato è una persona che lavora in un determinato negozio all'interno del centro commerciale.
		Ha il compito di preparare i pacchi richiesti dai clienti, aggiornare lo stato della spedizione 
		e consegnare tale pacco al corriere, in caso di ritiro direttamente in negozio da parte del cliente,
		provvede a consegnarlo di persona.

	• Servizio clienti:
		Si occupa di aiutare gli utenti registrati quando questi hanno bisogno di aiuto o vogliono chiedere informazioni.

	• Amministratore:
		L'amministratore è il moderatore della piattaforma.
		Approva sia le richieste di registrazione alla piattaforma dei commercianti sia le promozioni.  

## Funzionalità:

	• Registrazione nella piattaforma
		Un utente può registrarsi nella piattaforma come “Cliente”, “Commerciante”,
		“Impiegato” o “Corriere” che sono gli attori del progetto.
		Il commerciante registra anche il suo negozio, che per poter essere
		visualizzato nel sito deve essere accettato dall’Amministratore.
		L’impiegato per poter operare online deve essere approvato dal commerciante
		del suo negozio.

	• Gestione del negozio
		Un commerciante può inserire un nuovo prodotto dalla sua area personale,
		scegliendo nome, categoria, prezzo, descrizione, peso e scorte.
		Può anche rimuovere, inserire scorte e aggiungere sconti a determinati
		prodotti, oppure aggiungere una promozione su un’intera categoria che deve
		essere però approvata dall’amministratore.

	• Acquisto prodotti
		Un cliente può visualizzare tutti i prodotti dei negozi e può aggiungere al
		carrello quelli che desidera acquistare, quando è pronto per procedere con
		l’ordine, dovrà recarsi alla sezione “carrello”, scegliere l’indirizzo di
		spedizione (che avrà un costo di spedizione), può anche modificare la quantità 
		di ogni singolo articolo, per poi procedere al pagamento.

	• Elaborazione dell’ordine
		L’impiegato, nell’apposita sezione, può visualizzare tutti gli ordini
		effettuati nel suo negozio.
		Dopo aver preparato il pacco, potrà aggiornare lo stato dell’ordine per
		comunicare ai corrieri della spedizione disponibile, in caso di ritiro in
		negozio, verrà comunicato al cliente che il pacco è pronto per il ritiro.

	• Gestione spedizioni
		Il corriere può visualizzare tutte le spedizioni disponibili, quelle prese in
		carico e quelle terminate (che può eliminare).
		Può modificare lo stato delle spedizioni dopo averle prese in carico.

	• Servizio assistenza
		Tutti gli utenti registrati potranno usufruire del servizio di assistenza,
		compilando un form che invierà una mail al supporto.

	• Cancellazione dell’account
		Tutti gli utenti registrati possono cancellare il proprio account dalla
		piattaforma, in caso sia un commerciante verrà rimosso anche il suo negozio.


## Tecnologie Utilizzate:
  
	La parte di back-end è stata realizzata utilizzando il framework “Spring Boot” che, insieme a
	“Java Persistence API”, ci ha permesso di utilizzare il database, con motore MariaDB, in modo
	semplice ed efficace.

	La parte di front-end è stata realizzata in formato HTML, con CSS e JavaScript, con l’aiuto del
	framework Bootstrap e Thymeleaf, un motore di template.

	Per la gestione delle versioni del progetto abbiamo utilizzato GitHub, che ha facilitato il lavoro
	di gruppo attraverso i commit ed il semplice accesso alla repository.

	Per la configurazione del progetto abbiamo utilizzato Gradle.
	
## Link Utili
  
	Repository UML del progetto con Visual Paradigm: [Travis Bug VP]:(https://github.com/Orthoepiccrown0/travisbug.c3.vp)
  
## Partecipanti al progetto:

	Francesco Santarelli
	francesc.santarelli@studenti.unicam.it

	Dmytro Zyatikov
	dmytro.zyatikov@studenti.unicam.it

	Diego Concetti
	diego.concetti@studenti.unicam.it

