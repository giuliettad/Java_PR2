import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Test {

    public static void batteryTestHashMap() throws IllegalAccessException {
        int i = 0;

        System.out.println("-------------------- TEST BOARD_1 --------------------");
        String user1 = "giulietta_d";
        String invalidUser = null;
        String invalidpsw = null;
        String invalidUser2 = "4567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ujhgfdshnchvbnsdfghj567890'098765434567890poiuhygfxcvghjklngfdsdfgujn12345678901234567890ertyuiopsdfghjklòzxcvbnmaaamnbvcxzlkjhgfdsoiuytrew876543";
        String friend = "Carlotta";
        String copyfriend = "Roberta";

        //CREAZIONE PASSWORD
        String giulia_PSW = "_De9l3nzE!o";
        String chiara_PSW = "maDhrS1nZ?.";
        String danilo_PSW = "!sOlaUadiMajjeq";

        //CREAZIONE OGGETTI DI TIPO BOARD_1
        Board_1 giuliaBoard = new Board_1("Giulia", giulia_PSW);
        Board_1 chiaraBoard = new Board_1("Chiara", chiara_PSW);
        Board_1 daniloBoard = new Board_1("Danilo", danilo_PSW);

        Board_1 bacheca;
        System.out.println("\n____________________________");
        System.out.println("------- USERNAME TEST -------");
        System.out.println(" ____________________________");
        try {
            System.out.println("\n*** Inserisco un username che supera il numero massimo di caratteri. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_1(invalidUser2, "Invalidpsw");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Username troppo lungo. ");
        }
        try {
            System.out.println("\n*** Inserisco username nullo. Mi aspetto NullPointerException ***");
            bacheca = new Board_1(invalidUser, "Invalidpsw");
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Username:" + e.getMessage());
        }

        System.out.println("\n____________________________");
        System.out.println("------- PASSWORD TEST -------");
        System.out.println(" ____________________________");
        try {
            System.out.println("\n*** Inserisco come password la stringa vuota. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_1("Chiara", "");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Password non valida. ");
        }
        try {
            System.out.println("\n*** Inserisco una password con un numero di caratteri minore di 8. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_1("Alessio", "a_7ye");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! La password deve avere una lunghezza minima di 8 caratteri ");
        }
        try {
            System.out.println("\n*** Inserisco passord nulla. Mi aspetto NullPointerException ***");
            bacheca = new Board_1("Roberta", invalidpsw);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Password nulla non valida. ");
        }
        try {
            System.out.println("\n*** Inserisco una password che non contiene caratteri alfanumerici. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_1("Luigi", "passwordno");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Password non valida. La password DEVE contenere caratteri alfanumerici.");
        }

        System.out.println("\n____________________________");
        System.out.println("------- CATEGORIES TEST -------");
        System.out.println("  ____________________________");

        //aggiungo categorie alle board precedentemente create
        String[] categories1 = {"cani", "gatti", "mucche", "cavalli", "insetti"};
        String[] categories2 = {"polmoni", "cuore", "scheletro"};
        String[] categories3 = {"Apple", "Samsung", "Huawei", "Honor", "Lg"};

        for (String value : categories1) {
            try {
                giuliaBoard.createCategory(value, giulia_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }

        for (String value : categories2) {
            try {
                chiaraBoard.createCategory(value, chiara_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }
        //System.out.println("\n");

        for (String value : categories3) {
            try {
                daniloBoard.createCategory(value, danilo_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }

        try{
            System.out.println("\n*** Provo a inserire una categoria non valida. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory(".!0o,,", giulia_PSW);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Categoria non valida. Sono ammessi solo caratteri.");
        }

        try{
            System.out.println("\n*** Provo a inserire stringa vuota come categoria. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory("", giulia_PSW);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Categoria non valida.");
        }
        try{
            System.out.println("\n*** Provo a inserire una categoria nulla. Mi aspetto NullPointerException ***");
            giuliaBoard.createCategory(invalidUser, giulia_PSW);
        }catch (NullPointerException e){
            System.out.println("Errore! Categoria nulla. ");
        }
        try {
            System.out.println("\n*** Provo a inserire una categoria già presente. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory("cani", giulia_PSW);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore! CATEGORIA GIA' ESISTENTE.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError");
            daniloBoard.createCategory("casa", chiara_PSW);
        }catch ( IllegalAccessError e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        try {
            System.out.println("\n*** Provo a rimuovere una categoria non presente. Mi aspetto IllegalArgumentException ***");
            chiaraBoard.removeCategory("casa", chiara_PSW);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore! CATEGORIA NON ESISTENTE.");
        }

        System.out.println("\n____________________________");
        System.out.println("------- FRIENDS TEST -------");
        System.out.println("  ____________________________");

        //Aggiungo amici alla board di Giulia
        String[] friendsList = {"Roberta", "Bianca", "Eugenio", "Luigi", "Davide"};
        String[] friendsList2 = {"Lucia", "Feliciana", "Edu", "Carlo"};
        String frd ="Donato";

       for(String f : friendsList){
           giuliaBoard.addFriend("gatti", giulia_PSW, f);
       }

       for(Object f : giuliaBoard.getHash("gatti")){
            System.out.println("Categoria: gatti. Amico: " + f.toString());
        }
        for(String f : friendsList2){
            giuliaBoard.addFriend("cani", giulia_PSW, f);
        }

        try {
            System.out.println("\n*** Provo ad aggiungere un amico a una categoria non esistente. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.addFriend("delfini", giulia_PSW, friend);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Impossibile aggiungere " + friend + " alla bacheca. Categoria NON presente!"  );
        }

        try{
            System.out.println("\n*** Provo ad aggiungere un amico già presente nella lista di amici. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.addFriend("gatti", giulia_PSW, copyfriend);
        }catch (IllegalArgumentException e){
            System.out.println("Impossibile aggiungere " + copyfriend + ". Amico già aggiunto.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError");
            giuliaBoard.addFriend("cavalli",danilo_PSW, "Giovanni");
        }catch ( IllegalAccessError e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        try {
            System.out.println("\n*** Provo rimuovere un amico da una categoria NON esistente. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.removeFriend("delfini", giulia_PSW, friend);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Impossibile rimuovere " + friend + " alla bacheca. Categoria NON presente!"  );
        }

        try{
            System.out.println("\n*** Provo a rimuovere un amico NON presente nella lista di amici. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.removeFriend("gatti", giulia_PSW, frd );
        }catch (IllegalArgumentException e){
            System.out.println("Impossibile rimuovere " + frd + ". Amico non trovato.");
        }

        System.out.println("\n____________________________");
        System.out.println("--------- POST TEST ---------");
        System.out.println(" ____________________________");

        System.out.println("\n*** Aggiungo i dati in giuliaBoard ***");
        Data tempData;
        tempData = new Data("Giulia", "Zoe è un labrador di 3 anni.", "cani" );
        giuliaBoard.put(giulia_PSW, tempData, "cani");
        giuliaBoard.insertLike("Lucia",tempData);
        giuliaBoard.displayDato("cani", tempData);

        tempData = new Data("Giulia", "Frida è un cucciolo di British Shorthair.", "gatti" );
        giuliaBoard.put(giulia_PSW, tempData, "gatti");
        giuliaBoard.insertLike("Roberta", tempData);
        giuliaBoard.insertLike("Eugenio", tempData);
        giuliaBoard.displayDato("gatti", tempData);
        //tempData.display();

        //System.out.println("Inserisco lo stesso dato in due categorie diverse");
        tempData = new Data("Giulia", "zanzara tigre", "insetti" );
        giuliaBoard.put(giulia_PSW, tempData, "insetti");
        giuliaBoard.displayDato("insetti", tempData);

        //tempData.display();

        tempData = new Data("Giulia", "Prova di post gatti", "gatti" );
        giuliaBoard.put(giulia_PSW, tempData, "gatti");
        giuliaBoard.insertLike("Eugenio", tempData);
        giuliaBoard.insertLike("Luigi", tempData);
        giuliaBoard.insertLike("Bianca", tempData);
        giuliaBoard.displayDato("gatti", tempData);



        //Creo nuovo dato uguale a uno già memorizzato
        System.out.println("\n____________________________");
        System.out.println(" ----------TEST GET-----------");
        System.out.println("  ____________________________");
        System.out.println("\n*** Cerco un dato che ho già memorizzato, se lo trovo lo stampo ***");
        tempData = new Data("Giulia", "Zoe è un labrador di 3 anni.", "cani" );
        giuliaBoard.get(giulia_PSW, tempData);

        System.out.println("\n*** Cerco un dato non presente ***");
        tempData = new Data("Matteo", "Ciao ciao ciao", "cani");
        giuliaBoard.get(giulia_PSW, tempData);
        System.out.println(tempData.getTesto() + ". Not found.");

        try{
            System.out.println("\n*** Cerco dato nullo. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.get(giulia_PSW, null);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Dato non valido.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError ***");
            giuliaBoard.get(chiara_PSW, tempData);
        }catch ( IllegalAccessError e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        System.out.println("\n____________________________");
        System.out.println("-------- REMOVE TEST --------");
        System.out.println(" ____________________________");
        tempData = new Data("Giulia", "Zoe è un labrador di 3 anni.", "cani" );
        System.out.println("Rimuovo dato presente.");
        giuliaBoard.remove(giulia_PSW, tempData);

        System.out.println("*** Provo a rimuovere dato NON presente:  ***");
        tempData = new Data("Domenico", "Testo senza senso", "cani");
        tempData.display();
        giuliaBoard.remove(giulia_PSW, tempData);

        try{
            System.out.println("\n*** Cerco di rimuovere dato nullo. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.remove(giulia_PSW, null);
        }catch (NullPointerException e){
            System.out.println("Errore! Dato non valido.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError ***");
            giuliaBoard.remove(chiara_PSW, tempData);
        }catch ( IllegalAccessError e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        System.out.println("\n______________________________");
        System.out.println("---- GET DATA CATEGORY TEST ----");
        System.out.println(" ______________________________");

        giuliaBoard.getDataCategory(giulia_PSW, "gatti");



        System.out.println("\n____________________________");
        System.out.println("--------- LIKE TEST ---------");
        System.out.println(" ____________________________");

        System.out.println("*** Un amico aggiunge un like, visualizzo il dato prima e dopo l'aggiunta ***");
        tempData = new Data("Giulia", "Simba è un cucciolo di Persiano.", "gatti" );
        giuliaBoard.put(giulia_PSW, tempData, "gatti");
        giuliaBoard.displayDato("gatti", tempData);
        giuliaBoard.insertLike("Bianca", tempData);
        giuliaBoard.displayDato("gatti", tempData);

        try {
            System.out.println("*** Prova ad aggiungere un like una paersona che non fa pate degli amici, mi aspetto IllegalAccexException ***");
            giuliaBoard.insertLike("Matteo", tempData);
        }catch (IllegalAccessException e){
            System.out.println("\nErrore! L'amico  Matteo non fa parte degli amici.");
        }

        //System.out.println("Numero like: " + giuliaBoard.getDatoFromHash("gatti", tempData).getN_likes());

        System.out.println("\n______________________________");
        System.out.println("--------- ITERATOR TEST -------");
        System.out.println(" ______________________________");

        System.out.println("\nTest per la restituzione dell'iteratore senza remove che genera tutti i dati in bacheca ordinati rispetto al numero di like");
        try{
            giuliaBoard.getIterator(giulia_PSW);
            System.out.println("ok");
        }catch (IllegalAccessError e){
            System.out.println("Password errata");
        }
        System.out.println("\nPasso come parametro password errata mi aspetto IllegalAccessError");
        try {
            giuliaBoard.getIterator(chiara_PSW);
            System.out.println("ok");
        }catch (IllegalAccessError e){
            System.out.println("Password errata " + e.getMessage());
        }

        System.out.println("\nTest per la restituzione dell'iteratore senza remove che genera tutti i dati in bacheca condivisi");
        try {
            giuliaBoard.getFriendIterator("Luigi");
            System.out.println("ok");
        }catch (IllegalArgumentException e){
            System.out.println("Amico non valido");
        }


    }

















    public static void batteryTestArrayList() throws IllegalAccessException {
        System.out.println("\n--------------------TEST SECONDA IMPLEMENTAZIONE--------------------------------\n");
        String giulia_PSW = "_De9l3nzE!o";
        String chiara_PSW = "maDhrS1nZ?.";
        String luca_PSW = "!sOlaUadiMajjeq";
        String lorenzo_PSW = "De9l3!sOla1nZ?.";
        String copyfriend = "Roberta";
        String friend = "Carlotta";

        String invalidUser = null;
        String invalidpsw = null;
        String invalidUser2 = "4567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ujhgfdshnchvbnsdfghj567890'098765434567890poiuhygfxcvghjklngfdsdfgujn12345678901234567890ertyuiopsdfghjklòzxcvbnmaaamnbvcxzlkjhgfdsoiuytrew876543";
        String danilo_PSW = "!sOlaUadiMajjeq";

        //CREAZIONE OGGETTI DI TIPO BOARD_2
        Board_2 giuliaBoard = new Board_2("Giulia", giulia_PSW);
        Board_2 chiaraBoard = new Board_2("Chiara", chiara_PSW);
        Board_2 daniloBoard = new Board_2("Danilo", danilo_PSW);
       /* Board_2 lucaBoard = new Board_2("Luca", luca_PSW);
        Board_2 lorenzoBoard = new Board_2("Lorenzo", lorenzo_PSW);*/

        Board_2 bacheca;

        System.out.println("\n____________________________");
        System.out.println("------- USERNAME TEST -------");
        System.out.println(" ____________________________");
        try {
            System.out.println("\n*** Inserisco un username che supera il numero massimo di caratteri. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_2<>(invalidUser2, "Invalidpsw");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Username troppo lungo. ");
        }
        try {
            System.out.println("\n*** Inserisco username nullo. Mi aspetto NullPointerException ***");
            bacheca = new Board_2(invalidUser, "Invalidpsw");
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Username:" + e.getMessage());
        }

        System.out.println("\n____________________________");
        System.out.println("------- CATEGORIES TEST -------");
        System.out.println("  ____________________________");

        //aggiungo categorie alle board precedentemente create
        String[] categories1 = {"cani", "gatti", "mucche", "cavalli", "insetti"};
        String[] categories2 = {"polmoni", "cuore", "scheletro"};
        String[] categories3 = {"Apple", "Samsung", "Huawei", "Honor", "Lg"};

        for (String value : categories1) {
            try {
                giuliaBoard.createCategory(value, giulia_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }

        //System.out.println("\n");
        for (String value : categories2) {
            try {
                chiaraBoard.createCategory(value, chiara_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }


        for (String value : categories3) {
            try {
                daniloBoard.createCategory(value, danilo_PSW);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore! Categoria già esistente " + e.getMessage());
            }
        }

        try{
            System.out.println("\n*** Provo a inserire una categoria non valida. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory(".!0o,,", giulia_PSW);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Categoria non valida. Sono ammessi solo caratteri.");
        }

        try{
            System.out.println("\n*** Provo a inserire stringa vuota come categoria. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory("", giulia_PSW);
        }catch (IllegalArgumentException e){
            System.out.println("Errore! Categoria non valida.");
        }
        try{
            System.out.println("\n*** Provo a inserire una categoria nulla. Mi aspetto NullPointerException ***");
            giuliaBoard.createCategory(invalidUser, giulia_PSW);
        }catch (NullPointerException e){
            System.out.println("Errore! Categoria nulla. ");
        }
        try {
            System.out.println("\n*** Provo a inserire una categoria già presente. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.createCategory("cani", giulia_PSW);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore! CATEGORIA GIA' ESISTENTE.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError");
            daniloBoard.createCategory("Lg", chiara_PSW);
        }catch ( IllegalAccessException e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        try {
            System.out.println("\n*** Provo a rimuovere una categoria non presente. Mi aspetto IllegalArgumentException ***");
            chiaraBoard.removeCategory("casa", chiara_PSW);
        } catch (NullPointerException e) {
            System.out.println("Errore! CATEGORIA NON ESISTENTE.");
        }

        System.out.println("\n____________________________");
        System.out.println("------- PASSWORD TEST -------");
        System.out.println(" ____________________________");
        try {
            System.out.println("\n*** Inserisco come password la stringa vuota. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_2("Chiara", "");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Password non valida. ");
        }
        try {
            System.out.println("\n*** Inserisco una password con un numero di caratteri minore di 8. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_2("Alessio", "a_7ye");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! La password deve avere una lunghezza minima di 8 caratteri ");
        }
        try {
            System.out.println("\n*** Inserisco passord nulla. Mi aspetto NullPointerException ***");
            bacheca = new Board_2("Roberta", invalidpsw);
        } catch (NullPointerException e) {
            System.out.println("ERRORE! Password nulla non valida. ");
        }
        try {
            System.out.println("\n*** Inserisco una password che non contiene caratteri alfanumerici. Mi aspetto IllegalArgumentException ***");
            bacheca = new Board_2("Luigi", "passwordno");
        } catch (IllegalArgumentException e) {
            System.out.println("ERRORE! Password non valida. La password DEVE contenere caratteri alfanumerici.");
        }

// Test Friends

        giuliaBoard.addFriend("gatti", giulia_PSW, "luca");
        giuliaBoard.addFriend("gatti", giulia_PSW, "bianca");
        giuliaBoard.addFriend("gatti", giulia_PSW, "laura");
        giuliaBoard.addFriend("cani", giulia_PSW, "elena");
        giuliaBoard.addFriend("insetti", giulia_PSW, "giuseppe");
        giuliaBoard.addFriend("mucche", giulia_PSW, "toremove");
        giuliaBoard.removeFriend("mucche", giulia_PSW, "toremove");

        chiaraBoard.addFriend("polmoni", chiara_PSW, "lorenzo");
        chiaraBoard.addFriend("cuore", chiara_PSW, "luca");
        chiaraBoard.addFriend("scheletro", chiara_PSW, "giorgio");

        System.out.println("\n____________________________");
        System.out.println("------- FRIENDS TEST -------");
        System.out.println("  ____________________________");

        String frd ="Donato";

        try {
            System.out.println("\n*** Provo ad aggiungere un amico a una categoria non esistente. Mi aspetto IllegalArgumentException ***");
           giuliaBoard.addFriend("prova", giulia_PSW, "luigi");
        }catch( NullPointerException e) { System.out.print("Categoria non esistente: "); System.out.println(e);}


        try{
            System.out.println("\n*** Provo ad aggiungere un amico già presente nella lista di amici. Mi aspetto IllegalArgumentException ***");
            giuliaBoard.addFriend("gatti", giulia_PSW, copyfriend);
        }catch (IllegalArgumentException e){
            System.out.println("Impossibile aggiungere " + copyfriend + ". Amico già aggiunto.");
        }

        try {
            System.out.println("\n*** Inserisco una password errata. Mi aspetto  IllegalAccessError");
            giuliaBoard.addFriend("cavalli",danilo_PSW, "Giovanni");
        }catch ( IllegalAccessError e){
            System.out.println("Errore! Password non valida. Autenticazione fallita.");
        }

        try {
            System.out.println("\n*** Provo rimuovere un amico da una categoria NON esistente. Mi aspetto NullPointerException ***");
            giuliaBoard.removeFriend("delfini", giulia_PSW, friend);
        }catch (NullPointerException e){
            System.out.println("Errore! Impossibile rimuovere " + friend + " alla bacheca. Categoria NON presente!"  );
        }

        try{
            System.out.println("\n*** Provo a rimuovere un amico NON presente nella lista di amici. Mi aspetto NullPointerException ***");
            giuliaBoard.removeFriend("gatti", giulia_PSW, frd );
        }catch (NullPointerException e){
            System.out.println("Impossibile rimuovere " + frd + ". Amico non trovato.");
        }

        System.out.println("\n____________________________");
        System.out.println("--------- POST TEST ---------");
        System.out.println(" ____________________________");

        System.out.println("\n*** Aggiungo i dati in giuliaBoard ***");
        Data tempData;
        tempData = new Data("Giulia", "Zoe è un labrador di 3 anni.", "cani" );
        giuliaBoard.put(giulia_PSW, tempData, "cani");
        giuliaBoard.get(giulia_PSW, tempData).display();

        tempData = new Data("Giulia", "Frida è un cucciolo di British Shorthair.", "gatti" );
        giuliaBoard.put(giulia_PSW, tempData, "gatti");
        giuliaBoard.get(giulia_PSW, tempData).display();


        //System.out.println("Inserisco lo stesso dato in due categorie diverse");
        tempData = new Data("Giulia", "zanzara tigre", "insetti" );
        giuliaBoard.put(giulia_PSW, tempData, "insetti");
        giuliaBoard.get(giulia_PSW, tempData).display();

        System.out.println("\n____________________________");
        System.out.println("--------- LIKE TEST ---------");
        System.out.println(" ____________________________");

        System.out.println("*** Un amico aggiunge un like, visualizzo il dato prima e dopo l'aggiunta ***");
        tempData = new Data("Giulia", "Simba è un cucciolo di Persiano.", "gatti" );
        giuliaBoard.put(giulia_PSW, tempData, "gatti");
        giuliaBoard.get(giulia_PSW, tempData).display();
        giuliaBoard.insertLike("laura", tempData);

        try{
            System.out.println("*** Prova ad aggiungere un like una persona che non fa pate degli amici, mi aspetto IllegalArgumentException ***");
            giuliaBoard.insertLike("Matteo", tempData);
        }catch (IllegalArgumentException e){
            System.out.println("Impossibile aggiungere il like. Amico non trovato.");
        }

        // Test Iteratori
        System.out.println("\n"+"FUNZIONAMENTO ITERATORE CHE VISUALIZZA I DATI IN BACHECA IN ORDINE DI NUMLIKES");
        System.out.println("\n" + "Test iteratore bacheca 'Giulia'");
        Iterator it = giuliaBoard.getIterator(giulia_PSW);
        while(it.hasNext())
        {
            final Data x = (Data) it.next();
            x.display();
        }

        System.out.println("TEST ECCEZIONI: ");
        try {
            giuliaBoard.getIterator(chiara_PSW);
        }catch(IllegalAccessException e) { System.out.print("Password errata: "); System.out.println(e);}


        try {
            it = giuliaBoard.getIterator(giulia_PSW);
            if(it.hasNext())
            {
                 Data x = (Data) it.next();
                it.remove();
            }
        }catch(UnsupportedOperationException e) { System.out.print("Remove sull'iterator non consentita!: "); System.out.println(e);}




        System.out.println("\n"+"TEST ITERATORE CHE VISUALIZZA I DATI IN BACHECA VISUALIZZABILI DA UN AMICO PASSATO PER PARAMETRO");
        it = giuliaBoard.getFriendIterator("elena");
        while(it.hasNext())
        {
            final Data x = (Data) it .next();
            x.display();
        }
        System.out.println("TEST ECCEZIONI: ");

        try {
            giuliaBoard.getFriendIterator(null);
        }catch(final NullPointerException e) { System.out.print("Friend passato null: "); System.out.println(e);}

        try {
            it = giuliaBoard.getFriendIterator("elena");
            if(it.hasNext())
            {
                Data x = (Data) it.next();
                it.remove();
            }
        }catch(UnsupportedOperationException e) { System.out.print("Remove sull'iterator non consentita!: "); System.out.println(e);}



        System.out.println("\n"+"TEST METODO CHE VISUALIZZA I DATI IN BACHECA DI UNA CERTA CATEGORIA");
        System.out.println(giuliaBoard.getDataCategory(giulia_PSW, "gatti"));

        System.out.println("TEST:");
        try {
            giuliaBoard.getDataCategory( chiara_PSW, "gatti");
        }catch(IllegalAccessException e) { System.out.print("Password errata: "); System.out.println(e);}

        try {
            giuliaBoard.getDataCategory(giulia_PSW, "notexist");
        }catch(NullPointerException e) { System.out.print("Categoria non esistente: "); System.out.println(e);}

        try {
            giuliaBoard.getDataCategory(giulia_PSW,null);
        }catch(final NullPointerException e) { System.out.print("Categoria passata nulla: "); System.out.println(e);}

        try {
            giuliaBoard.getDataCategory(null, "testo");
        }catch(NullPointerException e) { System.out.print("Password nulla: "); System.out.println(e);}


    }

    public static void main (String[] args) throws IllegalAccessException {

        batteryTestHashMap();
        batteryTestArrayList();
    }
}





