# Progetto-Enterprise-Applications-Authentication
Authentication server per il resource server del corso di Enterprise Applications, permette di ottenere access-token effettuando login in modo **locale (utilizzando email,username,password**) e **provider esterni (google)** </br>
Sviluppato da Andrea Marchio, **223401**
L'applicazione è pensata per essere utilizzata con un resource server, il cui codice è disponibile nella seguente repository: [Progetto-Enterprise-Applications-REST-API](https://github.com/AndreaDev001/Progetto-Enteprise-Applications-REST-API)</br>
Se si vuole eseguire il server in locale è necessario cambiare le seguenti variabili d'ambiente:
+ **SERVER_PORT**
+ **DB_USERNAME**
+ **DB_PASSWORD**
+ **GOOGLE_CLIENT_ID**
+ **GOOGLE_CLIENT_SECRET**
+ **GOOGLE_CLIENT_NAME** </br>

Valori GOOGLE_CLIENT_ID,GOOGLE_CLIENT_SECRET,GOOGLE_CLIENT_NAME: </br>
  CLIENT_ID: 851600564894-peisdmus5cfrvrt1eom896pbrvhf09mv.apps.googleusercontent.com> </br>
  CLIENT_SECRET: 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 </br>
  CLIENT_NAME: Enterprise Applications Login </br>

Il server è eseguito già su una VPS remota e di conseguenza per provare l'applicazione non è necessario eseguirlo localmente </br>
E' possibile eseguire il server in modo locale, anche se così facendo non sarà possibile effettuare il login con Google, per via della configurazione su google cloud console

