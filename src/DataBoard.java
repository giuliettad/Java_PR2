import java.util.Iterator;
import java.util.List;

/**
 * Iterfaccia DataBoard, rappresenta una collezione di oggetti generici che estendono il tipo di dato Data
 * @author Giulia De Paola
 *
 */
public interface DataBoard<E extends Data> {
    /**
     * OVERVIEW: Tipo modificabile che rappresenta una collezione di oggetti, che estendono il tipo di dato Data,
     *           associati a una categoria, che fungono da spazio per la memorizzazione e
     *           visualizzazione di dati
     *
     * TYPICAL ELEMENT: <categoria_i, <Element_0, ...,Element_n> >
     *                  dove 0<=i<=getCategoryLength() e n=getDataCategory(password_i, categoria_i).size()
     *
     */

    /**
     * REQUIRES: password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * MODIFIES: this
     * EFFECTS: Crea una nuova categoria di dati nella bacheca
     * THROWS:  IllegalAccessError
     * @param category Nome della categoria da creare
     * @param passw password associata alla categoria
     */
    public void createCategory(String category, String passw) throws IllegalAccessError, IllegalAccessException;


    /**
     * REQUIRES: password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * MODIFIES: this
     * EFFECTS: Rimuove una nuova categoria di dati dalla bacheca
     * THROWS:  IllegalAccessError, IllegalArgumentException
     * @param category Nome della categoria da creare
     * @param passw password associata alla categoria
     */
    public void removeCategory(String category, String passw) throws IllegalAccessError, IllegalArgumentException;


    /**
     * REQUIRES: friend!= null && password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * MODIFIES: this
     * EFFECTS: Aggiunge un amico alla lista di amici che possono vedere una categoria
     * THROWS:  IllegalAccessError, IllegalArgumentException
     * @param category Nome della categoria da creare
     * @param passw password associata alla categoria
     */
    public void addFriend(String category, String passw, String friend) throws IllegalAccessError, IllegalArgumentException, IllegalAccessException;


    /**
     * REQUIRES: friend!= null && password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * MODIFIES: this
     * EFFECTS: Rimuove un amico alla lista di amici che possono vedere una categoria
     * THROWS:  IllegalAccessError, IllegalArgumentException
     * @param category Nome della categoria da creare
     * @param passw password associata alla categoria
     */
    public void removeFriend(String category, String passw, String friend) throws IllegalAccessError, IllegalArgumentException;

    /**
     * REQUIRES: dato!= null && password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * MODIFIES:this
     * EFFECTS: Aggiunge un dato alla bacheca, se il dato è null lancia eccezione
     * THROWS: IllegalAccessExceptios
     * @param category Nome della categoria da creare
     * @param passw password associata alla categoria
     */
    public boolean put(String passw, E dato, String category) throws IllegalAccessException;


    /**
     * REQUIRES: dato!= null && password!=null && password diversa da stringa vuota
     * EFFECTS: ottiene una copia del dato in bacheca, se il dato è null lancia eccezione
     * THROWS: IllegalAccessError
     * @param passw password associata alla categoria
     * @param dato dato di cui ottenere la copia
     */
    public E get(String passw, E dato) throws IllegalAccessError, IllegalAccessException;
    // Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
    /**
     * REQUIRES: dato!= null && password!=null && password diversa da stringa vuota
     * MODIFIES:this
     * EFFECTS: rimuove il dato dalla bacheca, se il dato è null lancia eccezione
     * THROWS: IllegalAccessError
     * @param passw password associata alla categoria
     * @param dato dato di cui ottenere la copia
     */
    public E remove(String passw, E dato) throws IllegalAccessError, IllegalAccessException;

    /**
     * REQUIRES:password!=null && password diversa da stringa vuota && category!= null && cateory diversa da stringa vuota
     * EFFECTS: Crea la lista dei dati in bacheca per una determinata categoria
     * THROWS:  IllegalAccessError, IllegalArgumentException
     * @param passw password associata alla categoria
     * @param category Nome della categoria da creare
     * @return
     */
    public List<E> getDataCategory(String passw, String category) throws IllegalAccessError, IllegalArgumentException, IllegalAccessException;

    /**
     * REQUIRES:password!=null && password diversa da stringa vuota
     * EFFECTS: Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
     * THROWS:  IllegalAccessError
     * @param passw password associata alla categoria
     */
    public Iterator<E> getIterator(String passw) throws IllegalAccessError, IllegalAccessException;


    /**
     * REQUIRES: dato!= null && friend!=null
     * MODIFIES:this
     * EFFECTS: Associa un like, da parte di un amico, al dato condiviso
     * THROWS: Se dato = null lancia una NullPointException
     *         ( eccezione disponibile in Java, unchecked)
     * @param friend amico che vuole aggiungere il like
     * @param dato dato di cui ottenere la copia
     */
    void insertLike(String friend, E dato)throws NullPointerException, IllegalArgumentException, IllegalAccessException;

    /**
     * REQUIRES:friend!=null && friend diverso da stringa vuota
     * EFFECTS: Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi con un amico
     * THROWS: IllegalArgumentException,NullPointerException
     * @param friend amico a cui sono condivisi i dati
     */
    public Iterator<E> getFriendIterator(String friend);

}