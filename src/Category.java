import java.util.ArrayList;
import java.util.List;

/**
 * Classe categoria per seconda implementazione.
 * Contenuti e amici non possono essere nulli.
 * I contenuti sono ordinati in base al numero di like (e post validi).
 * Ogni valore dei contenuti contiene la categoria di cui fa parte.
 *
 */
public class Category<E extends Data> {

    private String nome;
    private ArrayList<String> amici;
    private ArrayList<E> dati;

    public Category(String name) {
        amici= new ArrayList<String>();
        dati= new ArrayList<E>();
        this.nome=name;
    }

    //Aggiunge frd alla lista degli amici se non è già contenuto
    public void addFriend(String frd) throws IllegalAccessException, IllegalArgumentException {
        if (!this.amici.contains(frd)) {
            amici.add(frd);
        }
        else throw new IllegalArgumentException();
    }


    //Rimuove l'amico frd dalla lista friends
    public void removeFriend(String frd) throws IllegalArgumentException {
        //Controllo se l'amico esiste nella lista
        if (this.amici.contains(frd))
        {
            //Rimuovo l'amico dalla lista
            this.amici.remove(frd);
            //Ad ogni dato a cui l'amico ha messo like, diminuisco di uno il numero di like del dato
            for (E temp : dati) {
                if (temp.getLiked().contains(frd)) {
                    temp.removelike(frd);
                    temp.minusLike();
                }
            }
        }
        //Non ho trovato l'amico nella lista
        else System.out.println("Errore! Non e' possibile rimuovere " + frd + ". Amico non presente.");
    }


    //Lo uso per il metodo put
    public boolean addDato(E dato, String author){
        //Se il dato e' gia' contenuto nella categoria, return false
        for (Data tmp : dati) if (tmp.isEquals(dato)) return false;
        //Setta l'autore del dato
        dato.setAuthor(author);
        //Aggiunge il dato ai dati della categoria
        dati.add(dato);
        return true;
    }

    public boolean addDato(E dato, String author, int index) {
        for (Data tmp : dati) if (tmp.isEquals(dato)) return false;  //Se il dato e' gia' contenuto nella categoria, return false
        dato.setAuthor(author);					//Setta l'autore del dato
        dati.add(index, dato);					//Aggiunge il dato a un certo index dei dati della categoria
        return true;
    }


    // Usato per getDato e InsertLike

    public E getDato(E dato) { //Ritorna una deep copy del dato uguale al dato passato per parametro
        E datocloned = null;
        for (E temp : dati) { if (temp.isEquals(dato)) { datocloned=(E) temp.copyData(); }}
        return datocloned;
    }
    //  Usato per RemoveDato

    public E removeDato(E dato) { //Rimuove il dato uguale al dato passato per parametro e ritorna una deep copy di esso
        E datocloned = null;
        E savedtmp=null;
        for (E temp : dati) if (temp.equals(dato))  { datocloned=(E) temp.copyData(); if (temp.getLiked()!=null) savedtmp=temp;} //Cerco il dato e ne faccio la deep copy
        if (datocloned==null) return null; //Se non trovo il dato ritorno null
        dati.remove(savedtmp); //Rimuovo il dato
        return datocloned;	//Ritorno la copia del dato
    }

    //  Usato per GetDataCategory e getIterator e InsertLike


    // Restituisce una lista con tutti i dati di una categoria
    public List<E> getDati() {
        ArrayList<E> data = new ArrayList<E>();
        for (E temp : dati) data.add((E) temp.copyData());
        return data;
    }

    //  Usato per getFriendIterator

    // Restituisce una arraylist con tutti gli amici associati alla categoria
    public ArrayList<String> getFriends() {

        ArrayList<String> fd = new ArrayList<String>();
        for (String tmp : amici) fd.add(tmp);
        return fd;
    }

    // Usato dalla classe data per
    // prendere il nome della categoria
    public String getName() {
        return this.nome;
    }


    // Reimplementazione metodo contains
    public boolean containsDato(E dato) {
        for (E temp : dati) if (temp.isEquals(dato)) return true;
        return false;
    }

    // Usato per prendere l'indice di un dato
    public int getIndex(Data dato) {
        int i=0;
        for (E tmp : dati) if (tmp.isEquals(dato)) return i;  else i++;
        return -1;
    }

}

