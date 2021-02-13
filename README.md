# travisbug.c3


##Breve Introduzione:

  Il progetto è un’applicazione Web per un Centro Commerciale, che permetta ad
  ogni suo negozio di registrarsi sulla piattaforma per vendere i propri prodotti
  online che possono essere comprati da un cliente e spediti da un corriere in
  un locker o ritirati direttamente di persona in negozio.
  
  
##Attori che compaiono nel progetto:
  
  Cliente - 
  
  Corriere - 
  
  Commerciante - 
  
  Impiegato - 
  
  Supporto - 
  
  Amministratore -
  

##Funzionalità:

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


##Tecnologie Utilizzate:
  
  La parte di back-end è stata realizzata utilizzando il framework “Spring Boot” che, insieme a
  “Java Persistence API”, ci ha permesso di utilizzare il database, con motore MariaDB, in modo
  semplice ed efficace.
  
  La parte di front-end è stata realizzata in formato HTML, con CSS e JavaScript, con l’aiuto del
  framework Bootstrap e Thymeleaf, un motore di template.
  
  Per la gestione delle versioni del progetto abbiamo utilizzato GitHub, che ha facilitato il lavoro
  di gruppo attraverso i commit ed il semplice accesso alla repository.
  
  Per la configurazione del progetto abbiamo utilizzato Gradle.
  
  
##Partecipanti al progetto:

  Francesco Santarelli
  francesc.santarelli@studenti.unicam.it

  Dmytro Zyatikov
  dmytro.zyatikov@studenti.unicam.it

  Diego Concetti
  diego.concetti@studenti.unicam.it

